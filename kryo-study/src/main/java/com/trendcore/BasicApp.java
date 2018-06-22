package com.trendcore;

import com.esotericsoftware.kryo.Kryo;

import java.util.HashSet;
import java.util.Set;

public class BasicApp {

    public static void main(String[] args) {
        BasicApp basicApp = new BasicApp();
        basicApp.start();
    }

    private void start() {
        User user = new User();
        user.setId(1);
        user.setRoles(new HashSet<>());
        Role r = new Role();
        r.id = 1;
        r.user = new HashSet<>();
        r.user.add(user);
        user.roles.add(r);
        UserProfile userProfile = new UserProfile();
        userProfile.email = "test@123.com";
        user.userProfile = userProfile;

        Kryo kryo = new Kryo();
        kryo.register(User.class);
        kryo.register(HashSet.class);
        kryo.register(Role.class);
        kryo.register(UserProfile.class);
        User copy = kryo.copy(user);

        System.out.println(user.hashCode());
        System.out.println(copy.hashCode());
    }

}
