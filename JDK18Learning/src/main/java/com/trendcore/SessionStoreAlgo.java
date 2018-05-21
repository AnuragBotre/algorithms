package com.trendcore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class SessionStoreAlgo {

    class Codes {

        public static final int ST_EXPIRED = 400;
        public static final int ST_NOT_PRESENT = 401;
        public static final int OK = 200;
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

        class Response{

            Object data;

            int status;

            public void setData(Object data) {
                this.data = data;
            }

            public void setStatus(int i) {

            }

            public Object getData() {
                return data;
            }
        }

        class Request{

            String action;

            Object object;

        }

        class Node{
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

                final long expiryTime = TimeUnit.MINUTES.toSeconds(30);

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
                        if(request.object == null) {
                            response.setData("Service Ticket not found.");
                            response.setStatus(Codes.ST_NOT_PRESENT);
                            return response;
                        }
                    }

                    switch (request.action){
                        case "/demo":{
                                String serviceTicketId = (String) request.object;
                                ServiceTicket serviceTicket = inMemoryStore.fetch(serviceTicketId,ServiceTicket.class);
                                if (System.currentTimeMillis() > serviceTicket.lastModifiedTime + serviceTicket.expiryTime) {
                                        response.setData(serviceTicket);
                                        response.setStatus(Codes.OK);
                                }else{
                                    response.setData("Service Ticket Expired.");
                                    response.setStatus(Codes.ST_EXPIRED);
                                }
                            }
                            break;
                        case "/login":{
                                ServiceTicket serviceTicket = new ServiceTicket(UUID.randomUUID());
                                serviceTicket.lastModifiedTime = System.currentTimeMillis();

                                database.persist(serviceTicket.getId(), serviceTicket);

                                inMemoryStore.persist(serviceTicket.getId(), serviceTicket);

                                ServiceTicket finalServiceTicket = serviceTicket;
                                executor.submit(() -> informOtherNodes(finalServiceTicket));
                                response.setData(serviceTicket);
                                response.setStatus(Codes.OK);
                            }
                            break;
                    }
                    return response;
                });
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
                    return request;
                }).get();

                printResponse(response,res -> System.out.println(res.data + " " + res.status));

                Response response1 = node1.processRequest(() -> {
                    Request request = new Request();
                    request.action = "/demo";
                    request.object = ((Node.ServiceTicket)response.data).getId();
                    return request;
                }).get();

                printResponse(response1 , res -> System.out.println(res.data + " " + res.status));

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        };

        Thread thread1 = new Thread(client);

        thread1.start();


    }

    private static <T> void printResponse(T t,Consumer<T> consumer) {
        consumer.accept(t);
    }

}
