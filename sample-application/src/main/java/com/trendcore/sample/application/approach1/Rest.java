package com.trendcore.sample.application.approach1;

import com.sun.deploy.net.HttpRequest;

import java.util.List;
import java.util.stream.Stream;

public class Rest {

    public Stream getUsers(HttpRequest request) {
        Business b = new Business();
        return b.getUsers(request);
    }

}
