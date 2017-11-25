package com.trendcore.service;

import com.trendcore.model.Greeting;
import com.trendcore.repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created by Anurag
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
public class GreetingServiceImpl implements GreetingService {

    @Autowired
    GreetingRepository greetingRepository;

    @Override
    public Collection<Greeting> findAll() {
        return greetingRepository.findAll();
    }

    @Override
    public Greeting findOne(Long id) {
        return greetingRepository.findOne(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Greeting create(Greeting greeting) {

        if (greeting.getId() != null) {
            return null;
        }

        Greeting savedGreeting = greetingRepository.save(greeting);

        if (greeting.getId() == 4L) {
            throw new RuntimeException("Roll me back.");
        }

        return savedGreeting;
    }

    @Override
    public Greeting update(Greeting greeting) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
