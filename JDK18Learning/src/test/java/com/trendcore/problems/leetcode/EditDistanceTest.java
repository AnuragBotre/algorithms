package com.trendcore.problems.leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

public class EditDistanceTest {

    EditDistance editDistance = new EditDistance();

    @Test
    public void example_1() {
        act("horse", "ros", 3);
    }

    @Test
    public void example_2() {
        act("intention", "execution", 5);
    }

    @Test
    public void example_3() {

        //abcdef
        //dxyzef

        act("abcdef", "dxyzef", 4);
    }

    @Test
    public void example_4() {
        act("spartan", "part", 3);
    }

    @Test
    public void time_limit_exceeded() {
        act("abcdxabcde","abcdeabcdx",2);
    }

    public void act(String word1, String word2, int expected) {
        int i = editDistance.minDistance(word1, word2);
        assertEquals(expected,i);
    }

    @Test
    public void replaceChar() {
        String s = editDistance.replaceChar("abcd", 2, "efghi");
        System.out.println(s);
    }

    @Test
    public void addChar() {
        String s = editDistance.addChar("abcd", 2, "efghi");
        System.out.println(s);
    }

    @Test
    public void addCharAtLastPos() {
        String s = editDistance.addChar("abcd", 4, "efghi");
        System.out.println(s);
    }

    @Test
    public void removeChar() {
        String s = editDistance.removeChar("abcd",2);
        System.out.println(s);
    }

    @Test
    public void removeCharAt_0_pos() {
        String s = editDistance.removeChar("abcd",0);
        System.out.println(s);
    }

    @Test
    public void removeCharAt_last_pos() {
        String s = editDistance.removeChar("abcd",3);
        System.out.println(s);
    }
}