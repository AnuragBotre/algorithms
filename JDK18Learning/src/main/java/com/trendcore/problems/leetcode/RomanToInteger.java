package com.trendcore.problems.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/roman-to-integer/
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 * <p>
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * For example, two is written as II in Roman numeral, just two one's added together.
 * Twelve is written as, XII, which is simply X + II.
 * The number twenty seven is written as XXVII, which is XX + V + II.
 * <p>
 * Roman numerals are usually written largest to smallest from left to right.
 * However, the numeral for four is not IIII. Instead, the number four is written as IV.
 * Because the one is before the five we subtract it making four.
 * The same principle applies to the number nine, which is written as IX.
 * There are six instances where subtraction is used:
 * <p>
 * I can be placed before V (5) and X (10) to make 4 and 9.
 * X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * Given a roman numeral, convert it to an integer.
 * Input is guaranteed to be within the range from 1 to 3999.
 * <p>
 * Example 1:
 * <p>
 * Input: "III"
 * Output: 3
 * Example 2:
 * <p>
 * Input: "IV"
 * Output: 4
 * Example 3:
 * <p>
 * Input: "IX"
 * Output: 9
 * Example 4:
 * <p>
 * Input: "LVIII"
 * Output: 58
 * Explanation: C = 100, L = 50, XXX = 30 and III = 3.
 * Example 5:
 * <p>
 * Input: "MCMXCIV"
 * Output: 1994
 * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 */
public class RomanToInteger {

    public static void main(String[] args) {
        RomanToInteger r = new RomanToInteger();

        System.out.println(r.romanToInt("I"));
        System.out.println(r.romanToInt("III"));
        System.out.println(r.romanToInt("IV"));
        System.out.println(r.romanToInt("VI"));
        System.out.println(r.romanToInt("IX"));
        System.out.println(r.romanToInt("MCMXCIV"));
        System.out.println(r.romanToInt("MM"));
        System.out.println(r.romanToInt("CM"));
        System.out.println(r.romanToInt("LVII"));
    }

    private static Map<Character, Integer> map = new HashMap<>();;

    static {
        putInMap(map, 'I', 1);
        putInMap(map, 'V', 5);
        putInMap(map, 'X', 10);
        putInMap(map, 'L', 50);
        putInMap(map, 'C', 100);
        putInMap(map, 'D', 500);
        putInMap(map, 'M', 1000);
    }

    public int romanToInt(String s) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i + 1 < s.length()) {
                if (map.get(s.charAt(i)) < map.get(s.charAt(i + 1))) {
                    result = result + map.get(s.charAt(i + 1)) - map.get(s.charAt(i));
                    i++;
                } else {
                    result = result + map.get(s.charAt(i));
                }
            } else {
                result = result + map.get(s.charAt(i));
            }
        }

        return result;
    }

    private static void putInMap(Map<Character,Integer> map, Character i, int i1) {
        map.put(i, i1);
    }

}
