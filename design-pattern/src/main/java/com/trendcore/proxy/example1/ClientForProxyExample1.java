package com.trendcore.proxy.example1;

public class ClientForProxyExample1 {

    public static void main(String[] args) {
        Internet internet = new InternetProxy();

        try {
            System.out.println(internet.connectTo("abc"));
            System.out.println(internet.connectTo("pqr"));
        }catch (Exception e){

        }
        System.out.println(internet.connectTo("google"));
        System.out.println(internet.connectTo("microsoft"));
    }

}
