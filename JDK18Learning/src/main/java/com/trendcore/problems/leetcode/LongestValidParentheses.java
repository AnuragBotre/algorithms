package com.trendcore.problems.leetcode;

import java.util.Stack;

/**
 * https://leetcode.com/problems/longest-valid-parentheses/description/
 * <p>
 * 32. Longest Valid Parentheses
 * <p>
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
 * <p>
 * Example 1:
 * <p>
 * Input: "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()"
 * Example 2:
 * <p>
 * Input: ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()"
 */
public class LongestValidParentheses {

    public static void main(String[] args) {
        LongestValidParentheses l = new LongestValidParentheses();
        System.out.println(l.longestValidParentheses("(()"));
        System.out.println(l.longestValidParentheses(")()())"));
        System.out.println(l.longestValidParentheses(")(())"));
        System.out.println(l.longestValidParentheses("()(()"));

    }

    public int longestValidParentheses(String s) {

        if (s.length() < 2)
            return 0;

        Stack stack = new Stack();

        String validParenthesesString = "";
        String maxLengthString = "";

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(s.charAt(i));
            } else {
                if (!stack.empty()) {
                    Object pop = stack.pop();
                    validParenthesesString = validParenthesesString + pop + ")";
                } else {

                    if (validParenthesesString.length() > maxLengthString.length()) {
                        maxLengthString = validParenthesesString;
                    }
                    //reset
                    validParenthesesString = "";
                }
            }
        }



        return maxLengthString.length();
    }

}
