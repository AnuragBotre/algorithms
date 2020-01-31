package com.trendcore.problems.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

    @Test
    public void checkHashMapMethod() {
        Map<Character, Integer> map = new HashMap();
        String t = "aab";
        for (int i = 0; i < t.length(); i++) {
            map.compute(t.charAt(i), (o, o2) -> Optional.ofNullable(o2).orElse(0) + 1);
        }

        map.entrySet().stream().forEach(characterIntegerEntry -> System.out.println(characterIntegerEntry.getKey() + " " + characterIntegerEntry.getValue()));
    }

    @Test
    public void isAllCharactersVisited() {
        Map<Character, Integer> map = new HashMap();
        String t = "aab";
        for (int i = 0; i < t.length(); i++) {
            //map.compute(t.charAt(i),(o, o2) -> Optional.ofNullable(o2).orElse(0) + 1);
            map.compute(t.charAt(i), (o, o2) -> Optional.ofNullable(o2).orElse(0));
        }

        assertTrue(!m.isAllCharactersVisited(map));

    }

    private void act(String s, String t, String expected) {
        String s1 = m.minWindow(s, t);
        assertEquals(expected, s1);
    }
}