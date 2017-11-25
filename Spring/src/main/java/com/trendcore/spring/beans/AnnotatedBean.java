package com.trendcore.spring.beans;

import com.trendcore.spring.aop.Loggable;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by Anurag
 */
@Component
public class AnnotatedBean {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void exceptionMethod() throws Exception {
        throw new Exception();
    }

    @Loggable
    public void businessMethodWithLoggingAspect(){

    }

}
