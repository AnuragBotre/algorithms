package com.trendcore.problems.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


        List finalList = new ArrayList();
        for (int i = 0; i < strs.length; i++) {

            if (strs[i] != null) {
                String str = strs[i];
                List list = new ArrayList();
                list.add(str);

                int j;
                for (j = i+1; j < strs.length; j++) {
                    if (strs[j] != null && isAnagram(str, strs[j])) {
                        list.add(strs[j]);
                        strs[j] = null;
                    }
                }

                finalList.add(list);
            }

        }

        return finalList;
    }

    private boolean isAnagram(String str, String str1) {

        if (str.length() != str1.length())
            return false;

        int arr[] = new int[26];

        for (int i = 0; i < str.length(); i++) {
            arr[str.charAt(i) - 'a'] = arr[str.charAt(i) - 'a'] + 1;

            arr[str1.charAt(i) - 'a'] = arr[str1.charAt(i) - 'a'] - 1;
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                return false;
            }
        }

        return true;
    }

}
