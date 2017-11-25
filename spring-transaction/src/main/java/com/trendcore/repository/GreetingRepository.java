package com.trendcore.repository;

import com.trendcore.model.Greeting;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Anurag
 */
public interface GreetingRepository extends JpaRepository<Greeting,Long>{


}
