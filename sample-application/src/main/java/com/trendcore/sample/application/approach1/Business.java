package com.trendcore.sample.application.approach1;

import java.util.stream.Stream;

public class Business {

    public Stream getUsers(Object object) {
        DAO dao = new DAO();
        return dao.getUsers();
    }
}
