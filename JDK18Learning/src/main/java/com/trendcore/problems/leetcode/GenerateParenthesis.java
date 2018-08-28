package com.trendcore.problems.leetcode;

import java.util.*;

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
        //System.out.println(g.generateParenthesis(3));
        System.out.println(g.generateParenthesis(4));
    }

    public List generateParenthesis(int n) {

        Set<String> list = new HashSet<>();

        for (int i = 0; i < n; i++) {
            if (list.isEmpty()) {
                list.add("()");
            } else {
                //List newList = new ArrayList();
                Set<String> newList = new HashSet<>();
                /*for (int j = 0; j < list.size(); j++) {
                    String s = list.get(j);
                    //start inserting  ()
                    insert(s, newList);
                }*/
                for(String s : list){
                    insert(s, newList);
                }
                list = newList;
            }
        }

        List list1 = new ArrayList(list);

        return list1;
    }

    private void insert(String s, Set<String> newList) {
        String t = "()";
        String newString = "";
        for (int i = 0; i < s.length(); i++) {
            newString = "";
            for (int j = 0; j < i; j++) {
                newString = newString + s.charAt(j);
            }
            newString = newString + t;
            for (int j = i; j < s.length(); j++) {
                newString = newString + s.charAt(j);
            }

            newList.add(newString);
        }
    }

    private void approch2NotWorking(int n, List<String> list) {
        String s = "";
        String finalString = "";
        for (int i = 0; i < n; i++) {
            s = "(" + s + ")";
            finalString = finalString + "()";
        }

        list.add(s);
        //list.add(finalString);

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
                list.add(tempString);
                System.out.println(tempString);
                int remainingLength = tempString.length() - (2 * rotationCount);
                positions = (2 * rotationCount) + (remainingLength / 2);
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
                list.add(tempString);
                System.out.println(tempString);
                positions++;
                cnt++;
            }
        }
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

    /**
     * Leet Code most optimized solution
     */
    class Solution {
        public List<String> generateParenthesis(int n) {
            List<String> result = new ArrayList<>();
            if (n <= 0) {
                return result;
            }
            StringBuilder sb = new StringBuilder();
            dfs(result, n, n, sb);
            return result;
        }
        private void dfs(List<String> result, int left, int right, StringBuilder sb) {
            if (left == 0 && right == 0) {
                result.add(sb.toString());
                return;
            }
            if (left > 0) {
                sb.append('(');
                dfs(result, left - 1, right, sb);
                sb.deleteCharAt(sb.length() - 1);
            }
            if (right > left) {
                sb.append(')');
                dfs(result, left, right - 1, sb);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

}
