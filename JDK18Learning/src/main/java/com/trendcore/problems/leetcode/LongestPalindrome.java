package com.trendcore.problems.leetcode;

import java.util.*;

/**
 * https://leetcode.com/problems/longest-palindromic-substring/description/
 * 5. Longest Palindromic Substring
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 * <p>
 * Example 1:
 * <p>
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * Example 2:
 * <p>
 * Input: "cbbd"
 * Output: "bb"
 */
public class LongestPalindrome {

    public static void main(String[] args) {
        LongestPalindrome l = new LongestPalindrome();
        //String s = l.longestPalindrome("aaaabaaa");
        //baaaaab

        //Hint
        //How can we reuse a previously computed palindrome to compute a larger palindrome?
        String s1 = l.longestPalindrome("bananas");
        System.out.println(s1);

        /*String s[] = {"a", "aa", "aba", "aaa", "abba", "aaaa", "abcba", "abccba", "abcdcba","abcaba" , "babad" , "bananas" , "babad"};
        for (int i = 0; i < s.length; i++) {
            System.out.println(l.longestPalindrome(s[i]));
        }*/

    }

    public String longestPalindrome(String s) {
        String biggestPalindrome = "";
        int backPointer = 0;

        String palindrome = "";

        for (int i = 0; i < s.length(); i++) {
            if (palindrome.equals("")) {
                palindrome = "" + s.charAt(i);
                backPointer = i;
            } else {
                if (palindrome.length() == 1) {
                    if (palindrome.charAt(0) == s.charAt(i)) {
                        palindrome = palindrome + s.charAt(i);
                        backPointer = i - 1;
                    } else if (i - 2 >= 0 && s.charAt(i - 2) == s.charAt(i)) {
                        palindrome = s.charAt(i - 2) + palindrome + s.charAt(i);
                        backPointer = i - 2;
                    } else if (i - 1 >= 0 && i + 1 < s.length() && s.charAt(i - 1) == s.charAt(i + 1)) {
                        palindrome = "" + s.charAt(i - 1) + s.charAt(i) + s.charAt(i + 1);
                        backPointer = i - 1;
                        i++;
                    } else {
                        palindrome = "" + s.charAt(i);
                        backPointer = i;
                    }
                } else if (palindrome.length() == 2) {

                    if (palindrome.charAt(0) == s.charAt(i)) {
                        palindrome = palindrome + s.charAt(i);
                    } else if (backPointer - 1 >= 0 && s.charAt(backPointer - 1) == s.charAt(i)) {
                        palindrome = s.charAt(backPointer - 1) + palindrome + s.charAt(i);
                        backPointer--;
                    } else {
                        palindrome = "" + s.charAt(i);
                        backPointer = i;
                    }

                } else if (palindrome.length() > 2) {

                    if (palindrome.charAt(0) == s.charAt(i)) {
                        palindrome = palindrome + s.charAt(i);
                    } else if (backPointer - 1 >= 0 && s.charAt(backPointer - 1) == s.charAt(i)) {
                        palindrome = s.charAt(backPointer - 1) + palindrome + s.charAt(i);
                        backPointer--;
                    } else {
                        palindrome = "" + s.charAt(i);
                        backPointer = i;
                    }
                    //backPointer-1 char and current char should be same
                } else {
                    palindrome = "" + s.charAt(i);
                    backPointer = i;
                }
            }

            if (biggestPalindrome.length() < palindrome.length()) {
                biggestPalindrome = palindrome;
            }
        }


        return biggestPalindrome;
    }

    private boolean isPalindrome(String palindrome, char c) {
        if (palindrome.length() == 1) {
            return palindrome.charAt(0) == c;
        } else if (palindrome.length() % 2 == 0) {
            return false;
        } else {
            return isPalindrome(palindrome + c, 0, palindrome.length() + 1);
        }
    }

    private String approach2(String s) {
        if (s.length() == 0 || s.length() == 1)
            return s;

        String longestPalindrome = "";

        for (int forward = 0; forward < s.length(); forward++) {
            for (int reverse = s.length() - 1; reverse > forward; reverse--) {
                if (isPalindrome(s, forward, reverse)) {
                    String currentPalindrome = s.substring(forward, reverse + 1);

                    if (longestPalindrome.length() < currentPalindrome.length()) {
                        longestPalindrome = currentPalindrome;
                    }

                    if (reverse == s.length()) {
                        forward = reverse;
                    }

                    //forward = reverse;
                    break;
                }
            }
        }

        if (longestPalindrome.length() == 0) {
            longestPalindrome = s.substring(0, 1);
        }

        return longestPalindrome;
    }

    private String approach1(String s) {
        if (s.length() == 0 || s.length() == 1)
            return s;

        Map<Character, List<Integer>> map = new HashMap();

        String biggestPalindromeString = "";

        for (int i = 0; i < s.length(); i++) {
            char currChar = s.charAt(i);
            List<Integer> integers = map.get(currChar);
            if (integers != null) {

                for (int j = 0; j < integers.size(); j++) {
                    //now to check if 2 strings are palindrome or not
                    Integer startIndex = integers.get(j);
                    if (isPalindrome(s, startIndex, i)) {
                        int len = i + 1 - startIndex;
                        if (biggestPalindromeString.length() < len) {
                            biggestPalindromeString = s.substring(startIndex, i + 1);
                        }
                    }
                }

                integers.add(i);

            } else {
                ArrayList<Integer> value = new ArrayList<>();
                value.add(i);
                map.put(currChar, value);
            }
        }

        if (biggestPalindromeString.length() == 0) {
            return s.substring(0, 1);
        } else {
            return biggestPalindromeString;
        }
    }

    private boolean isPalindrome(String s, int startIndex, int endIndex) {
        for (int i = startIndex, j = endIndex, counter = 0; counter <= ((endIndex - startIndex) / 2); i++, j--, counter++) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }

}
