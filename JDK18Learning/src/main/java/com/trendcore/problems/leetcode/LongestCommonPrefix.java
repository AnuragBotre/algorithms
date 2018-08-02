package com.trendcore.problems.leetcode;

/**
 * https://leetcode.com/problems/longest-common-prefix/description/
 * 14. Longest Common Prefix
 * Write a function to find the longest common prefix string amongst an array of strings.
 * <p>
 * If there is no common prefix, return an empty string "".
 * <p>
 * Example 1:
 * <p>
 * Input: ["flower","flow","flight"]
 * Output: "fl"
 * Example 2:
 * <p>
 * Input: ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 * Note:
 * <p>
 * All given inputs are in lowercase letters a-z.
 */
public class LongestCommonPrefix {

    public static void main(String[] args) {
        LongestCommonPrefix l = new LongestCommonPrefix();
        System.out.println(l.longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
        System.out.println(l.longestCommonPrefix(new String[]{"dog", "racecar", "car"}));
        System.out.println(l.longestCommonPrefix(new String[]{""}));
        System.out.println(l.longestCommonPrefix(new String[]{}));
        System.out.println(l.longestCommonPrefix(new String[]{"aca","cba"}));

    }

    public String longestCommonPrefix(String[] strs) {

        if(strs.length == 0)
            return "";

        String result = "";

        for (int i = 0; i < strs[0].length(); i++) {
            //if any of the string breaks
            if (remainingStringHaveSameChar(strs, strs[0].charAt(i), i)) {
                result = result + strs[0].charAt(i);
            }else{
                break;
            }
        }

        return result;
    }

    private boolean remainingStringHaveSameChar(String[] strs, char c, int pos) {
        for (int i = 1; i < strs.length; i++) {
            if (!(pos < strs[i].length() && strs[i].charAt(pos) == c)) {
                return false;
            }
        }

        return true;
    }

}
