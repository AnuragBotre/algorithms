package com.trendcore.sample.application;

public class HttpLayer {

    public String method1() {
        BusinessModule businessModule = new BusinessModule();
        String string = businessModule.getBusinessObject(1,"test");
        return string;
    }

}
