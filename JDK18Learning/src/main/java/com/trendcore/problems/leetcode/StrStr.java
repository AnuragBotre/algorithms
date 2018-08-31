package com.trendcore.problems.leetcode;

/**
 * https://leetcode.com/problems/implement-strstr/description/
 * 28. Implement strStr()
 * Implement strStr().
 * <p>
 * Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 * <p>
 * Example 1:
 * <p>
 * Input: haystack = "hello", needle = "ll"
 * Output: 2
 * Example 2:
 * <p>
 * Input: haystack = "aaaaa", needle = "bba"
 * Output: -1
 * Clarification:
 * <p>
 * What should we return when needle is an empty string? This is a great question to ask during an interview.
 * <p>
 * For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().
 */
public class StrStr {

    public static void main(String[] args) {
        testCase("hello", "ll");
        testCase("aaaaaa", "baa");
        testCase("aabbaa", "baa");
        testCase("aaaa", "aab");
        testCase("","a");
    }

    private static void testCase(String haystack, String needle) {
        StrStr s = new StrStr();
        System.out.println("Input :- " + haystack + " Needle :- " + needle + " Output :- " + s.strStr(haystack, needle));
    }

    public int strStr(String haystack, String needle) {

        return approach1(haystack, needle);

    }

    /**
     * For Optimized Solution look String indexOf implementation of Java Langauge.
     * @param haystack
     * @param needle
     * @return
     */
    private int approach1(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }

        int pos = -1;


        for (int i = 0; i < haystack.length(); i++) {

            if (haystack.charAt(i) == needle.charAt(0)) {
                int needleIndex = 1;
                pos = i;

                boolean found = true;
                int j = i + 1;

                for (; j < haystack.length() && needleIndex < needle.length(); j++, needleIndex++) {
                    if (haystack.charAt(j) != needle.charAt(needleIndex)) {
                        found = false;
                        break;
                    }
                }

                if (found && needleIndex >= needle.length()) {
                    return pos;
                } else {
                    pos = -1;
                }

            }
        }

        return pos;
    }

}
