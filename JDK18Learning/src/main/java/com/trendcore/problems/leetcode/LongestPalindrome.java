package com.trendcore.problems.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/longest-palindromic-substring/description/
 * 5. Longest Palindromic Substring
 Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

 Example 1:

 Input: "babad"
 Output: "bab"
 Note: "aba" is also a valid answer.
 Example 2:

 Input: "cbbd"
 Output: "bb"
 */
public class LongestPalindrome {

    public static void main(String[] args) {
        LongestPalindrome l = new LongestPalindrome();
        String s = l.longestPalindrome("cbbd");
        System.out.println(s);
    }

    public String longestPalindrome(String s) {

        return approach1(s);
    }

    private String approach1(String s) {
        if(s.length() == 0 || s.length() == 1)
            return s;

        Map<Character,List<Integer>> map = new HashMap();

        String biggestPalindromeString = "";

        for(int i = 0 ; i < s.length() ; i++){
            char currChar = s.charAt(i);
            List<Integer> integers = map.get(currChar);
            if(integers != null){

                for(int j = 0 ; j < integers.size() ; j++){
                    //now to check if 2 strings are palindrome or not
                    Integer startIndex = integers.get(j);
                    if(isPalindrome(s, startIndex,i)){
                        int len = i+1 - startIndex;
                        if(biggestPalindromeString.length() < len){
                            biggestPalindromeString = s.substring(startIndex,i+1);
                        }
                    }
                }

                integers.add(i);

            }else{
                ArrayList<Integer> value = new ArrayList<>();
                value.add(i);
                map.put(currChar, value);
            }
        }

        if(biggestPalindromeString.length() == 0){
            return s.substring(0,1);
        }else{
            return biggestPalindromeString;
        }
    }

    private boolean isPalindrome(String s, int startIndex, int endIndex) {
        for(int i = startIndex,j = endIndex,counter = 0 ; counter  <= ((endIndex - startIndex) / 2) ; i++,j--,counter++){
            if(s.charAt(i) != s.charAt(j)){
                return false;
            }
        }
        return true;
    }

}
