package com.trendcore.composite.example2;

public class Anchor implements Control{

    private String id;

    public Anchor(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void init() {
        System.out.println("init link");
    }

    @Override
    public void render() {
        System.out.println("render link");
    }

    @Override
    public void destroy() {
        System.out.println("destroy link");
    }

    @Override
    public String getType() {
        return "anchor";
    }
}
