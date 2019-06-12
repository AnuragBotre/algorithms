package com.trendcore.composite.example2;

public class InputElement implements Control{

    private String id;

    public InputElement(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public void init() {
        System.out.println("init Input Element..");
    }

    @Override
    public void render() {
        System.out.println("render Input Element..");
    }

    @Override
    public void destroy() {
        System.out.println("destroy Input Element..");
    }

    @Override
    public String getType() {
        return "input";
    }
}
