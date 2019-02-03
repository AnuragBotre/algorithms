package com.trendcore.sample.application;

import com.trendcore.sample.application.approach1.DAOSpecs;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Module {

    private Map<String, Function> map = new HashMap<>();

    private String baseUrl;

    public <T, R> void serve(String s, Function<T, R> function) {
        map.put(s, function);
    }

    public void baseUrl(String s) {
        baseUrl = s;
    }

    public Map<String, Function> getMap() {
        return map;
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
