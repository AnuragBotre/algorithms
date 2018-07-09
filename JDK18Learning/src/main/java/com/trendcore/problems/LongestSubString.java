package com.trendcore.problems;

import java.util.ArrayList;
import java.util.List;

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

        for (int i = 0; i < inputs.length; i++) {
            int lengthOfLongestSubstring = lengthOfLongestSubstring(inputs[i]);
            System.out.println("Length of longest substring for Input " + inputs[i] + " :- " + lengthOfLongestSubstring);
        }
    }

    private static int lengthOfLongestSubstring(String input) {

        if(input.length() == 0)
            return 0;

        String s;

        int max = 1;


        for (int i = 0; i < input.length(); i++) {

            s = ""+input.charAt(i);

            for (int j = i + 1; j < input.length(); j++) {

                if (s.contains("" + input.charAt(j))) {
                    if (max < s.length()) {
                        max = s.length();
                    }
                    s = "" + input.charAt(j);
                } else {
                    s = s + input.charAt(j);
                }
            }

            if (max < s.length()) {
                max = s.length();
            }
        }

        return max;
    }


}
