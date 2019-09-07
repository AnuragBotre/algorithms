package com.trendcore.problems.leetcode;

/**
 * https://leetcode.com/problems/merge-sorted-array/
 * <p>
 * 88. Merge Sorted Array
 * <p>
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 * <p>
 * Note:
 * <p>
 * The number of elements initialized in nums1 and nums2 are m and n respectively.
 * You may assume that nums1 has enough space (size that is greater or equal to m + n)
 * to hold additional elements from nums2.
 * Example:
 * <p>
 * Input:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * <p>
 * Output: [1,2,2,3,5,6]
 */
public class MergeSortedArray {

    class Queue {
        int current;
        int end;
        int arr[];

        int start;

        public Queue(int start, int[] arr) {
            this.current = start;
            this.arr = arr;
            this.start = start;
        }

        void insert(int i) {
            arr[current] = i;
            current++;
        }

        int getFirstElement() {
            return arr[start];
        }

        int swapFirstElement(int ele) {
            int i = arr[start];
            arr[start] = ele;
            return i;
        }

        public boolean isEmpty() {
            return current == start;
        }

        public int size() {
            return current - start;
        }
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {


        int i = 0;

        int j = 0;

        int queueStart = m;

        while (i < nums1.length && queueStart < nums1.length) {

            int[] process = process(nums1, m, nums2, n, i, j, queueStart);
            i = process[0];
            j = process[1];
            m = process[2];
            queueStart = queueStart + m;
        }

    }

    public int[] process(int[] nums1, int m, int[] nums2, int n, int i, int j, int queueStart) {

        //result cnt will start from i
        int k = i;
        int result[] = nums1;
        return processInternal(nums1, m + i, nums2, n, i, j, queueStart, k, result);
    }

    public int[] processInternal(int[] nums1, int m, int[] nums2, int n, int i, int j, int queueStart, int k, int[] result) {
        Queue queue = new Queue(queueStart, result);
        for (; i < m || j < n; ) {

            if (!queue.isEmpty()) {

                if (k >= m) {
                    break;
                }

                int elementFromQueue = queue.getFirstElement();
                if (j < n) {
                    if (elementFromQueue < nums2[j]) {
                        int t = nums1[k];
                        nums1[k] = elementFromQueue;
                        queue.swapFirstElement(t);
                        k++;
                    } else {
                        int t = nums1[k];
                        nums1[k] = nums2[j];
                        j++;
                        k++;

                        queue.insert(t);
                    }
                } else {
                    int t = nums1[k];
                    nums1[k] = elementFromQueue;
                    queue.swapFirstElement(t);
                    k++;
                }
            } else {

                if (i < m) {


                    if (nums1[i] <= nums2[j]) {
                        i++;
                        k++;
                    } else {
                        int t = nums1[k];
                        nums1[k] = nums2[j];
                        k++;
                        j++;

                        queue.insert(t);
                    }
                } else {
                    nums1[k] = nums2[j];
                    k++;
                    j++;
                }
            }
        }

        return new int[]{k, j, queue.size()};
    }

    private void print(int[] nums1) {
        for (int i = 0; i < nums1.length; i++) {
            System.out.print(" " + nums1[i]);
        }
    }

    public void incompletePrevApproach(int[] nums1, int m, int[] nums2) {
        int j = 0;
        int i = 0;

        int emptySpaceStart = m;

        int temp = 0;

        for (; i < nums1.length; ) {

            //If elements are present in empty space
            if (emptySpaceStart != m) {

                //if element in empty space last ele is less than num 2 then swap with num2
                if (nums1[emptySpaceStart - 1] < nums2[j]) {
                    int t = nums2[j];
                    nums2[j] = nums1[emptySpaceStart - 1];
                    nums1[emptySpaceStart - 1] = t;
                } else {
                    if (nums1[i] > nums2[j]) {
                        //then num1 -> i needs to be moved to empty space
                        //num2 -> j element  moved to num1 -> i
                        int t = nums1[i];
                        nums1[i] = nums2[j];
                        i++;
                        j++;

                        nums1[emptySpaceStart] = t;
                        emptySpaceStart++;

                    } else {
                        i++;
                    }
                }

            } else {
                if (nums1[i] > nums2[j]) {
                    //then num1 -> i needs to be moved to empty space
                    //num2 -> j element  moved to num1 -> i
                    int t = nums1[i];
                    nums1[i] = nums2[j];
                    i++;
                    j++;

                    nums1[emptySpaceStart] = t;
                    emptySpaceStart++;

                } else {
                    i++;
                }
            }

        }

        //do we need to sort between i and last elem.
    }
}
