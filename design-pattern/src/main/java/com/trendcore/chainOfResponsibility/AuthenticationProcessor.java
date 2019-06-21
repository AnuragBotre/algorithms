package com.trendcore.chainOfResponsibility;

public interface AuthenticationProcessor {

    void setNext(AuthenticationProcessor processor);

    boolean authenticate(Credential credential);

}
