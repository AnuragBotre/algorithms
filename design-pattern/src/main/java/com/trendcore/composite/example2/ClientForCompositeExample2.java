package com.trendcore.composite.example2;

public class ClientForCompositeExample2 {

    public static void main(String[] args) {
        Page page = new Page("page1");

        page.add(new Anchor("link"));

        Form login = new Form("login");
        login.add(new InputElement("username"));
        login.add(new InputElement("password"));

        page.add(login);

        page.init();
        page.render();
        page.destroy();
    }

}
