package com.trendcore.problems.leetcode;

/**
 * https://leetcode.com/problems/regular-expression-matching/description/
 * 10. Regular Expression Matching
 * Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.
 * <p>
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 * <p>
 * Note:
 * <p>
 * s could be empty and contains only lowercase letters a-z.
 * p could be empty and contains only lowercase letters a-z, and characters like . or *.
 * Example 1:
 * <p>
 * Input:
 * s = "aa"
 * p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 * Example 2:
 * <p>
 * Input:
 * s = "aa"
 * p = "a*"
 * Output: true
 * Explanation: '*' means zero or more of the precedeng element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
 * Example 3:
 * <p>
 * Input:
 * s = "ab"
 * p = ".*"
 * Output: true
 * Explanation: ".*" means "zero or more (*) of any character (.)".
 * <p>
 * <p>
 * Example 4:
 * Input:
 * s = "aab"
 * p = "c*a*b"
 * Output: true
 * Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore it matches "aab".
 * <p>
 * <p>
 * <p>
 * Example 5:
 * Input:
 * s = "mississippi"
 * p = "mis*is*p*."
 * Output: false
 */
public class RegularExpressionMatching {

    public static void main(String[] args) {
        RegularExpressionMatching r = new RegularExpressionMatching();
        System.out.println(r.isMatch("aaa", "a"));
        System.out.println(r.isMatch("aaa", "aa."));
        System.out.println(r.isMatch("aaa", "a*"));
        System.out.println(r.isMatch("aaab", "a*b"));
        System.out.println(r.isMatch("aaab", ".*b"));

        System.out.println(r.isMatch("mississippi", "mis*is*p*."));

    }

    public boolean isMatch(String s, String p) {

        int stringPointer = 0;
        int patternPointer = 0;

        boolean flag = true;

        char prevChar = 0;

        for (; flag; ) {

            if ((stringPointer == s.length() && patternPointer == p.length()) || (stringPointer == s.length() && patternPointer == p.length() - 1)) {
                return true;
            } else if (stringPointer < s.length() && patternPointer == p.length()) {
                return false;
            } else if(stringPointer == s.length() || patternPointer == p.length()){
                return false;
            }

            if (s.charAt(stringPointer) == p.charAt(patternPointer) && (p.charAt(patternPointer) != '.' || p.charAt(patternPointer) != '*')) {
                patternPointer++;
            } else if (p.charAt(patternPointer) == '.') {
                patternPointer++;
            } else if (p.charAt(patternPointer) == '*') {
                if(patternPointer - 1 >= 0){
                    prevChar = p.charAt(patternPointer - 1);
                    if(prevChar != '.' && prevChar != s.charAt(stringPointer)){
                        stringPointer--;
                        patternPointer++;
                    }
                }
            } else {
                break;
            }


            stringPointer++;
        }
        return true;
    }
}
