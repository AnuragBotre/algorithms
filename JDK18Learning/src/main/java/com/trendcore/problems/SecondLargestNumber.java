package com.trendcore.problems;

/**
 * Created by Anurag
 */
public class SecondLargestNumber {

    public static void main(String[] args) {
        int arr[] = {1, 1, 1, 1, 2, 1, 1, 1};

        findSecondLargest(arr);
    }

    private static void findSecondLargest(int[] arr) {
        int firstMax = -1;
        int secondMax = -1;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > firstMax) {
                secondMax = firstMax;
                firstMax = arr[i];
            } else if (arr[i] > secondMax && arr[i] != firstMax){
                secondMax = arr[i];
            }

        }

        System.out.println(firstMax + " " + secondMax);
    }

}
