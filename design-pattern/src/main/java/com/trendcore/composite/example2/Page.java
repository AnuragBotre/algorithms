package com.trendcore.composite.example2;

import java.util.ArrayList;
import java.util.List;

public class Page implements Control{

    private String id;

    List<Control> controls = new ArrayList<>();

    public Page(String id) {
        this.id = id;
    }

    @Override
    public String getType() {
        return "div";
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void init() {
        controls.forEach(Control::init);
    }

    @Override
    public void render() {
        controls.forEach(Control::render);
    }

    @Override
    public void destroy() {
        controls.forEach(Control::destroy);
    }

    public void add(Control control){
        controls.add(control);
    }
}
