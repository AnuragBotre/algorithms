package com.trendcore.lock;



import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static com.trendcore.lock.MultiLockApp.PartitionNames.*;

public class MultiLockApp {

    interface Visitor{
        void visit(Tree t);
    }

    interface OAction{
        <O> O execute();
    }

    interface IAction<I>{
        void execute(I i);
    }

    interface IOAction{
        <I,O> O execute(I i);
    }

    interface Lock<T>{
        void lock(T t);

        void unlock(T t);
    }

    static class Tree{
        String name;
        Set<Tree> relations = new HashSet<>();

        public Tree(String name) {
            this.name = name;
        }

        public Tree(PartitionNames name) {
            this.name = name.name();
        }

        public void add(Tree tree) {
            relations.add(tree);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Set<Tree> getRelations() {
            return relations;
        }

        public void setRelations(Set<Tree> relations) {
            this.relations = relations;
        }
    }

    static enum PartitionNames{
        Product,
        User,
        Roles,
        Groups,
        Operation,
        UserProfile,
        ProductCallbackUrl,
        UserAttribute,
        RegisterServices,
        TicketGrantingTicket, ServiceTicket, AttributeSpecification
    }

    public static void main(String[] args) throws Exception {

        class TreeTraversal{

            public void traverse(Tree t,Visitor visitor) {
                visitor.visit(t);
                if(t.relations != null) {

                    for (final Tree subTree : t.relations) {
                        traverse(subTree, visitor);
                    }

                }
            }
        }

        class LockAcquireAlgo{
            public void performUnderLock(List list,Lock lock,OAction action){
                int i = 0;
                try{
                    while(i < list.size()){
                        lock.lock(list.get(i));
                        i++;
                    }
                    action.execute();
                } catch (Exception e) {
                    System.err.println(Thread.currentThread().getName()+ " " + e.getMessage());
                } finally {
                    i--;
                    while(i >= 0){
                        lock.unlock(list.get(i));
                        i--;
                    }
                }
            }
        }

        class CommonData{

            Map<String,Partitions> partitionMap = new HashMap();

            public CommonData(){
                createPartition(Product);
                createPartition(User);
                createPartition(Roles);
                createPartition(Groups);
                createPartition(Operation);
                createPartition(UserProfile);
                createPartition(ProductCallbackUrl);
                createPartition(UserAttribute);
                createPartition(AttributeSpecification);
                createPartition(RegisterServices);
                createPartition(TicketGrantingTicket);
                createPartition(ServiceTicket);
            }

            private void createPartition(PartitionNames name) {
                Partitions partitions = new Partitions(name.name());
                partitionMap.put(partitions.name,partitions);
            }

            public void modifyData(final Tree tree) {
                modifyUnderLock(tree,new OAction(){
                    @Override
                    public <O> O execute() {
                        TreeTraversal treeTraversal = new TreeTraversal();
                        treeTraversal.traverse(tree, new Visitor() {
                            @Override
                            public void visit(Tree t) {
                                System.out.println(Thread.currentThread().getName() + " Modifying data on partition :- " + partitionMap.get(t.name).name);
                                sleep(200);
                            }
                        });
                        return null;
                    }
                });
            }

            private void modifyUnderLock(Tree tree, OAction iAction) {
                TreeTraversal t = new TreeTraversal();
                final List<String> list = new ArrayList<>();
                t.traverse(tree, new Visitor() {
                    @Override
                    public void visit(Tree t) {
                        list.add(t.name);
                    }
                });

                Collections.sort(list);

                int i = 0;

                LockAcquireAlgo lockAcquireAlgo = new LockAcquireAlgo();
                lockAcquireAlgo.performUnderLock(list, new Lock() {
                    @Override
                    public void lock(Object o) {
                        partitionMap.get(o).readWriteLock.writeLock().lock();
                    }

                    @Override
                    public void unlock(Object o) {
                        partitionMap.get(o).readWriteLock.writeLock().unlock();
                    }
                },iAction);

            }

            public void readData(final Tree tree) throws Exception {

                readUnderLock(tree, new OAction() {
                    @Override
                    public <O> O execute() {
                        TreeTraversal t = new TreeTraversal();
                        t.traverse(tree, new Visitor() {
                            @Override
                            public void visit(Tree t) {
                                System.out.println(Thread.currentThread().getName() + " Reading Data from partition :- " + partitionMap.get(t.name).name);
                            }
                        });
                        return null;
                    }
                });


            }

            private void readUnderLock(Tree tree, final OAction action) throws Exception {
                TreeTraversal t = new TreeTraversal();
                final List<String> list = new ArrayList<>();
                t.traverse(tree, new Visitor() {
                    @Override
                    public void visit(Tree t) {
                        //System.out.println(Thread.currentThread().getName() + " Reading Data from partition :- " + partitionMap.get(t.name).name);
                        list.add(t.name);
                    }
                });

                Collections.sort(list);

                LockAcquireAlgo lockAcquireAlgo = new LockAcquireAlgo();
                lockAcquireAlgo.performUnderLock(list, new Lock() {
                    @Override
                    public void lock(Object o) {
                        if(!partitionMap.get(o).readWriteLock.readLock().tryLock()){
                            throw new RuntimeException("Could not acquire lock for partition :- " + o);
                        }
                    }

                    @Override
                    public void unlock(Object o) {
                        partitionMap.get(o).readWriteLock.readLock().unlock();
                    }
                },action);

            }

            class Partitions{

                private String name;

                private ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);

                public Partitions(String name) {
                    this.name = name;
                }
            }


        }




