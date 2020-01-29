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

    @Test
    public void intput2() {
        String s = "ADOBECODEBANC", t = "AD";
        String output = "AD";
        act(s, t, output);
    }

    @Test
    public void intput3() {
        String s = "ADOBECODEBANC", t = "XY";
        String output = "";
        act(s, t, output);
    }

    @Test
    public void input4() {
        String s = "ADOBECODEBANC", t = "DBC";
        String output = "DOBEC";
        act(s, t, output);
    }

    @Test
    public void failedInput_1() {
        String s = "aa", t = "aa";
        String output = "aa";
        act(s, t, output);
    }

    @Test
    public void failedInput_2() {
        String s = "bdab", t = "ab";
        String output = "ab";
        act(s, t, output);
    }

    private void act(String s, String t, String output) {
        String s1 = m.minWindow(s, t);
        assertEquals(output,s1);
    }
}