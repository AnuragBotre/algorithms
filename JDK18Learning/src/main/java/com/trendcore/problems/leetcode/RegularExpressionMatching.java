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
        System.out.println(r.formatResult("aab", "c*a*bb"));

        System.out.println(r.formatResult("a", "a*"));
        System.out.println(r.formatResult("a", "aa*"));
        System.out.println(r.formatResult("aaa", "a*a*a*"));
        System.out.println(r.formatResult("aaa", "c*d*a*"));

        System.out.println(r.formatResult("aa", "a"));

        System.out.println(r.formatResult("", "bab"));

        //This is the best input to test which will invalidate below algo
        System.out.println(r.formatResult("mississippi", "mis*is*ip*."));
        System.out.println(r.formatResult("aaa", "ab*a"));
        System.out.println(r.formatResult("a", "ab*a"));
    }

    public String formatResult(String s, String p) {
        return s + " " + p + " " + isMatch(s, p);
    }

    public boolean isMatch(String s, String p) {

        int stringPointer = 0;
        int patternPointer = 0;

        boolean flag = false;




        for (; flag; ) {

            /*if(remainingLengthOfPattern > remainingLengthOfString){
                //we might have to skip certain tokens
            }else{

            }*/

            /*if((s.charAt(stringPointer) == p.charAt(patternPointer)) || p.charAt(patternPointer) == '.'){
                stringPointer++;
                patternPointer++;
            }else if(patternPointer+1 < ){

            }*/
        }

        return true;


    }

    private boolean modifiedApproach1(String s, String p) {
        //This strategy is not working
        int stringPointer = 0;
        int patternPointer = 0;

        boolean flag = true;

        //In this Approach need to remember position of the the last processed character
        //If it  is * or *-1 char then do it accordingly

        boolean tokenIsProcessedPrev = false;

        for (; flag; ) {

            if (stringPointer == s.length()) {

                if (patternPointer == p.length()) {
                    return true;
                } else {
                    for (int k = patternPointer; k < p.length(); k++) {
                        if (p.charAt(k) == '*') {
                            continue;
                        }

                        if (k + 1 < p.length() && p.charAt(k + 1) == '*') {
                            continue;
                        }

                        if (tokenIsProcessedPrev) {
                            return false;
                        }
                    }
                }

                return true;
            } else if (patternPointer == p.length()) {

                return false;
            }

            if (s.charAt(stringPointer) == p.charAt(patternPointer) && (p.charAt(patternPointer) != '.' || p.charAt(patternPointer) != '*')) {
                patternPointer++;
                tokenIsProcessedPrev = true;
            } else if (p.charAt(patternPointer) == '.') {
                patternPointer++;
                tokenIsProcessedPrev = true;
            } else if (p.charAt(patternPointer) == '*') {
                if (patternPointer - 1 >= 0) {
                    char prevChar = p.charAt(patternPointer - 1);
                    if (prevChar != '.' && prevChar != s.charAt(stringPointer)) {
                        stringPointer--;
                        patternPointer++;
                    }
                }
            } else {
                //when nothing matched then next char should be *
                //return false;
                if (patternPointer + 1 < p.length()) {
                    if (p.charAt(patternPointer + 1) == '*') {
                        patternPointer = patternPointer + 2;
                        stringPointer--;
                    } else {
                        tokenIsProcessedPrev = false;
                    }
                } else {
                    return false;
                }

            }
            stringPointer++;
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

                for (int c = patternPointer + 1; c < p.length(); c++) {
                    if (p.charAt(c) != '*') {
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
