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
        System.out.println(w.isMatch("abc", "*"));
        System.out.println(w.isMatch("abc", "**"));
        System.out.println(w.isMatch("abc", "***"));
        System.out.println(w.isMatch("abc", "abc"));
        System.out.println(w.isMatch("abc", "???"));
        System.out.println(w.isMatch("abc", "a"));
        System.out.println(w.isMatch("abc", "a*c"));
        System.out.println(w.isMatch("abc", "a?c"));
        System.out.println(w.isMatch("abc", "a**c"));
        System.out.println(w.isMatch("abc", "**c"));
        System.out.println(w.isMatch("abc", "*b*c"));
        System.out.println(w.isMatch("", "*"));
        System.out.println(w.isMatch("acdcb", "a*c?b"));
        System.out.println(w.isMatch("aaaabaaaabbbbaabbbaabbaababbabbaaaababaaabbbbbbaabbbabababbaaabaabaaaaaabbaabbbbaababbababaabbbaababbbba", "*****b*aba***babaa*bbaba***a*aaba*b*aa**a*b**ba***a*a*"));
    }

    public boolean isMatch(String s, String p) {
        if (s.length() == 0 && p.length() == 0) {
            return true;
        }

        return traverse(s, 0, p, 0);
    }

    private boolean traverse(String s, int stringPointer, String p, int patternPointer) {

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

            //rule 1
            boolean traverse = traverse(s, stringPointer + 1, p, patternPointer);
            if (!traverse) {
                traverse = traverse(s, stringPointer + 1, p, patternPointer + 1);
                /*if (!traverse) {
                    traverse = traverse(s, stringPointer, p, patternPointer + 1);
                }*/
            }
            return traverse;
        } else if (c == '?') {
            return traverse(s, stringPointer + 1, p, patternPointer + 1);
        } else if (c == s.charAt(stringPointer)) {
            return traverse(s, stringPointer + 1, p, patternPointer + 1);
        }

        return false;
    }

}
