package com.trendcore.problems.leetcode;

import org.junit.Test;

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

    private void act(int[] nums, int expected) {
        RemoveDuplicatesFromSortedArrayII r = new RemoveDuplicatesFromSortedArrayII();
        int actual = r.removeDuplicates(nums);
        assertEquals(expected, actual);
    }
}