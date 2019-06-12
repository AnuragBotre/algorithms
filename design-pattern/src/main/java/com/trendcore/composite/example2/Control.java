package com.trendcore.composite.example2;

public interface Control {

    String getId();

    void init();

    void render();

    void destroy();

    String getType();
}
