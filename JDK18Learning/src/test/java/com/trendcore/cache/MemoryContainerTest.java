package com.trendcore.cache;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MemoryContainerTest {

    interface Cacheable extends Cloneable{

        Object getPrimaryKey();

        Object getShallowCopy();

        Object[] getRelatedFields();
    }

    class Role{
        Integer id;
        String name;
        Set<User> users;
    }

    class Group{
        String id;
        Set<User> users;
    }

    class UserProfile{
        String email;
        String phoneNo;
        User user;
    }

    class OnetoOne{

    }

    class OneToMany{

    }

    class ManyToMany{

    }

    class User implements Cacheable{
        Integer id;
        String loginId;
        Boolean enabled;
        Boolean accountLocked;

        UserProfile userProfile = new UserProfile();
        Set<Role> roles = new HashSet<>();
        Set<Group> groups = new HashSet<>();

        class RelationShips{
            OnetoOne userProfile;
            ManyToMany roles;
            ManyToMany groups;
        }

        @Override
        public Object getPrimaryKey() {
            return loginId;
        }

        @Override
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        @Override
        public Object getShallowCopy() {
            try {
                return clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException("Error occurred while creating shallow copy.",e);
            }
        }

        @Override
        public Object[] getRelatedFields() {
            return new Object[]{ userProfile,roles,groups};
        }
    }

    class Container{

        class Key{
            Object key;

            Key(Object key){
                this.key = key;
            }

            @Override
            public int hashCode() {
                return key.hashCode();
            }

            @Override
            public boolean equals(Object obj) {
                return key.equals(obj);
            }
        }

        class Value{

            ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);

            volatile Object content;

            long timestamp;

            Value(Object content){
                this.content = content;
                timestamp = System.currentTimeMillis();
            }
        }

        Map<Key,Value> c;

        public Container(){
            c = new HashMap<>();
        }

        public void insert(Cacheable cacheable) {
            Object primaryKey = cacheable.getPrimaryKey();

            Key key = new Key(primaryKey);
            Value value = getValueFromMap(key);
            if (value != null && value.content != null) {
                //update case
                //need to check how to handle this.
            } else {
                //new case
                Object clone = cacheable.getShallowCopy();
                value = new Value(cacheable);
                setValue(key,value);

                processRelatedFields(cacheable);
            }

        }

        private void processRelatedFields(Cacheable cacheable) {
            Object[] relatedFields = cacheable.getRelatedFields();

            for(Object object : relatedFields){
                if(object instanceof Collection){
                    System.out.println("Collection ");
                }else{
                    System.out.println("Here");
                }
            }
        }

        private Value getValueFromMap(Key key) {
            return c.get(key);
        }

        private void setValue(Key key,Value value) {
            c.put(key,value);
        }

    }


    @Test
    public void createStructure() throws Exception {
        Container c = new Container();
        User user = new User();

        user.id = 1;
        user.loginId = "admin";
        user.enabled = true;
        user.accountLocked = true;

        c.insert(user);
    }

    @Test
    public void testCloneMethod() throws Exception {

        User user = new User();
        user.id = 1;
        user.loginId = "admin";
        user.enabled = true;
        user.accountLocked = true;

        user.userProfile = new UserProfile();

        user.roles = new HashSet<>();
        Role role = new Role();
        role.id = 1;
        role.name = "Admin";
        role.users = new HashSet<>();

        user.roles.add(role);
        role.users.add(user);

        System.out.println(user);
        System.out.println(user.userProfile);
        System.out.println(user.roles);

        User shallowCopy = (User) user.getShallowCopy();

        System.out.println("Only this is changed." + shallowCopy);
        System.out.println(shallowCopy.userProfile);
        System.out.println(shallowCopy.roles);

        Assert.assertEquals(user.roles.hashCode(),shallowCopy.roles.hashCode());
        Assert.assertEquals(user.userProfile.hashCode(),shallowCopy.userProfile.hashCode());

    }
}
