package com.trendcore.problems;

import java.util.Arrays;

/**
 * Created by Anurag
 */
public class Segregate0And1 {

    /**
     * Segregate 0s and 1s in an array
     * Input array   =  [0, 1, 0, 1, 0, 0, 1, 1, 1, 0]
     * Output array =  [0, 0, 0, 0, 0, 1, 1, 1, 1, 1]
     *
     * @param args
     */
    public static void main(String[] args) {
        approach1();
    }

    private static void approach1() {
        Object[] arr = Arrays.asList(0, 1, 0, 1, 0, 0, 1, 1, 1, 0).toArray();

        printArray(arr);

        System.out.println("-");

        int indexFor1 = -1;

        int indexFor0 = -1;

        for (int i = 0; i < arr.length; i++) {
            if(indexFor0 == -1 && (Integer)arr[i] == 0){
                indexFor0 = i;
            }

            if((Integer)arr[i] == 1){
                indexFor1 = i;
            }

            if((Integer)arr[i] == 0 && indexFor0 < indexFor1){
                if(indexFor0+1<arr.length){
                    arr[indexFor0+1] = 0;
                    arr[i] = 1;
                    indexFor0++;
                }
            }
        }

        printArray(arr);
    }

    private static void printArray(Object[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

}
