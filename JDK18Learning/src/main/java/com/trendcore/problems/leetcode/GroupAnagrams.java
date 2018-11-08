package com.trendcore.problems.leetcode;

import java.util.List;

/**
 * https://leetcode.com/problems/group-anagrams/
 * 49. Group Anagrams
 * Given an array of strings, group anagrams together.
 * <p>
 * Example:
 * <p>
 * Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * Output:
 * [
 * ["ate","eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]
 * Note:
 * <p>
 * All inputs will be in lowercase.
 * The order of your output does not matter.
 */
public class GroupAnagrams {

    public static void main(String[] args) {
        GroupAnagrams g = new GroupAnagrams();
        g.testCase(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});
    }

    private void testCase(String[] strs) {
        groupAnagrams(strs);
    }

    public List<List<String>> groupAnagrams(String[] strs) {


        return null;
    }

}
