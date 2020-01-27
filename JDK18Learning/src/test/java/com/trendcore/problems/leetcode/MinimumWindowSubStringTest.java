package com.trendcore.problems.leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

public class MinimumWindowSubStringTest {

    MinimumWindowSubString m = new MinimumWindowSubString();

    @Test
    public void input1() {
        String s = "ADOBECODEBANC", t = "ABC";
        String output = "BANC";
        act(s, t, output);
    }

    private void act(String s, String t, String output) {
        String s1 = m.minWindow(s, t);
        assertEquals(output,s1);
    }
}