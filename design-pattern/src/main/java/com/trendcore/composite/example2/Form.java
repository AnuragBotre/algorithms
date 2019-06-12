package com.trendcore.composite.example2;

import java.util.ArrayList;
import java.util.List;

public class Form implements Control{

    private String id;

    List<Control> formElements = new ArrayList<>();

    public Form(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void init() {
        System.out.println("Initializing form..");
        formElements.forEach(Control::init);
    }

    @Override
    public void render() {
        System.out.println("rendering form");
        formElements.forEach(Control::render);
    }

    @Override
    public void destroy() {
        System.out.println("Destroying form");
    }

    @Override
    public String getType() {
        return "Form";
    }

    public void add(Control control){

        switch (control.getType()){
            case "input":
            case "hidden":
            case "password":
                formElements.add(control);
                break;
        }

    }
}
