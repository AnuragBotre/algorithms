package com.trendcore.problems.leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

public class MinimumWindowSubStringTest {

    MinimumWindowSubString m = new MinimumWindowSubString();

    @Test
    public void input1() {
        String s = "ADOBECODEBANC", t = "ABC";
        String expected = "BANC";
        act(s, t, expected);
    }

    @Test
    public void intput2() {
        String s = "ADOBECODEBANC", t = "AD";
        String expected = "AD";
        act(s, t, expected);
    }

    @Test
    public void intput3() {
        String s = "ADOBECODEBANC", t = "XY";
        String expected = "";
        act(s, t, expected);
    }

    @Test
    public void input4() {
        String s = "ADOBECODEBANC", t = "DBC";
        String expected = "DOBEC";
        act(s, t, expected);
    }

    @Test
    public void failedInput_1() {
        String s = "aa", t = "aa";
        String expected = "aa";
        act(s, t, expected);
    }

    @Test
    public void failedInput_2() {
        String s = "bdab", t = "ab";
        String expected = "ab";
        act(s, t, expected);
    }

    @Test
    public void failedInput_3() {
        String s = "a", t = "aa";
        String expected = "";
        act(s, t, expected);
    }

    @Test
    public void failedInput_4() {
        String s = "acbbaca", t = "aba";
        String expected = "baca";
        act(s, t, expected);
    }



    private void act(String s, String t, String expected) {
        String s1 = m.minWindow(s, t);
        assertEquals(expected,s1);
    }
}