package com.trendcore.spring.rest;

import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Anurag
 */
@RestController
public class FirstRestController {

    public String hello(){
        return "hello";
    }

}
