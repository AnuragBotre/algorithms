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
        System.out.println(r.formatResult("aaa", "a"));
        System.out.println(r.formatResult("aaa", "aa."));
        System.out.println(r.formatResult("aaa", "a*"));
        System.out.println(r.formatResult("aaab", "a*b"));
        System.out.println(r.formatResult("aaab", ".*b"));

        System.out.println(r.formatResult("mississippi", "mis*is*p*."));

        System.out.println(r.formatResult("aab", "c*a*b*"));

    }

    public String formatResult(String s, String p) {
        return s + " " + p + " " + isMatch(s, p);
    }

    public boolean isMatch(String s, String p) {

        int stringPointer = 0;
        int patternPointer = 0;

        boolean flag = true;

        for(;flag;){
            if(s.charAt(stringPointer) == s.charAt(patternPointer)){
                stringPointer++;
                patternPointer++;
            }else{
                if(s.length()-stringPointer != p.length()-patternPointer){

                }
            }
        }

        return true;
    }

    private boolean approach1(String s, String p) {
        int stringPointer = 0;
        int patternPointer = 0;

        boolean flag = true;

        char prevChar = 0;

        for (; flag; ) {

            if ((stringPointer == s.length() && patternPointer == p.length()) || (stringPointer == s.length() && patternPointer == p.length() - 1)) {
                return true;
            } else if (stringPointer < s.length() && patternPointer == p.length()) {
                return false;
            } else if (stringPointer == s.length()) {

                //We are at the end of string and pattern length is not finished yet.
                //hence analyzing pattern length

                for(int c = patternPointer+1 ; c < p.length() ; c++){
                    if(p.charAt(c) != '*'){
                        return false;
                    }
                }

                return true;
                /*}else{
                    return false;
                }*/
            }

            if (s.charAt(stringPointer) == p.charAt(patternPointer) && (p.charAt(patternPointer) != '.' || p.charAt(patternPointer) != '*')) {
                patternPointer++;
            } else if (p.charAt(patternPointer) == '.') {
                patternPointer++;
            } else if (p.charAt(patternPointer) == '*') {
                if (patternPointer - 1 >= 0) {
                    prevChar = p.charAt(patternPointer - 1);
                    if (prevChar != '.' && prevChar != s.charAt(stringPointer)) {
                        //stringPointer--;
                        patternPointer++;
                    }
                }
            } else {
                if (patternPointer + 1 < p.length()) {
                    if (p.charAt(patternPointer + 1) == '*') {
                        patternPointer = patternPointer + 2;
                    }
                } else {
                    return false;
                }

            }


            stringPointer++;
        }
        return true;
    }
}
