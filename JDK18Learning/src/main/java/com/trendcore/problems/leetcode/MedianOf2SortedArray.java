package com.trendcore.problems.leetcode;

/**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * <p>
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * <p>
 * Example 1:
 * <p>
 * nums1 = [1, 3]
 * nums2 = [2]
 * <p>
 * The median is 2.0
 * <p>
 * <p>
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * <p>
 * The median is (2 + 3)/2 = 2.5
 *
 * Median Defination
 *
 * Input : ar1[] = {-5, 3, 6, 12, 15}
 ar2[] = {-12, -10, -6, -3, 4, 10}
 The merged array is :
 ar3[] = {-12, -10, -6, -5 , -3,
 3, 4, 6, 10, 12, 15}
 Output : The median is 3.

 Input : ar1[] = {2, 3, 5, 8}
 ar2[] = {10, 12, 14, 16, 18, 20}
 The merged array is :
 ar3[] = {2, 3, 5, 8, 10, 12, 14, 16, 18, 20}
 if the number of the elements are even,
 so there are two middle elements,
 take the average between the two :
 (10 + 12) / 2 = 11.
 Output : The median is 11.
 */
public class MedianOf2SortedArray {

    public static void main(String[] args) {

        MedianOf2SortedArray m = new MedianOf2SortedArray();

        /*int nums1[] = new int[]{-5, 3, 6, 12, 15};
        int nums2[] = new int[]{-12, -10, -6, -3, 4, 10};*/

        //int nums1[] = new int[]{2, 3, 5, 8};
        //int nums2[] = new int[]{10, 12, 14, 16, 18, 20};

        int nums1[] = new int[]{1,2};
        int nums2[] = new int[]{3,4};

        //-12   -10     -6      -5      -3      3       4       6

        double medianSortedArrays = m.findMedianSortedArrays(nums1, nums2);
        System.out.println("Median of 2 sorted array :- " + medianSortedArrays);
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int totalLength = nums1.length + nums2.length;
        boolean isOdd = true;
        if(totalLength % 2 == 0){
            isOdd = false;
        }

        int num1Pointer = 0;
        int num2Pointer = 0;

        int maxTraversal = traversalLength(totalLength, isOdd);

        int i = 0;

        int mergedArray[] = new int[maxTraversal];

        while(i < maxTraversal){

            //getNumberFromFirstArr

            if (num1Pointer < nums1.length) {
                if (num2Pointer < nums2.length) {
                    if (nums1[num1Pointer] < nums2[num2Pointer]) {
                        mergedArray[i] = nums1[num1Pointer];
                        num1Pointer++;
                    }else{
                        mergedArray[i] = nums2[num2Pointer];
                        num2Pointer++;
                    }
                }else{
                    mergedArray[i] = nums1[num1Pointer];
                    num1Pointer++;
                }

            }else{
                if(num2Pointer < nums2.length){
                    mergedArray[i] = nums2[num2Pointer];
                    num2Pointer++;
                }
            }

            i++;
        }

        if (isOdd) {
            return mergedArray[i-1];
        }else{
            double d = mergedArray[i-1] + mergedArray[i-2];
            return d/2;
        }
    }

    private int traversalLength(int totalLength, boolean isOdd) {
        return (totalLength / 2) + 1;
    }

    private boolean finished(int num1Pointer, int[] arr) {
        return num1Pointer < arr.length;
    }


}
