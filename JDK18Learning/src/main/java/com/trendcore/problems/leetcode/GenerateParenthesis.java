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
 *
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
        for (int i = 0; i < n; i++) {
            s = s + "(";
        }
        for (int i = 0; i < n; i++) {
            s = s + ")";
        }

        list.add(s);

        String originalString = s;

        boolean flag = true;
        int offset = 0;
        while (flag) {
            //first need to find
            int pos = getPosition(s, offset);
            //move that character if possible
            if (canMove(s, pos)) {
                //form new string
                String newString = "";
                for (int i = 0; i < pos - 1; i++) {
                    newString = newString + s.charAt(i);
                }
                newString = newString + ")";
                newString = newString + "(";
                for (int i = pos + 1; i < s.length(); i++) {
                    newString = newString + s.charAt(i);
                }

                System.out.println(newString);
                s = newString;

            } else {
                offset = pos + 1;
            }
        }

        System.out.println(s);

        return null;


        //can go have recursive algo like generating ()
        //then insert followed and append
        //in case of 2
        //1st () -> (())
        //2nd -> ()()
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
            }else{
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
