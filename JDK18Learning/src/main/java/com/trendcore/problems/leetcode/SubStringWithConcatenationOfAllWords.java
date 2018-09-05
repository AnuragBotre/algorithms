package com.trendcore.problems.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/substring-with-concatenation-of-all-words/description/
 * <p>
 * 30. Substring with Concatenation of All Words
 * You are given a string, s, and a list of words, words, that are all of the same length.
 * Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * s = "barfoothefoobarman",
 * words = ["foo","bar"]
 * Output: [0,9]
 * Explanation: Substrings starting at index 0 and 9 are "barfoor" and "foobar" respectively.
 * The output order does not matter, returning [9,0] is fine too.
 * Example 2:
 * <p>
 * Input:
 * s = "wordgoodstudentgoodword",
 * words = ["word","student"]
 * Output: []
 */
public class SubStringWithConcatenationOfAllWords {

    public static void main(String[] args) {
        testCase("barfoothefoobarman", new String[]{"foo", "bar"});

        SubStringWithConcatenationOfAllWords s = new SubStringWithConcatenationOfAllWords();
        s.combinationOfString(new String[]{"foo", "bar", "abc"});
    }

    private void combinationOfString(String[] strings) {

        //TODO Fix Me
        //Instead of string with contains
        //use positions

        List<String> list = new ArrayList();

        for (int i = 0; i < strings.length; i++) {
            if (i == 0) {
                for (int j = 0; j < strings.length; j++) {
                    list.add(strings[j]);
                }
            } else {
                List list1 = new ArrayList();
                for (int j = 0; j < list.size(); j++) {
                    for (int k = 0; k < strings.length; k++) {
                        if (!list.get(j).contains(strings[k])) {
                            list1.add(list.get(j) + strings[k]);
                        }
                    }
                }
                list = list1;
            }
        }

        System.out.println(list);
    }

    /*private void findCombinationOfOtherWords(String[] strings, int wordToBeSkipped,) {

    }*/

    private static void testCase(String str, String[] words) {
        SubStringWithConcatenationOfAllWords s = new SubStringWithConcatenationOfAllWords();
        s.findSubstring(str, words);
    }

    public List<Integer> findSubstring(String s, String[] words) {

        //find all combination of words
        //then check for substring


        return null;
    }

}
