package com.trendcore.problems;

/**
 * Created by Anurag
 */
public class FibonanciSeries {

    /**
     * 0,1,1,2,3,5,8
     *
     * @param args
     */
    public static void main(String[] args) {

        int fn=1;
        int fn_1=0;
        //0,1,1,2,3,5,8

        int i = 0;

        while(i < 10){
            System.out.println(fn);
            int temp = fn;
            fn = fn + fn_1;
            fn_1 = temp;
            i++;
        }

    }


}
