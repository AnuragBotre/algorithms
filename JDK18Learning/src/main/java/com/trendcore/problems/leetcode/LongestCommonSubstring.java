package com.trendcore.problems.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Problem of my own creation.
 */
public class LongestCommonSubstring {

    public static void main(String[] args) {
        LongestCommonSubstring l = new LongestCommonSubstring();
        l.longestCommonSubstring(new String[]{"flower", "flow", "flight"});
    }

    public String longestCommonSubstring(String[] strs) {
        //process input arrays
        int minLength = 0;

        List list = new ArrayList();


        for (int i = 0; i < strs.length; i++) {
            if (strs[i].length() < minLength) {
                minLength = strs[i].length();
            }

            Map map = new HashMap();
            for (int j = 0; j < strs[i].length(); j++) {
                if (map.get(strs[i].charAt(j)) != null) {
                    List list1 = (List) map.get(strs[i].charAt(j));
                    list1.add(j);
                } else {
                    List list1 = new ArrayList();
                    list1.add(j);
                    map.put(strs[i].charAt(j), list1);
                }
            }

            list.add(map);
        }

        String result = "";

        for (int i = 0; i < strs[minLength].length(); i++) {
            char c = strs[minLength].charAt(i);

            //starting with this characters position
            //

        }

        return result;
    }
}
