package com.trendcore.spring.boot;

import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Anurag
 */
@RestController
public class RestControllerWithSamePackage {


    public String get(){
        return "Hello";
    }

}
