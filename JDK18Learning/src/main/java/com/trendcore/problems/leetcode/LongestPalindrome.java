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
        String s1 = l.longestPalindrome("xabbabba");
        System.out.println(s1);
        /*String s1 = l.longestPalindrome("eeeeeeeee");
        System.out.println(s1);*/

        String s[] = {"a", "aa", "aba", "aaa", "abba", "aaaa", "abcba", "abccba", "abcdcba", "abcaba", "babad",
                "bananas", "babad", "xabbabba", "abbabban", "xabbabban", "aaabaaaa", "aabbaa"
                , "eeeeeeeee", "eeeeeeee"};
        for (int i = 0; i < s.length; i++) {
            try{
                System.out.println(s[i] + " " + l.longestPalindrome(s[i]) + " - " + l.approach2(s[i]) + " - " + l.longestPalindrome(s[i]).equals(l.approach2(s[i])));
            }catch (Exception e){
                System.out.println(s[i]);
            }

        }

    }

    public String longestPalindrome(String s) {
        s = preProcess(s);
        int centre = 0,right=0;
        int p[] = new int[s.length()];

        //Map p = new HashMap();

        for(int i = 1 ; i < s.length()-1 ; i++){
            int mirror = 2*centre-i;

            //p.put(i,);
            p[i] = right > i ? Math.min(right-i,p[mirror]) : 0;

            // Attempt to expand palindrome centered at i
            while (s.charAt(i + 1 + p[i]) == s.charAt(i - 1 - p[i]))
                p[i]++;


            // If palindrome centered at i expand past R,
            // adjust center based on expanded palindrome.
            if (i + p[i] > right) {
                centre = i;
                right = i + p[i];
            }
        }


        // Find the maximum element in P.
        int maxLen = 0;
        int centerIndex = 0;
        for (int i = 1; i < p.length; i++) {
            if (p[i] > maxLen) {
                maxLen = p[i];
                centerIndex = i;
            }
        }

        return s.substring((centerIndex - 1 - maxLen)/2, maxLen);

    }

    private String approach3(String s) {
        String biggestPalindrome = "";
        s = preProcess(s);

        String palindrome = "";

        for(int i = 0 ; i < s.length() ; i++){
            palindrome = ""+s.charAt(i);
            for(int backwardPointer = i-1 , forwardPointer = i+1 ; backwardPointer >= 0 && forwardPointer < s.length() ; backwardPointer--,forwardPointer++){
                if(s.charAt(backwardPointer) == s.charAt(forwardPointer)){
                    palindrome = s.charAt(backwardPointer) +palindrome+ s.charAt(forwardPointer);
                }else{
                    break;
                }
            }

            if(biggestPalindrome.length() < palindrome.length()){
                biggestPalindrome = palindrome;
            }
        }

        return biggestPalindrome.replace("$","");
    }

    private String preProcess(String s) {
        /*String result = "";
        for(int i = 0 ; i < s.length() ; i++) {
            result = result + "$" + s.charAt(i);
        }
        return result + "$";*/

        int n = s.length();
        if (n == 0) return "^$";
        String ret = "^";
        for (int i = 0; i < n; i++)
            ret += "#" + s.charAt(i);

        ret += "#$";
        return ret;


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
