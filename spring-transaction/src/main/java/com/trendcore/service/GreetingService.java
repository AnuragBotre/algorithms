package com.trendcore.service;

import com.trendcore.model.Greeting;

import java.util.Collection;

/**
 * Created by Anurag
 */
public interface GreetingService {

    Collection<Greeting> findAll();

    Greeting findOne(Long id);

    Greeting create(Greeting greeting);

    Greeting update(Greeting greeting);

    void delete(Long id);

}
