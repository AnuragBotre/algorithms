package com.trendcore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StreamApp {

    public static class User{
        String name;

        public User(String s) {
            name = s;
        }
    }

    public static void main(String[] args) {
        List<User> users = new ArrayList<>();

        users.add(new User("1"));
        users.add(new User("2"));
        users.add(new User("3"));
        users.add(new User("4"));

        //Pre
        /*for(User user : users){
            System.out.println("Doing Pre on " + user.name);
            user.name = "some_modification" + user.name;
        }



        //Save user
        for(User user : users){
            System.out.println("Save User :- " + user.name);
        }


        //Post
        for(User user : users){
            System.out.println(" Post  On :- " + user.name);
        }*/


        List batch = new ArrayList();

        for(User user : users){
            System.out.println("Doing Pre on " + user.name);
            System.out.println("Save User :- " + user.name);
            batch.add(user);
            //System.out.println(" Post  On :- " + user.name);
        }
        batch.clear();



    }

}
