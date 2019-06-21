package com.trendcore.chainOfResponsibility;

public class ClientForChainOfResponsibility {

    public static void main(String[] args) {
        AuthenticationProcessor authenticationProcessor = new UsernamePasswordProcessor();
        authenticationProcessor.setNext(new OAuthProcessor());

        authenticationProcessor.authenticate(new UsernamePassword());
        authenticationProcessor.authenticate(new OAuthCredential());
    }

}
