package com.trendcore;

import java.util.UUID;
import java.util.concurrent.*;

public class SessionStoreAlgo {

    public static void main(String[] args) {

        class Node{
            ExecutorService executor = Executors.newFixedThreadPool(4);

            String name;

            public Node(String s) {
                this.name = s;
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

                    if(serviceTicketId == null){
                        //create st
                        serviceTicket = new ServiceTicket(UUID.randomUUID());
                    }else{
                        //validate st

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
