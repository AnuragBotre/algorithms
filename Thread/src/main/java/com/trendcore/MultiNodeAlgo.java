package com.trendcore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MultiNodeAlgo {

    public static void main(String[] args) {

        class Database{

            ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);

            Object object;

            public <T> void store(T s) {
                try {
                    readWriteLock.writeLock().lock();
                    object = s;
                }finally {
                    readWriteLock.writeLock().unlock();
                }
            }

            public <T> T get() {
                try {
                    readWriteLock.readLock().lock();
                    return (T) object;
                }finally {
                    readWriteLock.readLock().unlock();
                }
            }
        }

        final Database d = new Database();

        class Node{

            private List<Node> list;

            private String name;

            private STStorage storage;

            public Node(String name) {
                this.name = name;
                storage = new STStorage();
            }

            public void setOtherNodes(Node node) {
                if(list == null){
                    list = new ArrayList();
                }

                list.add(node);
            }

            class ServiceTicket{

                private String id;

                private long currentTime;

                private long expiryTime = TimeUnit.MINUTES.toMinutes(2);

                public ServiceTicket(String s) {
                    id = s;
                    currentTime = System.currentTimeMillis();
                }
            }

            class STStorage extends Database{
            }

            public ServiceTicket assignServiceTicket() {
                //create service ticket in database
                ServiceTicket s = new ServiceTicket("Node-1 ServiceTicket");
                d.store(s);
                informOtherNodes(s);
                storage.store(s);
                return s;
            }

            private void informOtherNodes(ServiceTicket s) {
                for(Node n : list){
                    n.passInfo(s);
                }
            }

            private void passInfo(ServiceTicket s) {

            }

            public void validateST(ServiceTicket serviceTicket){
                ServiceTicket o = d.get();
                if(System.currentTimeMillis() + o.expiryTime < o.currentTime){
                    throw new RuntimeException("Service Ticket Expired.");
                }
            }
        }

        Node node1 = new Node("Node1");
        Node node2 = new Node("Node2");

        node1.setOtherNodes(node2);
        node2.setOtherNodes(node1);

        Node.ServiceTicket serviceTicket = node1.assignServiceTicket();
        node1.validateST(serviceTicket);

    }

}
