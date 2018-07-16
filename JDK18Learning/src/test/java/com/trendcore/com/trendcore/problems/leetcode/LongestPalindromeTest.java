package com.trendcore.com.trendcore.problems.leetcode;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class LongestPalindromeTest {

    @Test
    public void singleCharacter() throws Exception {
        String s = "a";
        assertTrue(isPalidrome(s));
    }

    @Test
    public void isPalidromeAfterAppendingCharacter() throws Exception {
        String a = "a";
        assertTrue(isPalidrome(getPalindrome(a,'a')));
    }

    private String getPalindrome(String a, char c) {
        if(a.length() == 1 && a.charAt(a.length()-1) == c){
            a = ""+c;
        }
        return a;
    }

    @Test
    public void extractPalindrome() throws Exception {
        String a = "a";
        String b = "abc";

    }

    private boolean isPalidrome(String s) {
        return s.length() == 1;
    }
}
