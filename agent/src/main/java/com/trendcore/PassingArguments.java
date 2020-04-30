package com.trendcore;

import java.lang.annotation.Annotation;
import java.util.Date;

public class PassingArguments {

    @Profile
    public void method1(String a, String b, String c, int d, long e, float f, Date date){
        Profiler.pushMethod("PassingArguments","profileMe",System.currentTimeMillis(),"java.lang.String,java.lang.String","HTTP_REQUEST",a,b,c,d,e,f,date);



    }

}
