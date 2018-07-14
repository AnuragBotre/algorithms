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
        //solve this
        //aabbaa
        String s1 = l.longestPalindrome("eeeeeeeee");
        System.out.println(s1);

        String s[] = {"a", "aa", "aba", "aaa", "abba", "aaaa", "abcba", "abccba", "abcdcba","abcaba" , "babad" ,
                        "bananas" , "babad" , "xabbabba" , "abbabban" , "xabbabban" , "aaabaaaa" , "aabbaa"
                        ,"eeeeeeeee" , "eeeeeeee"};
        for (int i = 0; i < s.length; i++) {
            System.out.println(s[i] + " " + l.longestPalindrome(s[i]) + " - " + l.approach2(s[i]) + " " + l.longestPalindrome(s[i]).equals(l.approach2(s[i])));
        }

    }

    public String longestPalindrome(String s) {
        String biggestPalindrome = "";
        int backPointer = 0;
        int frontPointer = 0;

        String palindrome = "";

        //aabbaa

        for (int i = 0; i < s.length(); i++) {
            if(palindrome.equals("")){
                palindrome = ""+s.charAt(i);
                backPointer = i;
                frontPointer = i;
            }else{

                //int lastPointer = backPointer - 1 >= 0 ? backPointer - 1 : backPointer;
                int lastPointer = backPointer - 1;

                if( lastPointer >= 0 && (frontPointer+1) < s.length() && s.charAt(lastPointer) == s.charAt(frontPointer+1) ){
                    palindrome = s.charAt(lastPointer) + palindrome + s.charAt(frontPointer+1);
                    backPointer--;
                    frontPointer++;
                }else if(palindrome.charAt(palindrome.length() - 1) == s.charAt(i)){
                    String c = "";
                    int forwardTraversalPtr;
                    int backTravesalPtr;
                    for(backTravesalPtr = palindrome.length() - 1 , forwardTraversalPtr = i; backTravesalPtr >= 0;backTravesalPtr--,forwardTraversalPtr++){

                        if(forwardTraversalPtr >= s.length()){
                            c = "";
                            break;
                        }

                        if(palindrome.charAt(backTravesalPtr) == s.charAt(forwardTraversalPtr)){
                            c = c + s.charAt(forwardTraversalPtr);
                        }else{
                            c = "";
                            break;
                        }
                    }

                    if(c.length() > 0){
                        i = forwardTraversalPtr - 1;
                        palindrome = palindrome + c;
                        frontPointer = i;
                        if (biggestPalindrome.length() < palindrome.length()) {
                            biggestPalindrome = palindrome;
                        }
                        continue;
                    }else{

                        if(palindrome.length() >= 2 && palindrome.charAt(palindrome.length() - 2) == s.charAt(i)){
                            c = "";
                            for(backTravesalPtr = palindrome.length() - 2 , forwardTraversalPtr = i; backTravesalPtr >= 0;backTravesalPtr--,forwardTraversalPtr++){

                                if(forwardTraversalPtr >= s.length()){
                                    c = "";
                                    break;
                                }

                                if(palindrome.charAt(backTravesalPtr) == s.charAt(forwardTraversalPtr)){
                                    c = c + s.charAt(forwardTraversalPtr);
                                }else{
                                    c = "";
                                    break;
                                }
                            }

                            if(c.length() > 0){
                                i = forwardTraversalPtr - 1;
                                palindrome = palindrome + c;
                                frontPointer = i;
                                if (biggestPalindrome.length() < palindrome.length()) {
                                    biggestPalindrome = palindrome;
                                }
                                continue;
                            }
                        }
                    }

                }else if(palindrome.length() >= 2 && palindrome.charAt(palindrome.length() - 2) == s.charAt(i)){
                    String c = "";
                    int forwardTraversalPtr;
                    int backTravesalPtr;
                    for(backTravesalPtr = palindrome.length() - 2 , forwardTraversalPtr = i; backTravesalPtr >= 0;backTravesalPtr--,forwardTraversalPtr++){

                        if(forwardTraversalPtr >= s.length()){
                            c = "";
                            break;
                        }

                        if(palindrome.charAt(backTravesalPtr) == s.charAt(forwardTraversalPtr)){
                            c = c + s.charAt(forwardTraversalPtr);
                        }else{
                            c = "";
                            break;
                        }
                    }

                    if(c.length() > 0){
                        i = forwardTraversalPtr - 1;
                        palindrome = palindrome + c;
                        frontPointer = i;
                        if (biggestPalindrome.length() < palindrome.length()) {
                            biggestPalindrome = palindrome;
                        }
                        continue;
                    }
                }else{
                    palindrome = ""+s.charAt(i);
                    frontPointer = i;
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
