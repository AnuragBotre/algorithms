package com.trendcore.sample.application.approach1;

import java.util.stream.Stream;

public interface DAOSpecs {

    Stream getUsers();

    void insertUsers();

}
