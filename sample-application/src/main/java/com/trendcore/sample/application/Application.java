package com.trendcore.sample.application;

public class Application {

    private String databaseType;

    public Application(String databaseType) {
        this.databaseType = databaseType;
    }

    public static void register() {

    }

    public static Module defineModule(String module1) {
        return new Module();
    }
}
