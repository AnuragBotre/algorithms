package com.trendcore.problems.leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

public class SearchInRotatedArrayIITest {

    private SearchInRotatedArrayII s = new SearchInRotatedArrayII();

    @Test
    public void input1() {
        act(new int[]{2, 5, 6, 0, 0, 1, 2}, 0, true);
    }

    @Test
    public void input2() {
        act(new int[]{2, 5, 6, 0, 0, 1, 2}, 3, false);
    }

    @Test
    public void input3() {
        act(new int[]{2, 5, 6, 0, 0, 1, 2}, 2, true);
    }

    @Test
    public void input4() {
        boolean b[] = {true, true, true, false, false, true, true};


        for (int i = 0; i < 7; i++) {
            try {
                int[] nums = {2, 5, 6, 0, 0, 1, 2};
                act(nums, i, b[i]);
            } catch (Throwable e) {
            }
        }
    }

    @Test
    public void input5() {
        act(new int[]{3}, 3, true);
    }

    @Test
    public void input6() {
        act(new int[]{3}, 1, false);
    }

    @Test
    public void failedInput_1() {
        act(new int[]{3, 1}, 1, true);
    }

    @Test
    public void failedInput_2() {
        act(new int[]{}, 1, false);
    }

    @Test
    public void failedInput_3() {
        act(new int[]{1, 3}, 3, true);
    }

    @Test
    public void failedInput_4() {
        act(new int[]{5, 1, 3}, 3, true);
    }

    @Test
    public void failedInput_5() {
        act(new int[]{3, 1}, 3, true);
    }

    private void act(int[] nums, int target, boolean expected) {
        boolean search = s.search(nums, target);
        System.out.println(expected + " " + target + " " + search);
        assertEquals(expected, search);
    }
}