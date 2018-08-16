package com.trendcore.problems.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/
 * <p>
 * 17. Letter Combinations of a Phone Number
 * <p>
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
 * <p>
 * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 * <p>
 * Input: "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 */
public class CombinationOfPhoneNumbers {



    public static void main(String[] args) {
        CombinationOfPhoneNumbers c = new CombinationOfPhoneNumbers();
        System.out.println(c.letterCombinations("23"));
    }

    private static Map<String, String> map;

    static {
        map = new HashMap();

        map.put("2", "abc");
        map.put("3", "def");
        map.put("4", "ghi");
        map.put("5", "jkl");
        map.put("6", "mno");
        map.put("7", "pqrs");
        map.put("8", "tuv");
        map.put("9", "wxyz");
    }

    public List<String> letterCombinations(String digits) {

        //get the strings from list
        List<String> list = new ArrayList();
        for (int i = 0; i < digits.length(); i++) {
            if (map.get(""+digits.charAt(i)) != null) {
                String s = map.get(""+digits.charAt(i));

                if (list.isEmpty()) {
                    for (int j = 0; j < s.length(); j++) {
                        list.add("" + s.charAt(j));
                    }
                } else {
                    List newList = new ArrayList();
                    for (int j = 0; j < s.length(); j++) {
                        for (int k = 0; k < list.size(); k++) {
                            newList.add(list.get(k)+s.charAt(j));
                        }
                    }
                    list = newList;
                }
            }
        }

        return list;
    }

}
