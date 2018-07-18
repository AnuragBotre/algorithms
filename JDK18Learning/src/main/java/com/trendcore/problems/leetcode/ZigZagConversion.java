package com.trendcore.problems.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/zigzag-conversion/description/
 * 6. ZigZag Conversion
 *
 The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

 P   A   H   N
 A P L S I I G
 Y   I   R
 And then read line by line: "PAHNAPLSIIGYIR"

 Write the code that will take a string and make this conversion given a number of rows:

 string convert(string s, int numRows);
 Example 1:

 Input: s = "PAYPALISHIRING", numRows = 3
 Output: "PAHNAPLSIIGYIR"
 Example 2:

 Input: s = "PAYPALISHIRING", numRows = 4
 Output: "PINALSIGYAHRPI"
 Explanation:

 P     I    N
 A   L S  I G
 Y A   H R
 P     I
 */
public class ZigZagConversion {

    public static void main(String[] args) {
        ZigZagConversion z = new ZigZagConversion();
        System.out.println(z.convert("PAYPALISHIRING",4));
    }

    public String convert(String s, int numRows) {

        int j=0;
        List c[]=new List[numRows];

        boolean zig=true;

        for(int i = 0 ; i < s.length() ; i++){

            if(zig) {
                int a;
                for (j = 0 ,a=i; j < numRows && a < s.length(); a++,j++) {
                    if(c[j] == null){
                        c[j] = new ArrayList();
                    }
                    c[j].add(s.charAt(a));
                    //System.out.println(s.charAt(j));
                }
                zig = false;
                i=a-1;
                j--;
            }else{
                j--;
                if(j==0){
                    i--;
                    zig=true;
                    continue;
                }
                c[j].add(s.charAt(i));

            }
        }

        return "";
    }

}
