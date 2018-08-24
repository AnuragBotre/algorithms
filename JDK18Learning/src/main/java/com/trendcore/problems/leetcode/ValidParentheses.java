package com.trendcore.problems.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * https://leetcode.com/problems/valid-parentheses/description/
 * 20. Valid Parentheses
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * <p>
 * An input string is valid if:
 * <p>
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Note that an empty string is also considered valid.
 * <p>
 * Example 1:
 * <p>
 * Input: "()"
 * Output: true
 * Example 2:
 * <p>
 * Input: "()[]{}"
 * Output: true
 * Example 3:
 * <p>
 * Input: "(]"
 * Output: false
 * Example 4:
 * <p>
 * Input: "([)]"
 * Output: false
 * Example 5:
 * <p>
 * Input: "{[]}"
 * Output: true
 */
public class ValidParentheses {


    public static void main(String[] args) {
        ValidParentheses v = new ValidParentheses();
        System.out.println(v.isValid("()"));
        System.out.println(v.isValid("()[]{}"));
        System.out.println(v.isValid("([)]"));
        System.out.println(v.isValid("{[]}"));
    }
    private static Map<Character, Character> map;

    static {
        map = new HashMap();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
    }

    public boolean isValid(String s) {


        if (s == null || "".equals(s))
            return true;

        Stack<Character> stack = new Stack();

        for (int i = 0; i < s.length(); i++) {
            if (stack.empty()) {
                stack.push(s.charAt(i));
            } else {
                if (map.get(stack.peek()) != null
                        && s.charAt(i) == map.get(stack.peek())) {
                    stack.pop();
                } else {
                    stack.push(s.charAt(i));
                }
            }
        }

        return stack.empty();
    }
}
