package com.trendcore.problems.leetcode;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class RemoveDuplicatesFromSortedArrayIITest {

    @Test
    public void example1Input() {
        int nums[] = {1, 1, 1, 2, 2, 3};
        act(nums, 5);
    }

    @Test
    public void exampleInput2() {
        int nums[] = {0, 0, 1, 1, 1, 1, 2, 3, 3};
        act(nums, 7);
    }

    @Test
    public void exampleInput3() {
        int nums[] = {1, 1, 1, 1, 1, 1, 1, 1, 2};
        act(nums, 3);
    }

    @Test
    public void exampleInput4() {
        int nums[] = {1, 1, 1, 2, 2, 2, 3, 3};
        act(nums, 6);
    }

    @Test
    public void singleInput() {
        int nums[] = {1};
        act(nums, 1);
    }

    @Test
    public void sameDoubleInput() {
        int nums[] = {1, 1};
        act(nums, 2);
    }

    @Test
    public void sameTripleInput() {
        int nums[] = {1, 1, 1, 1};
        act(nums, 2);
    }

    @Test
    public void normalArray() {
        int nums[] = {1, 2};
        act(nums, 2);
    }

    @Test
    public void lastNumber2Times() {
        int nums[] = {1, 2, 2};
        act(nums, 3);
    }

    @Test
    public void lastNumber3Times() {
        int nums[] = {1, 2, 2, 2};
        act(nums, 3);
    }

    @Test
    public void lastNumberMultipleTimes() {
        int nums[] = {1, 2, 2, 2, 2, 2, 2, 2};
        act(nums, 3);
    }


    @Test
    public void random1() {
        int nums[] = {1, 2, 2, 2, 2, 3};
        act(nums, 4);
    }

    @Test
    public void failedInput1() {
        int nums[] = {1, 1, 1, 2, 2, 3};
        act(nums, 5);
    }

    @Test
    public void failedInput2() {
        int nums[] = {0, 1, 2, 2, 2, 2, 2, 3, 4, 4, 4};
        int[] ints = {0, 1, 2, 2, 3, 4, 4};
        act(nums, ints.length);
    }

    @Test
    public void failedInputForApproach2_1() {
        int nums[] = {1, 1, 1, 2, 2, 3};
        int[] ints = {1, 1, 2, 2, 3};
        act(nums, ints.length);
    }

    private void act(int[] nums, int expected) {
        RemoveDuplicatesFromSortedArrayII r = new RemoveDuplicatesFromSortedArrayII();
        int actual = r.removeDuplicates(nums);
        System.out.println();
        System.out.print("Length :- " + actual + " :: ");
        Arrays.stream(nums).forEach(value -> System.out.print(" " + value));
        assertEquals(expected, actual);
    }
}