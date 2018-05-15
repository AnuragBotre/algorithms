package com.trendcore.lock;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static com.trendcore.lock.MultiLockApp.PartitionNames.*;

public class MultiLockApp {

    interface Visitor{
        void visit(Tree t);
    }

    static class Tree{
        String name;
        Set<Tree> relations = new HashSet<>();

        public Tree() {
        }

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
        AttributeSpecification
    }

    public static void main(String[] args) throws Exception {


        final Tree t = new Tree("User");
        Tree st1 = new Tree("Roles");
        st1.add(new Tree("Product"));
        t.add(st1);

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writer().writeValueAsString(t));

        class TreeTraversal{

            public void traverse(Tree t,Visitor visitor) throws Exception {
                visitor.visit(t);
                if(t.relations != null) {

                    for (final Tree subTree : t.relations) {
                        traverse(subTree, visitor);
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
            }

            private void createPartition(PartitionNames name) {
                Partitions partitions = new Partitions(name.name());
                partitionMap.put(partitions.name,partitions);
            }

            public void modifyData(Tree tree) throws Exception {
                TreeTraversal t = new TreeTraversal();
                t.traverse(tree, new Visitor() {
                    @Override
                    public void visit(Tree t) {
                        System.out.println(Thread.currentThread().getName() + " Modifying data in this partition :- " + partitionMap.get(t.name).name);
                    }
                });
            }

            public void readData(Tree tree) throws Exception {

                readUnderLock(tree);

                TreeTraversal t = new TreeTraversal();
                t.traverse(tree, new Visitor() {
                    @Override
                    public void visit(Tree t) {
                        System.out.println(Thread.currentThread().getName() + " Reading Data from partition :- " + partitionMap.get(t.name).name);
                    }
                });
            }

            private void readUnderLock(Tree tree) throws Exception {
                TreeTraversal t = new TreeTraversal();
                final List list = new ArrayList<>();
                t.traverse(tree, new Visitor() {
                    @Override
                    public void visit(Tree t) {
                        //System.out.println(Thread.currentThread().getName() + " Reading Data from partition :- " + partitionMap.get(t.name).name);
                        list.add(t.name);
                    }
                });

                Collections.sort(list);
            }

            class Partitions{

                private String name;

                private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

                public Partitions(String name) {
                    this.name = name;
                }
            }
        }

        TreeTraversal treeTraversal = new TreeTraversal();
        treeTraversal.traverse(t, new Visitor() {
            @Override
            public void visit(Tree t) {
                System.out.println(t.name);
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
                    sleep(200);
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
                    sleep(200);
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
                    sleep(200);
                }
            }
        };

        new Thread(readUser).start();
        new Thread(readProduct).start();
        new Thread(regServices).start();

    }

    public static void sleep(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
        }
    }
}
