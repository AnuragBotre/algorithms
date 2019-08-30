package com.trendcore.problems.leetcode;

/**
 * https://leetcode.com/problems/add-binary/
 * 67. Add Binary
 * Given two binary strings, return their sum (also a binary string).
 * <p>
 * The input strings are both non-empty and contains only characters 1 or 0.
 * <p>
 * Example 1:
 * <p>
 * Input: a = "11", b = "1"
 * Output: "100"
 * Example 2:
 * <p>
 * Input: a = "1010", b = "1011"
 * Output: "10101"
 */
public class AddBinary {

    public String addBinary(String a, String b) {

        int outputLen = a.length() > b.length() ? a.length() : b.length();
        char output[] = new char[outputLen];

        char anotherOutput[] = new char[outputLen + 1];

        char carry = '0';

        for (int i = a.length() - 1,
             j = b.length() - 1,
             k = outputLen - 1,
             l = anotherOutput.length - 1; k >= 0 ; i--, j--, k--, l--) {

            char c1 = getChar(a, i);
            char c2 = getChar(b, j);

            //String x = c1 + " " + c2 + " " + carry;

            char[] addition = getAddition(c1, c2 , carry);

            //System.out.println(x + " Result :- " + addition[0] + " carry :-"+ addition[1]);

            char finalResult[] = addition;
            /*if (k == outputLen - 1) {
                finalResult = addition;
            } else {
                finalResult = getAddition(addition[0], addition[1]);
            }*/

            carry = finalResult[1];
            output[k] = finalResult[0];
            anotherOutput[l] = finalResult[0];
        }

        if (carry == '1') {
            anotherOutput[0] = '1';
            return new String(anotherOutput);
        }

        return new String(output);

    }

    public char getChar(String a, int i) {
        char c1;
        if (i >= 0) {
            c1 = a.charAt(i);
        } else {
            c1 = '0';
        }
        return c1;
    }

    public char[] getAddition(char c, char c1, char carry) {

        if (c == '0' && c1 == '0') {

            if(carry == '1')
                return new char[]{'1', '0'};
            else
                return new char[]{'0', '0'};
        } else if (c == '0' && c1 == '1') {

            if(carry == '1')
                return new char[]{'0', '1'};
            else
                return new char[]{'1', '0'};

        } else if (c == '1' && c1 == '0') {
            if(carry == '1')
                return new char[]{'0', '1'};
            else
                return new char[]{'1', '0'};
        } else {
            if(carry == '1')
                return new char[]{'1', '1'};
            else
                return new char[]{'0', '1'};
        }

    }

}
