package com.trendcore.proxy.example1;

import java.util.ArrayList;
import java.util.List;

public class InternetProxy implements Internet{

    Internet internet;

    private List bannedSites = new ArrayList();

    public InternetProxy() {
        this.internet = new InternetImpl();
        bannedSites.add("abc");
        bannedSites.add("pqr");
    }

    @Override
    public String connectTo(String host) {
        if(allowedAccess(host)){
            return internet.connectTo(host);
        }
        throw new RuntimeException("Access not allowed.");
    }

    private boolean allowedAccess(String host) {
        return !bannedSites.stream().filter(o -> o.equals(host)).findAny().isPresent();
    }
}
