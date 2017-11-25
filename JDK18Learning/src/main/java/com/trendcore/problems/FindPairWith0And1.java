package com.trendcore.problems;

/**
 * Created by Anurag
 */
public class FindPairWith0And1 {

    /**
     * {-4,-3,-2,-1,0,1,3,4,7}
     *
     * @param args
     */
    public static void main(String[] args) {
        int arr[] = {-4, -3, -2, -1, 0, 1, 4, 7};

        System.out.println(-4 % 7);

        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (arr[i] + arr[j] == 0 && i != j) {
                    System.out.println(i+" "+j + " " + arr[i] + " " + arr[j]);
                }
            }
        }
    }

}
