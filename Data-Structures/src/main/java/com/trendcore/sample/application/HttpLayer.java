package com.trendcore.sample.application;

import com.trendcore.Profile;

public class HttpLayer {

    @Profile(category = "HTTP_METHOD")
    public String method1() {
        BusinessModule businessModule = new BusinessModule();
        String string = businessModule.getBusinessObject(1,"test");
        return string;
    }

}
