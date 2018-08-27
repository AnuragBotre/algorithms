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
 * ((()()))
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

        String tempString = s;
        int positions = n;
        int cnt = 0;
        int rotationCount = 1;

        int nn = n;

        while (!tempString.equals(finalString)) {
            if (cnt == nn - 1) {
                //rotation phase
                String newString = "";
                for (int i = 0; i < positions - 1; i++) {
                    newString = newString + tempString.charAt(i);
                }
                tempString = "()" + newString;
                System.out.println(tempString);
                int remainingLength=tempString.length()-(2*rotationCount);
                positions = (2*rotationCount) + (remainingLength / 2);
                cnt = 0;
                rotationCount++;
                nn--;
            } else {
                String newString = "";
                for (int i = 0; i < positions - 1; i++) {
                    newString = newString + tempString.charAt(i);
                }
                for (int i = positions + 1; i < s.length(); i++) {
                    newString = newString + tempString.charAt(i);
                }

                //insert at n+1 position
                String t = "";
                int j = 0;
                for (j = 0; j < positions; j++) {
                    t = t + newString.charAt(j);
                }
                t = t + "()";
                for (int i = j; i < newString.length(); i++) {
                    t = t + newString.charAt(i);
                }

                //need to move next pos starting cnt which is cnt+1
                tempString = t;
                System.out.println(tempString);
                positions++;
                cnt++;
            }
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
