package com.trendcore;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class SessionStoreAlgo {

    class Codes {

        public static final int ST_EXPIRED = 400;
        public static final int ST_NOT_PRESENT = 401;
        public static final int OK = 200;
        public static final int FORCE_LOGOUT = 201;
    }

    public static void main(String[] args) {

        class Database{
            Map store = new ConcurrentHashMap();

            public <T> void persist(String id,T t) {
                store.put(id,t);
            }

            public <T> T fetch(String id,Class<T> t) {
                return t.cast(store.get(id));
            }
        }

        Database database = new Database();


        class Request{
            String action;
            Map map = new HashMap();

            public Object val(String key) {
                return map.get(key);
            }

            public void val(String key, Object value) {
                map.put(key,value);
            }
        }

        class Response extends Request{

            int status;

            public void setStatus(int i) {
                status = i;
            }

        }

        class Node{
            static final String ST = "ST";
            static final String USERNAME = "USERNAME";
            public static final String RESPONSE = "response";
            ExecutorService executor = Executors.newFixedThreadPool(4);

            String name;

            InMemoryStore inMemoryStore;

            List<Node> nodeList;

            public void addNode(Node node) {
                nodeList.add(node);
            }

            class InMemoryStore extends Database{
                @Override
                public <T> void persist(String id, T t) {
                    store.put(id,t);
                }
            }

            public Node(String s) {
                this.name = s;
                inMemoryStore = new InMemoryStore();
                nodeList = new ArrayList<>();
            }

            class ServiceTicket{

                UUID uuid;

                long lastModifiedTime;

                final long expiryTime = TimeUnit.MINUTES.toSeconds(2);

                String username;

                public ServiceTicket(UUID uuid) {
                    this.uuid = uuid;
                }

                public String getId() {
                    return uuid.toString();
                }
            }


            public Future<Response> processRequest(final Supplier<Request> requestSupplier) {

                return executor.submit(() -> {

                    Request request = requestSupplier.get();

                    Response response = new Response();

                    if(request.action != "/login"){
                        if(request.val(ST) == null) {
                            response.val(RESPONSE,"Service Ticket not found.");
                            response.setStatus(Codes.ST_NOT_PRESENT);
                            return response;
                        }
                    }

                    switch (request.action){
                        case "/demo":{
                                String serviceTicketId = (String) request.val(ST);
                                ServiceTicket serviceTicket = inMemoryStore.fetch(serviceTicketId,ServiceTicket.class);

                                if (System.currentTimeMillis() > serviceTicket.lastModifiedTime + serviceTicket.expiryTime) {
                                        serviceTicket.lastModifiedTime = System.currentTimeMillis();
                                        response.val(RESPONSE,serviceTicket);
                                        response.setStatus(Codes.OK);
                                }else{
                                    response.val(RESPONSE,"Service Ticket Expired.");
                                    response.setStatus(Codes.ST_EXPIRED);
                                }
                            }
                            break;
                        case "/login":{
                                ServiceTicket serviceTicket = new ServiceTicket(UUID.randomUUID());
                                serviceTicket.lastModifiedTime = System.currentTimeMillis();
                                serviceTicket.username = (String) request.val("username");

                                database.persist(serviceTicket.getId(), serviceTicket);

                                inMemoryStore.persist(serviceTicket.getId(), serviceTicket);

                                executor.submit(() -> informOtherNodes(serviceTicket));

                                //get Active Session count
                                int activeSession = getActiveSessions((String) request.val(USERNAME));
                                if(activeSession > 2){
                                    response.val(RESPONSE,serviceTicket);
                                    response.setStatus(Codes.FORCE_LOGOUT);
                                }else{
                                    response.val(ST,serviceTicket);
                                    response.setStatus(Codes.OK);
                                }
                            }
                            break;
                    }
                    return response;
                });
            }

            private int getActiveSessions(String username) {

                class Count{
                    int count;

                    public void increment() {
                        count++;
                    }
                }

                Count count = new Count();

                inMemoryStore.store.keySet().forEach(o -> {
                    if(username.equals(inMemoryStore.store.get(o))){
                        count.increment();
                    }
                });
                return count.count;
            }

            private void informOtherNodes(ServiceTicket serviceTicket) {
                nodeList.forEach(node -> node.addServiceTicket(serviceTicket));
            }

            private void addServiceTicket(ServiceTicket serviceTicket) {
                executor.submit(() -> inMemoryStore.persist(serviceTicket.getId(),serviceTicket));
            }
        }


        Node node1 = new Node("Node-1");
        Node node2 = new Node("Node-2");
        node1.addNode(node2);
        node2.addNode(node1);

        Runnable client = () -> {
            try {
                //send req to node

                Response response = node1.processRequest(() -> {
                    Request request = new Request();
                    request.action = "/login";
                    request.val(Node.USERNAME,"user1");
                    return request;
                }).get();

                processResponse(response, res -> System.out.println(res.val(Node.RESPONSE) + " " + res.status));

                processResponse(node1.processRequest(() -> {
                    Request request = new Request();
                    request.action = "/demo";
                    //request.object = ((Node.ServiceTicket)response.data).getId();
                    request.val(Node.ST,((Node.ServiceTicket)response.val(Node.ST)).getId());
                    return request;
                }).get(), res -> System.out.println(res.val(Node.RESPONSE) + " " + res.status));



            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        };

        Thread thread1 = new Thread(client);

        thread1.start();


    }

    private static <T> void processResponse(T t, Consumer<T> consumer) {
        consumer.accept(t);
    }

}
