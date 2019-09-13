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

    public void act(String word1, String word2, int expected) {
        int i = editDistance.minDistance(word1, word2);
        assertEquals(expected,i);
    }
}