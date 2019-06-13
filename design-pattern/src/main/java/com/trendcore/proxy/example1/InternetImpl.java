package com.trendcore.proxy.example1;

public class InternetImpl implements Internet{

    @Override
    public String connectTo(String host) {
        return host+".com";
    }
}
