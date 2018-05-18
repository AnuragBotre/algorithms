package com.trendcore;

import java.util.*;
import java.util.concurrent.*;

public class SessionStoreAlgo {

    public static void main(String[] args) {

        class Database{
            Map store = new ConcurrentHashMap();

            public <T> void persist(String id,T t) {
                store.put(id,t);
            }

            public <T> T fetch(String id) {
                return (T) store.get(id);
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


            public Future<Response> processRequest(final String serviceTicketId) {

                return executor.submit(() -> {
                    Response response = new Response();
                    ServiceTicket serviceTicket = null;
                    if (serviceTicketId == null) {                            //create st
                        serviceTicket = new ServiceTicket(UUID.randomUUID());
                        serviceTicket.lastModifiedTime = System.currentTimeMillis();
                        //store it database
                        database.persist(serviceTicket.getId(), serviceTicket);
                        //store it in memory
                        inMemoryStore.persist(serviceTicket.getId(), serviceTicket);

                        ServiceTicket finalServiceTicket = serviceTicket;
                        executor.submit(() -> informOtherNodes(finalServiceTicket));
                        response.setData(serviceTicket);
                        response.setStatus(200);
                    } else {

                        //validate st

                        //get from in memory
                        serviceTicket = inMemoryStore.fetch(serviceTicketId);
                        if (serviceTicket == null) {
                            System.err.println(serviceTicketId + " not found.");
                        } else {
                            if (System.currentTimeMillis() > serviceTicket.lastModifiedTime + serviceTicket.expiryTime) {
                                response.setData(serviceTicket);
                                response.setStatus(200);
                            }
                        }

                        //if not found then fetch from database

                        //validate expiration time of ticket

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
                Response response = node1.processRequest(null).get();

                System.out.println(response.data);

                boolean flag = true;
                for(int i = 0; i < 100 ; i++){
                    node1.processRequest(((Node.ServiceTicket)response.data).getId());
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        };

        Thread thread1 = new Thread(client);

        client.run();


    }

}
