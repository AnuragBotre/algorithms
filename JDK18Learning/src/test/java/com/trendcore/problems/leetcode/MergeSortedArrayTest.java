package com.trendcore.problems.leetcode;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class MergeSortedArrayTest {

    MergeSortedArray mergeSortedArray = new MergeSortedArray();

    @Test
    public void testWithExampleInput() {
        act(new int[]{1, 2, 3, 0, 0, 0}, 3,
                new int[]{2, 5, 6}, 3, new int[]{1, 2, 2, 3, 5, 6});
    }

    @Test
    public void failedInput_1() {
        act(new int[]{4, 5, 6, 0, 0, 0}, 3,
                new int[]{1, 2, 3}, 3, new int[]{1, 2, 3, 4, 5, 6});
    }

    @Test
    public void failedInput_2() {

        act(new int[]{1, 2, 4, 5, 6, 0}, 5,
                new int[]{3}, 1, new int[]{1, 2, 3, 4, 5, 6});
    }

    @Test
    public void inputWith0s() {

        act(new int[]{-1, 0, 0, 3, 3, 3, 0, 0, 0}, 6,
                new int[]{1, 2, 2}, 3, new int[]{-1, 0, 0, 1, 2, 2, 3, 3, 3});
    }

    @Test
    public void failedInput_3() {
        act(new int[]{1, 2, 3, 0, 0, 0}, 3,
                new int[]{2, 5, 6}, 3, new int[]{1, 2, 2, 3, 5, 6});
    }

    public void act(int[] nums1, int m, int[] nums2, int n, int expected[]) {
        mergeSortedArray.merge(nums1, m, nums2, n);
        assertArrayEquals(expected, nums1);
    }
}