        final Tree t = new Tree("User");
        Tree st1 = new Tree("Roles");
        st1.add(new Tree("Product"));
        t.add(st1);

        /*ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writer().writeValueAsString(t));*/

        TreeTraversal treeTraversal = new TreeTraversal();
        final List list  = new ArrayList();
        treeTraversal.traverse(t, new Visitor() {
            @Override
            public void visit(Tree t) {
                list.add(t.name);
            }
        });
        Collections.sort(list);

        LockAcquireAlgo lockAcquireAlgo = new LockAcquireAlgo();
        lockAcquireAlgo.performUnderLock(list, new Lock() {
            @Override
            public void lock(Object o) {
                if ("User".equals(o)) {
                    throw new RuntimeException("Could not acquire lock on User.");
                }
                System.out.println("Acquiring Lock :- " + o);
            }


            @Override
            public void unlock(Object o) {
                System.out.println("Releasing Lock :- " + o);
            }
        }, new OAction() {
            @Override
            public <O> O execute() {
                System.out.println("here.");
                return null;
            }
        });


        final CommonData commonData = new CommonData();

        Runnable readUser = new Runnable() {
            @Override
            public void run() {
                Tree user = new Tree(User);
                user.add(new Tree(UserProfile));
                user.add(new Tree(UserAttribute));
                user.add(new Tree(Groups));
                Tree roles = new Tree(Roles);
                roles.add(new Tree(Operation));
                user.add(roles);

                boolean flag = true;
                while(flag) {
                    try {
                        commonData.readData(user);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    sleep(2000);
                }
            }
        };

        Runnable readProduct = new Runnable() {
            @Override
            public void run() {
                Tree product = new Tree(Product);
                product.add(new Tree(ProductCallbackUrl));

                boolean flag = true;

                while(flag){
                    try {
                        commonData.readData(product);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    sleep(2000);
                }
            }
        };

        Runnable regServices = new Runnable() {
            @Override
            public void run() {
                Tree registeredServices = new Tree(RegisterServices);
                registeredServices.add(new Tree(Roles));
                Tree product = new Tree(Product);
                product.add(new Tree(ProductCallbackUrl));
                registeredServices.add(product);

                boolean flag = true;

                while(flag){
                    try {
                        commonData.readData(registeredServices);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    sleep(2000);
                }
            }
        };

        Runnable stTGT = new Runnable() {
            @Override
            public void run() {
                Tree st = new Tree(ServiceTicket);
                st.add(new Tree(TicketGrantingTicket));

                boolean flag = true;

                while(flag){
                    try {
                        commonData.readData(st);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    sleep(500);
                }
            }
        };

        new Thread(readUser).start();
        new Thread(readProduct).start();
        new Thread(regServices).start();
        new Thread(stTGT).start();

        final ExecutorService executorService = Executors.newFixedThreadPool(10);

        Thread taskSubmitterThread = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean flag = true;
                System.out.println("Options :- ");
                while(flag){

                    executorService.submit(new Runnable() {
                        @Override
                        public void run() {
                            Tree user = new Tree(User);
                            user.add(new Tree(UserProfile));
                            user.add(new Tree(UserAttribute));
                            user.add(new Tree(Groups));
                            Tree roles = new Tree(Roles);
                            roles.add(new Tree(Operation));
                            user.add(roles);
                            commonData.modifyData(user);
                        }
                    });

                    executorService.submit(new Runnable() {
                        @Override
                        public void run() {
                            Tree user = new Tree(Operation);
                            user.add(new Tree(Roles));
                            user.add(new Tree(Groups));
                            user.add(new Tree(User));
                            Tree roles = new Tree(UserProfile);
                            roles.add(new Tree(UserAttribute));
                            user.add(roles);
                            commonData.modifyData(user);
                        }
                    });

                    executorService.submit(new Runnable() {
                        @Override
                        public void run() {
                            Tree productCallBackUrl  = new Tree(ProductCallbackUrl);
                            productCallBackUrl.add(new Tree(Product));
                            commonData.modifyData(productCallBackUrl);
                        }
                    });

                    executorService.submit(new Runnable() {
                        @Override
                        public void run() {
                            Tree roles = new Tree(Operation);
                            roles.add(new Tree(Roles));
                            commonData.modifyData(roles);
                        }
                    });

                    sleep(1000);
                }
            }
        });

        taskSubmitterThread.start();


    }

    public static void sleep(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
        }
    }
}
