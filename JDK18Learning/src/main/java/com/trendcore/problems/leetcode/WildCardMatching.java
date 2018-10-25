package com.trendcore.problems.leetcode;

/**
 * https://leetcode.com/problems/wildcard-matching/
 * Given an input string (s) and a pattern (p),
 * implement wildcard pattern matching with support for '?' and '*'.
 * <p>
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 * The matching should cover the entire input string (not partial).
 * <p>
 * Note:
 * <p>
 * s could be empty and contains only lowercase letters a-z.
 * p could be empty and contains only lowercase letters a-z, and characters like ? or *.
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
 * p = "*"
 * Output: true
 * Explanation: '*' matches any sequence.
 * Example 3:
 * <p>
 * Input:
 * s = "cb"
 * p = "?a"
 * Output: false
 * Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
 * Example 4:
 * <p>
 * Input:
 * s = "adceb"
 * p = "*a*b"
 * Output: true
 * Explanation: The first '*' matches the empty sequence,
 * while the second '*' matches the substring "dce".
 * Example 5:
 * <p>
 * Input:
 * s = "acdcb"
 * p = "a*c?b"
 * Output: false
 */
public class WildCardMatching {

    public static void main(String[] args) {
        WildCardMatching w = new WildCardMatching();

        w.testCase("bba", "*a**", true);
        /*w.testCase("abc", "**" , true);
        w.testCase("abc", "*" , true);
        w.testCase("abc", "***" , true);
        w.testCase("abc", "abc",true);
        w.testCase("abc", "???",true);
        w.testCase("abc", "a" , false);
        w.testCase("abc", "a*c" , true);
        w.testCase("abc", "a?c" , true);
        w.testCase("abc", "a**c" , true);
        w.testCase("abc", "**c" , true);
        w.testCase("abc", "*b*c" , true);
        w.testCase("", "*" , true);
        w.testCase("acdcb", "a*c?b" , false);*/
        w.testCase("aaaabaaaabbbbaabbbaabbaababbabbaaaababaaabbbbbbaabbbabababbaaabaabaaaaaabbaabbbbaababbababaabbbaababbbba", "*****b*aba***babaa*bbaba***a*aaba*b*aa**a*b**ba***a*a*", true);
        //w.testCase("aaaabaaaabbbbaabbbaabbaababbabbaaaababaaabbbbbbaabbbabababbaaabaabaaaaaabbaabbbbaababbababaabbbaababbbba", "*b*aba***babaa*bbaba***a*aaba*b*aa**a*b**ba***a*a*", true);
        /*w.testCase("ababba","***b*ab***ba",true);*/
    }

    private void testCase(String abc, String s, boolean b) {
        System.out.println("Expected :- " + b + " Actual :- " + isMatch(abc, s));
    }

    public boolean isMatch(String s, String p) {
        if (s.length() == 0 && p.length() == 0) {
            return true;
        }

        return traverse(s, 0, p, 0);
    }

    private boolean traverse(String s, int stringPointer, String p, int patternPointer) {

        if (stringPointer < 0) {
            return false;
        }

        if (stringPointer == s.length() - 1 && patternPointer == p.length() - 1) {
            char c = s.charAt(stringPointer);
            char pc = p.charAt(patternPointer);

            if (c == pc) {
                return true;
            } else if (pc == '*' || pc == '?') {
                return true;
            }

            return false;
        }

        if (patternPointer >= p.length()) {
            return false;
        }

        if (stringPointer >= s.length()) {
            //there are no * character left
            for (int i = patternPointer; i < p.length(); i++) {
                if (p.charAt(i) != '*') {
                    return false;
                }
            }
            return true;
        }


        char c = p.charAt(patternPointer);

        if (c == '*') {
            //TODO Need to fix this.
            //do we need to keep
            //go forward
            boolean b = combinationsOfZeroOrManyOccurances(s, stringPointer, p, patternPointer, stringPointer, patternPointer);
            if (!b) {
                traverse(s, stringPointer, p, patternPointer + 1);
            }
        } else if (c == '?') {
            return traverse(s, stringPointer + 1, p, patternPointer + 1);
        } else if (c == s.charAt(stringPointer)) {
            return traverse(s, stringPointer + 1, p, patternPointer + 1);
        }

        return false;
    }

    private boolean combinationsOfZeroOrManyOccurances(String s, int stringPointer, String p, int patternPointer, int originalStringPointer, int originalPatternPointer) {

        if (stringPointer >= s.length()) {
            return false;
        }

        if (patternPointer >= p.length()) {
            return false;
        }

        combinationsOfZeroOrManyOccurances(s, stringPointer + 1, p, patternPointer, originalStringPointer, originalPatternPointer);
        combinationsOfZeroOrManyOccurances(s, stringPointer - 1, p, patternPointer + 1, originalStringPointer, originalPatternPointer);

        return true;

    }

    private boolean traverseStar(String s, int stringPointer, String p, int patternPointer) {
        return traverse(s, stringPointer + 1, p, patternPointer);
    }

}
