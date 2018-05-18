package com.trendcore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.*;

public class SessionStoreAlgo {

    public static void main(String[] args) {

        class Database{
            Map store = new ConcurrentHashMap();

            public <T> void persist(String id,T t) {
                store.put(id,t);
            }
        }

        Database database = new Database();

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

                long currentTime;

                final long expiryTime = TimeUnit.MINUTES.toSeconds(30);

                public ServiceTicket(UUID uuid) {
                    this.uuid = uuid;
                }

                public String getId() {
                    return uuid.toString();
                }
            }

            public Future processRequest(final String serviceTicketId) {

                return executor.submit(() ->{
                        ServiceTicket serviceTicket = null;
                        if(serviceTicketId == null){                            //create st
                            serviceTicket = new ServiceTicket(UUID.randomUUID());
                            serviceTicket.currentTime = System.currentTimeMillis();
                            //store it database
                            database.persist(serviceTicket.getId(),serviceTicket);
                            //store it in memory
                            inMemoryStore.persist(serviceTicket.getId(),serviceTicket);

                            ServiceTicket finalServiceTicket = serviceTicket;
                            executor.submit(() -> informOtherNodes(finalServiceTicket));

                        }else{
                            //validate st

                            //get from in memory

                            //if not found then fetch from database

                            //validate expiration time of ticket
                        }
                        return serviceTicket.getId();
                    }
                );
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
                Object serviceTicket = node1.processRequest(null).get();



            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        };


    }

}
