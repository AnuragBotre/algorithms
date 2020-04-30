package com.trendcore;

import java.lang.annotation.Annotation;

public class PassingArguments {

    @Profile
    public void method1(String a,String b){
        Profiler.pushMethod("PassingArguments","profileMe",System.currentTimeMillis(),"java.lang.String,java.lang.String","HTTP_REQUEST",a,b);
    }

}
