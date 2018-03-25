package com.trendcore.sql;

import java.util.Arrays;
import java.util.function.Consumer;

public class Email {

    public static Enum ID = Address.STREET;

    public static void main(String[] args) {
        Arrays.asList(Email.class.getFields()).forEach(field -> System.out.println(field));

        Email e = new Email();
        e.get(Email.ID);
    }

    private void get(String name) {

    }

    private void get(Enum address) {
        System.out.println(address);
    }


}
