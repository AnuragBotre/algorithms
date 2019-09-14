package com.trendcore.problems.leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

public class SortColorsTest {

    SortColors sortColors = new SortColors();

    @Test
    public void ip_1() {
        act(new int[]{2, 0, 2, 1, 1, 0}, new int[]{0, 0, 1, 1, 2, 2});
    }

    @Test
    public void ip_2() {
        act(new int[]{2, 2, 2, 1, 1, 1}, new int[]{1, 1, 1, 2, 2, 2});
    }

    public void act(int[] nums, int[] expecteds) {
        sortColors.sortColors(nums);
        assertArrayEquals(expecteds, nums);
    }
}