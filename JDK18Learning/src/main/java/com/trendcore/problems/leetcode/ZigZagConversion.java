package com.trendcore.problems.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/zigzag-conversion/description/
 * 6. ZigZag Conversion
 * <p>
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
 * <p>
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 * <p>
 * Write the code that will take a string and make this conversion given a number of rows:
 * <p>
 * string convert(string s, int numRows);
 * Example 1:
 * <p>
 * Input: s = "PAYPALISHIRING", numRows = 3
 * Output: "PAHNAPLSIIGYIR"
 * Example 2:
 * <p>
 * Input: s = "PAYPALISHIRING", numRows = 4
 * Output: "PINALSIGYAHRPI"
 * Explanation:
 * <p>
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 */
public class ZigZagConversion {

    public static void main(String[] args) {
        ZigZagConversion z = new ZigZagConversion();
        System.out.println(z.convert("PAYPALISHIRING", 4));
        System.out.println(z.convert("", 1));
        System.out.println(z.convert("A", 2));
    }

    public String convert(String s, int numRows) {

        for(int i=0 ; i < s.length() ; i++){

        }

        return leetcodeApproach(s,numRows);
    }

    public String leetcodeApproach(String s, int numRows) {

        if (numRows == 1)
            return s;

        StringBuilder ret = new StringBuilder();
        int n = s.length();
        int cycleLen = 2 * numRows - 2;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < n; j += cycleLen) {
                ret.append(s.charAt(j + i));
                if (i != 0 && i != numRows - 1 && j + cycleLen - i < n)
                    ret.append(s.charAt(j + cycleLen - i));
            }
        }
        return ret.toString();
    }

    private String approach1(String s, int numRows) {
        if (numRows > s.length() || numRows <= 1) {
            return s;
        }

        int j = 0;
        String c[] = new String[numRows];

        for(int i = 0 ; i < c.length ; i++){
            c[i] = "";
        }

        boolean zig = true;

        String r1 = "";

        for (int i = 0; i < s.length(); i++) {

            if (zig) {
                int a;
                for (j = 0, a = i; j < numRows && a < s.length(); a++, j++) {
                    if (c[j] == null) {
                        c[j] = ""+s.charAt(a);
                    }else{
                        c[j] = c[j] + s.charAt(a);
                    }
                }
                zig = false;
                i = a - 1;
                j--;
            } else {
                j--;
                if (j <= 0) {
                    i--;
                    zig = true;
                    continue;
                }
                c[j] = c[j] + s.charAt(i);
            }
        }

        String r = "";
        for (int i = 0; i < c.length; i++) {
            r = r + c[i];
        }

        return r;
    }

}
