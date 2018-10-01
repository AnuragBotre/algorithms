package com.trendcore.problems.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/count-and-say/description/
 * 38. Count and Say
 * The count-and-say sequence is the sequence of integers with the first five terms as following:
 * <p>
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, then one 1" or 1211.
 * <p>
 * Given an integer n where 1 ≤ n ≤ 30, generate the nth term of the count-and-say sequence.
 * <p>
 * Note: Each term of the sequence of integers will be represented as a string.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: 1
 * Output: "1"
 * Example 2:
 * <p>
 * Input: 4
 * Output: "1211"
 */
public class CountAndSay {

    private Map map = new HashMap();

    {
        map.put(1,"1");
        map.put(2,"11");
        map.put(3,"21");
        map.put(4,"1211");
        map.put(5,"111221");
    }

    public static void main(String[] args) {
        CountAndSay c = new CountAndSay();
        c.testCase(1);
        c.testCase(4);
    }

    private void testCase(int n) {
        System.out.println(countAndSay(n));
    }

    public String countAndSay(int n) {
        return null;
    }

}
