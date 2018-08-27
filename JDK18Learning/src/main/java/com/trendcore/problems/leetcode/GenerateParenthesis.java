package com.trendcore.problems.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/generate-parentheses/description/
 * 22. Generate Parentheses
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * <p>
 * For example, given n = 3, a solution set is:
 * <p>
 * [
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 * <p>
 * (((())))
 * ((())())
 * ((()))()
 * ()((()))
 * ()(()())
 * ()(())()
 * ()()(())
 * ()()()()
 */
public class GenerateParenthesis {

    public static void main(String[] args) {
        GenerateParenthesis g = new GenerateParenthesis();
        g.generateParenthesis(3);
    }

    public List<String> generateParenthesis(int n) {

        List<String> list = new ArrayList();

        String s = "";
        String finalString = "";
        for (int i = 0; i < n; i++) {
            s = "(" + s + ")";
            finalString = finalString + "()";
        }


        list.add(s);
        list.add(finalString);

        while (!s.equals(finalString)) {

        }

        return list;
    }

    private boolean canMove(String s, int pos) {
        int cnt = 0;
        for (int i = pos - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                if (cnt > 0) {
                    return true;
                } else {
                    cnt++;
                }
            } else {
                cnt--;
            }
        }
        return false;
    }

    private int getPosition(String s, int offset) {
        for (int i = offset; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                return i;
            }
        }
        return 0;
    }

}
