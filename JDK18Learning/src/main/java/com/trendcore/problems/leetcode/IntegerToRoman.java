package com.trendcore.problems.leetcode;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/integer-to-roman/description/
 * 12. Integer to Roman
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
 * However, the numeral for four is not IIII.
 * Instead, the number four is written as IV.
 * Because the one is before the five we subtract it making four.
 * The same principle applies to the number nine, which is written as IX.
 * There are six instances where subtraction is used:
 * <p>
 * I can be placed before V (5) and X (10) to make 4 and 9.
 * X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * Given an integer, convert it to a roman numeral. Input is guaranteed to be within the range from 1 to 3999.
 * <p>
 * Example 1:
 * Input: 3
 * Output: "III"
 * <p>
 * Example 2:
 * Input: 4
 * Output: "IV"
 * <p>
 * Example 3:
 * Input: 9
 * Output: "IX"
 * <p>
 * Example 4:
 * Input: 58
 * Output: "LVIII"
 * Explanation: C = 100, L = 50, XXX = 30 and III = 3.
 * <p>
 * Example 5:
 * Input: 1994
 * Output: "MCMXCIV"
 * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 */
public class IntegerToRoman {

    public static void main(String[] args) {
        IntegerToRoman i = new IntegerToRoman();
        System.out.println(i.intToRoman(123));
        System.out.println(i.intToRoman(3));
        System.out.println(i.intToRoman(4));
        System.out.println(i.intToRoman(9));
        System.out.println(i.intToRoman(58));
        System.out.println(i.intToRoman(1994));
    }


    public String intToRoman(int num) {

        Map<Integer, String> map = new LinkedHashMap<>();
        putInMap(map, "I", 1);
        putInMap(map, "V", 5);
        putInMap(map, "X", 10);
        putInMap(map, "L", 50);
        putInMap(map, "C", 100);
        putInMap(map, "D", 500);
        putInMap(map, "M", 1000);

        int result = 0;

        int temp = 1;
        int counter = 0;

        String s = "";

        while (num > 0) {
            int i = num % 10 * temp;
            result = i + result;
            num = num / 10;
            temp = temp * 10;

            s = convertNumber(map, i, temp) + s;
        }
        return s;
    }

    private void putInMap(Map<Integer, String> map, String str, int number) {
        map.put(number, str);
    }

    private String convertNumber(Map<Integer, String> map, int num, int temp) {
        //analyze no
        String prevEntry = null;
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            if (entry.getKey() >= num) {
                int diff = entry.getKey() - num;
                int t = diff / (temp / 10);

                if (diff == 0) {
                    //then return key
                    return entry.getValue();
                } else if (diff / (temp / 10) > 3) {
                    return prevEntry + entry.getValue();
                } else {
                    String s = "";
                    for (int k = 0; k < num / (temp / 10); k++) {
                        s = s + prevEntry;
                    }
                    return s;
                }
            }
            prevEntry = entry.getValue();
        }
        return prevEntry;
    }
}
