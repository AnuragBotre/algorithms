package com.trendcore.chainOfResponsibility;

public class UsernamePasswordProcessor implements AuthenticationProcessor{

    private AuthenticationProcessor next;

    @Override
    public void setNext(AuthenticationProcessor processor) {
        next = processor;
    }

    @Override
    public boolean authenticate(Credential credential) {
        if(credential instanceof UsernamePassword){
            return isValid(credential);
        }else{
            return next.authenticate(credential);
        }
    }

    private boolean isValid(Credential credential) {

        System.out.println("Credential Validated using Username password." );
        //validate credential
        return false;
    }
}
