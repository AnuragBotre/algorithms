package com.trendcore;

import org.junit.Test;

import java.util.Random;

/**
 * Created by Anurag
 */

public class ThreadTest {


    @Test
    public void setDeamonThread() throws Exception {
        for(int i = 0 ; i < 200 ; i++){
            System.out.println(new Random().nextInt(5));
        }
    }
}
