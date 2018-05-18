package com.trendcore;

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

            class InMemoryStore extends Database{
                @Override
                public <T> void persist(String id, T t) {
                    store.put(id,t);
                }
            }



            public Node(String s) {
                this.name = s;
                inMemoryStore = new InMemoryStore();
            }

            class ServiceTicket{

                UUID uuid;

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
                            //store it database
                            database.persist(serviceTicket.getId(),serviceTicket);
                            //store it in memory
                            inMemoryStore.persist(serviceTicket.getId(),serviceTicket);
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
        }


        Node node = new Node("Node-1");

        Runnable client = () -> {
            try {
                //send req to node
                Object serviceTicket = node.processRequest(null).get();



            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        };




        try {
            Object serviceTicket = node.processRequest(null).get();

            //if()

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

}
