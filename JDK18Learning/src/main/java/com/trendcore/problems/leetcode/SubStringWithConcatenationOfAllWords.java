package com.trendcore.problems.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        testCase("wordgoodstudentgoodword", new String[]{"word", "student"});
        testCase("wordgoodgoodgoodbestword", new String[]{"word", "good", "best", "good"});
        testCase("foobarfoobar", new String[]{"foo","bar"});

        SubStringWithConcatenationOfAllWords s = new SubStringWithConcatenationOfAllWords();
        s.combinationOfString(new String[]{"foo", "bar", "abc"});
    }

    private List<List<Integer>> combinationOfString(String[] strings) {

        List<List<Integer>> positions = new ArrayList<>();

        int lengthOfCombinations = strings.length;

        for (int i = 0; i < lengthOfCombinations; i++) {
            if (i == 0) {
                for (int j = 0; j < lengthOfCombinations; j++) {
                    ArrayList<Integer> objects = new ArrayList<>();
                    objects.add(j);
                    positions.add(objects);
                }
            } else {
                List newList = new ArrayList();
                for (int j = 0; j < positions.size(); j++) {
                    for (int k = 0; k < lengthOfCombinations; k++) {

                        List<Integer> integers = positions.get(j);
                        if (!integers.contains(k)) {
                            ArrayList<Integer> integers1 = new ArrayList<>(integers);
                            integers1.add(k);
                            newList.add(integers1);
                        }
                    }
                }
                positions = newList;
            }
        }


        return positions;
        //System.out.println(list);
    }

    /*private void findCombinationOfOtherWords(String[] strings, int wordToBeSkipped,) {

    }*/

    private static void testCase(String str, String[] words) {
        SubStringWithConcatenationOfAllWords s = new SubStringWithConcatenationOfAllWords();
        System.out.println(" " + s.findSubstring(str, words));
    }

    public List<Integer> findSubstring(String s, String[] words) {

        //find all combination of words
        //then check for substring
        List<List<Integer>> combinations = combinationOfString(words);
        //List<String> combinations = new ArrayList<>();

        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < combinations.size(); i++) {
            List<Integer> integers = combinations.get(i);

            String s2 = "";
            for(Integer k : integers){
                s2 = s2 + words[k];
            }

            //TODO : This need to be corrected
            //indexOf returns only 1st occurance
            int i1 = s.indexOf(s2);

            if (i1 > -1) {
                set.add(i1);
            }
        }


        return new ArrayList<>(set);
    }

}
