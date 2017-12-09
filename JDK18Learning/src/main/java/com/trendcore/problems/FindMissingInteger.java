package com.trendcore.problems;

import java.util.Arrays;

/**
 * Created by Anurag
 */
public class FindMissingInteger {

    /**
     * Write a function:
     * <p>
     * class Solution { public int solution(int[] A); }
     * that, given an array A of N integers, returns the smallest positive integer (greater than 0)
     * that does not occur in A.
     * For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5.
     * Given A = [1, 2, 3], the function should return 4.
     * Given A = [−1, −3], the function should return 1.
     * <p>
     * Assume that:
     * <p>
     * N is an integer within the range [1..100,000];
     * each element of array A is an integer within the range [−1,000,000..1,000,000].
     * <p>
     * Complexity:
     * expected worst-case time complexity is O(N);
     * expected worst-case space complexity is O(N), beyond input storage
     * (not counting the storage required for input arguments).
     *
     * @param args
     */

    public static void main(String[] args) {
        //int arr[] = {1,3,6,4,1,2};
        //int arr[] = {5,1,0,-1};
        int arr[] = {-1,-2};
        //int arr[] = {6,7,1,3,4};
        //int arr[] = {1,2,3};
        //int arr[] = {1,2,3,-1};

        sortArray(arr);

        int output = 1;

        for(int i = 0 ; i < arr.length-1 ; i++){
            //System.out.println(arr[i]);
            if(arr[i+1] != arr[i]+1 && arr[i+1] != arr[i] && arr[i] > 0){
                output = arr[i]+1;
                break;
            }else if(i == arr.length-2 && arr[i+1] > 0){
                output = arr[i+1]+1;
                break;
            }
        }

        System.out.println(output);
    }

    private static void sortArray(int[] arr) {
        for(int i = 0 ; i < arr.length ; i++){
            for(int j = i ; j < arr.length ; j++){
                if(arr[i] > arr[j]){
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }


}
