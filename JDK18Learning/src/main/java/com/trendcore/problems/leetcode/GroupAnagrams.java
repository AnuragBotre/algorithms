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

        //load in map
        Map map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            map.put(strs[i], true);
        }

        List finalList = new ArrayList();
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            List list = new ArrayList();
            list.add(str);

            int j;
            for (j = i; j < strs.length ; j++) {
                if(isAnagram(map,str,strs[j])){
                    list.add(strs[j]);
                }
            }
            i = j;

            finalList.add(list);
        }

        return finalList;
    }

    private boolean isAnagram(Map map, String str, String str1) {

        if(str.length() != str1.length())
            return false;



        return true;
    }

}
