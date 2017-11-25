package com.trendcore.generics;

import org.junit.Test;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Anurag
 */
public class ApplicationTest {

    @Test
    public void simpleGenerics() throws Exception {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);

        //Below line will give compilation error
        //List<Integer> list1 = new ArrayList<Objects>();


        //Below line will give compilation error
        //List<? extends Integer> list1 = new ArrayList<? extends Integer>();

        //This is allowed
        List<? super Number> list1 = new ArrayList<>();
        //We can add DataTypes which are of Type Numbers
        list1.add(1);
        list1.add(2.0);
        //This will give compilation error
        //Because Number class does not implement Comparable
        /*list1.add(new Comparable<Integer>() {
            @Override
            public int compareTo(Integer o) {
                return 0;
            }
        });*/
        //list1.add(new Object());
        list1.add(new Double(123));
        list1.add(new AtomicInteger(123));
        list1.add(new BigDecimal(123));
        list1.add(new BigInteger(new byte[10]));


        List<Number> list2 = new ArrayList<>();
        list2.add(1);
        //Below line will give compilation error
        //list2.add(new Object());
        list2.add(1.0);

        List<? extends Number> list3 = (List<? extends Number>) list1;
        //Below line will give compilation error
        //list3.add(1);
        Number number = list3.get(3);
        System.out.println(number);
    }

    @Test
    public void classLevelGenerics() throws Exception {
        /*Box<String> box = new Box<>();
        box.setObject("123");*/
    }

    @Test
    public void methodLevelGenerics() throws Exception {
        /*Box<String> box = new Box<>();
        box.doSomething(123,"123",new ArrayList<>());
        box.doSomething("123",new HashMap<>(),new ArrayList<>());*/
    }

    @Test
    public void methodLevelReturnGenercis() throws Exception {
        /*Box<String> box = new Box<>();
        Object something = box.getSomething(123);
        Object something1 = box.getSomething("123");*/
    }

    @Test
    public void methodLevelGenericsWithWildCards() throws Exception {
        /*Box<String> box = new Box<>();
        box.methodWithWildCardForNumbers(1.0);
        box.methodWithWildCardForNumbers(1);
        box.methodWithWildCardForNumbers(10L);*/
    }

    @Test
    public void getParamsFromMethodWithWildCards() throws Exception {
        /*Box<String> box = new Box<>();
        box.getObjectWithWildCard(123.0);*/
    }
}

