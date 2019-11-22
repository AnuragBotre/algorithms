package com.trendcore;

import org.junit.Test;

public class CompositeFunctionTest {

    @Test
    public void composeFunction() {
        CompositeFunction<String,Integer> function1 = s -> Integer.parseInt(s);

        CompositeFunction<Integer,Integer> function2 = integer -> integer + 1;

        CompositeFunction<String, Integer> compose = function2.compose(function1);

        Integer apply = compose.apply("1");

        System.out.println(apply);
    }
}
