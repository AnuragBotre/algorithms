package com.trendcore.problems.leetcode;

import java.util.ArrayList;
import java.util.List;
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
        System.out.println(l.longestValidParentheses("()()()"));
        System.out.println(l.longestValidParentheses("(()()"));
        System.out.println(l.longestValidParentheses("(()())"));
        System.out.println(l.longestValidParentheses("()(())"));

        //This case needs to be handled
        System.out.println(l.longestValidParentheses(")()())()()("));

    }

    public int longestValidParentheses(String s) {

        if (s.length() < 2)
            return 0;

        Stack<Character> stack = new Stack<>();

        String r = "";

        List<Character> list = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(s.charAt(i));
                list.add(s.charAt(i));
            } else {
                if (!stack.empty()) {
                    stack.pop();
                    for (int j = list.size() - 1; j >= 0; j--) {
                        if (list.get(j) != '_') {
                            list.remove(j);
                            break;
                        }
                    }

                    list.add('_');
                }
            }
        }

        System.out.println(list);

        int max = 0;
        String str = "";
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == '(') {
                str = "";
            } else {
                str = str + list.get(i);
            }

            if (max < str.length()) {
                max = str.length();
            }
        }

        return 2 * max;
    }

}
