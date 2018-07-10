package com.trendcore.problems;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string, find the length of the longest substring without repeating characters.
 * <p>
 * Examples:
 * <p>
 * Given "abcabcbb", the answer is "abc", which the length is 3.
 * <p>
 * Given "bbbbb", the answer is "b", with the length of 1.
 * <p>
 * Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 * <p>
 * <p>
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
 */
public class LongestSubString {

    //optimize this solution

    public static void main(String[] args) {
        String inputs[] = new String[]{"abcabcbb", "bbbbb", "pwwkew", "c", "au", "dvdf"};
        //String inputs[] = new String[]{"abcabcbb"};

        for (int i = 0; i < inputs.length; i++) {
            int lengthOfLongestSubstring = lengthOfLongestSubstring(inputs[i]);
            System.out.println("Length of longest substring for Input " + inputs[i] + " :- " + lengthOfLongestSubstring);
        }
    }

    private static int lengthOfLongestSubstring(String input) {

        if(input.length() == 0)
            return 0;

        String s;

        int max = 0;

        int start = 0;

        Map characters = new HashMap();

        for (int i = 0; i < input.length(); i++) {

            char c = input.charAt(i);
            Integer position = (Integer) characters.get(input.charAt(i));
            if(position != null && start <= position){

                int len = getLength(start, i);
                if(max < len){
                    max = len;
                }

                start = position+1;
            }
            characters.put(input.charAt(i),i);
        }

        int len = getLength(start, input.length());
        if(max < len){
            max = len;
        }

        return max;
    }

    private static int getLength(int start, int currentPos) {
        return currentPos - start;
    }


}
