package com.trendcore.problems.leetcode;

import java.util.*;

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
        String s = l.longestPalindrome("aaaabaaa");
        System.out.println(s);
    }

    public String longestPalindrome(String s) {

        //aba

        //stack approach
        Stack<Character> stack = new Stack();

        for(int i = 0 ; i < s.length() ; i++){
            if(stack.empty()){
                //then push
                stack.push(s.charAt(i));
            }else{
                if(stack.peek().equals(s.charAt(i))  ){
                    //palindrome

                    //case for aa or bb like strings

                    //when palindrome string is hit
                    //then start traversing elements to get max palindrome.
                    //when we dont get element then empty stack
                    //bcoz prev elements are not useful.
                }else if(isPalindromeForStack(stack,s.charAt(i))){
                    //case for
                    //aba strings
                }


            }
        }

        return "";
    }

    private boolean isPalindromeForStack(Stack<Character> stack, char c) {
        if(stack.size() == 1){
            return false;
        }else{
            Character pop = stack.pop();
            if(stack.peek() == c){
                stack.push(pop);
                return true;
            }
        }
        return false;
    }

    private String approach2(String s) {
        if(s.length() == 0 || s.length() == 1)
            return s;

        String longestPalindrome = "";

        for(int forward = 0 ; forward < s.length() ; forward++){
            for(int reverse = s.length() - 1 ; reverse > forward ; reverse--){
                if(isPalindrome(s,forward,reverse)){
                    String currentPalindrome = s.substring(forward,reverse+1);

                    if(longestPalindrome.length() < currentPalindrome.length()){
                        longestPalindrome = currentPalindrome;
                    }

                    if(reverse == s.length()){
                        forward = reverse;
                    }

                    //forward = reverse;
                    break;
                }
            }
        }

        if(longestPalindrome.length() == 0){
            longestPalindrome = s.substring(0,1);
        }

        return longestPalindrome;
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
