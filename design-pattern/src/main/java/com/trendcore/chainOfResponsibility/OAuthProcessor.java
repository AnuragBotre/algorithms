package com.trendcore.chainOfResponsibility;

public class OAuthProcessor implements AuthenticationProcessor{

    private AuthenticationProcessor next;

    @Override
    public void setNext(AuthenticationProcessor processor) {
        next = processor;
    }

    @Override
    public boolean authenticate(Credential credential) {

        if(credential instanceof OAuthCredential){
            return isValid(credential);
        }else{
            return next.authenticate(credential);
        }
    }

    private boolean isValid(Credential credential) {
        //logic to validate
        System.out.println("Credential Validated using OAuth Processor." );
        return false;
    }
}
