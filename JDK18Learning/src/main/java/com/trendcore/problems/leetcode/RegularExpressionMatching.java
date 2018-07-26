package com.trendcore.problems.leetcode;

import java.util.HashMap;
import java.util.Map;

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
        System.out.println(r.formatResult("mississippi", "mis*is*ip*."));
        System.out.println(r.formatResult("aaa", "ab*a"));


        System.out.println(r.formatResult("a", "ab*a"));
        System.out.println(r.formatResult("aa", "ab*a"));
        System.out.println(r.formatResult("", "."));
        System.out.println(r.formatResult("a", ".*..a*"));
        System.out.println(r.formatResult("ab", ".*.."));
        System.out.println(r.formatResult("aaa", "ab*a*c*a"));
    }

    public String formatResult(String s, String p) {
        return s + " " + p + " " + isMatch(s, p);
    }

    class Struct {
        char c;
        boolean oneOrMoreOccurance;
    }

    public boolean isMatch(String s, String p) {

        int stringPointer = 0;
        int patternPointer = 0;

        boolean flag = true;

        Struct[] s1 = getStructs(p);

        boolean process = false;

        Map<Character, Integer> noOfCharacterInserted = new HashMap<>();

        for (; flag; ) {

            if(stringPointer == s.length() && patternPointer == s1.length){
                return true;
            }else if(stringPointer < s.length() && patternPointer == s1.length){
                return false;
            }else if(stringPointer == s.length() && patternPointer < s1.length){
                //check if remaining charaters are *
                for(int k=patternPointer ; k < s1.length ; k++ ){
                    if(!s1[k].oneOrMoreOccurance){
                        return false;
                    }else{
                        //what needs to do with these characters
                        //here we can use those counters which are added by *
                    }
                }

                return true;
            }

            if (s1[patternPointer].c == s.charAt(stringPointer) || s1[patternPointer].c == '.') {
                stringPointer++;
                if (!s1[patternPointer].oneOrMoreOccurance) {
                    patternPointer++;
                }else{
                    Integer cnt = noOfCharacterInserted.get(s1[patternPointer].c);
                    if(cnt != null){
                        noOfCharacterInserted.put(s1[patternPointer].c,cnt++);
                    }else{
                        noOfCharacterInserted.put(s1[patternPointer].c,1);
                    }
                }
            }else{
                if(!s1[patternPointer].oneOrMoreOccurance){
                    return false;
                }else{
                    patternPointer++;
                }
            }

        }

        return true;
    }

    private boolean approach2(String s, int stringPointer, int patternPointer, boolean flag, Struct[] s1, boolean process) {
        for (; flag; ) {


            if (remainingLengthOfPattern(s1, patternPointer) > remainingLengthOfString(s, stringPointer) && !process) {
                //we might have to skip certain tokens

                if (s1[patternPointer].oneOrMoreOccurance) {
                    patternPointer++;
                } else {
                    process = true;
                }

            } else {

                if (s1.length == patternPointer && s.length() == stringPointer) {
                    return true;
                } else if (s.length() == stringPointer) {
                    //remaing chars are zero or more seq then return true or return false
                    for (int k = patternPointer; k < s1.length; k++) {
                        if (!s1[k].oneOrMoreOccurance) {
                            return false;
                        }
                    }
                    return true;
                } else if (s1.length == patternPointer) {
                    return false;
                }

                if (s1[patternPointer].c == s.charAt(stringPointer) || s1[patternPointer].c == '.') {
                    stringPointer++;
                    if (!s1[patternPointer].oneOrMoreOccurance) {
                        patternPointer++;
                        process = false;
                    }
                } else {
                    patternPointer++;
                    process = false;
                }
            }


        }

        return true;
    }

    private Struct[] getStructs(String p) {
        Struct s2[] = new Struct[p.length()];
        int s1Pointer = 0;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*') {
                s2[s1Pointer - 1].oneOrMoreOccurance = true;
            } else {
                s2[s1Pointer] = new Struct();
                s2[s1Pointer].c = p.charAt(i);
                s1Pointer++;
            }
        }

        Struct s1[] = new Struct[s1Pointer];
        System.arraycopy(s2, 0, s1, 0, s1Pointer);
        return s1;
    }

    private int remainingLengthOfString(String s, int stringPointer) {
        return s.length() - stringPointer + 1;
    }

    private int remainingLengthOfPattern(Struct[] p, int patternPointer) {
        return p.length - patternPointer + 1;
    }

    private boolean isCharacterEqual(String s, String p, int stringPointer, int patternPointer) {
        return s.charAt(stringPointer) == p.charAt(patternPointer) || p.charAt(patternPointer) == '.';
    }

    private boolean modifiedApproach1(String s, String p) {
        //This strategy is not working
        int stringPointer = 0;
        int patternPointer = 0;

        boolean flag = true;

        //In this Approach need to remember position of the the last processed character
        //If it  is * or *-1 char then do it accordingly

        int lastCharProcessedPosition = 0;

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

                        if (s.length() - 1 >= 0 && p.charAt(k) == s.charAt(s.length() - 1) || p.charAt(k) == '.') {
                            //if that char was processed using * then we can remove
                            if (lastCharProcessedPosition + 1 < p.length() && p.charAt(lastCharProcessedPosition + 1) == '*') {
                                lastCharProcessedPosition = lastCharProcessedPosition + 1;
                                continue;
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    }
                }

                return true;
            } else if (patternPointer == p.length()) {

                return false;
            }

            if (s.charAt(stringPointer) == p.charAt(patternPointer) && (p.charAt(patternPointer) != '.' || p.charAt(patternPointer) != '*')) {
                lastCharProcessedPosition = patternPointer;
                patternPointer++;
            } else if (p.charAt(patternPointer) == '.') {
                lastCharProcessedPosition = patternPointer;
                patternPointer++;
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
