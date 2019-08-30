package com.trendcore.problems.leetcode;

/**
 * https://leetcode.com/problems/plus-one/
 * 66. Plus One
 * <p>
 * Given a non-empty array of digits representing a non-negative integer,
 * plus one to the integer.
 * <p>
 * The digits are stored such that the most
 * significant digit is at the head of the list,
 * and each element in the array contain a single digit.
 * <p>
 * You may assume the integer does not contain
 * any leading zero, except the number 0 itself.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,2,3]
 * Output: [1,2,4]
 * Explanation: The array represents the integer 123.
 * Example 2:
 * <p>
 * Input: [4,3,2,1]
 * Output: [4,3,2,2]
 * Explanation: The array represents the integer 4321.
 */
public class PlusOne {

    public int[] plusOne(int[] digits) {

        int output[] = new int[digits.length];
        int outputWithCarry[] = new int[digits.length + 1];

        int carry = 0;
        for (int i = digits.length - 1; i >= 0; i--) {

            int i1 = 0;
            if (i == digits.length - 1) {
                i1 = digits[i] + 1;
            } else {
                i1 = digits[i];
            }
            i1 = i1 + carry;
            if (i1 > 9) {
                carry = 1;
                output[i] = 0;
                outputWithCarry[i+1] = 0;
            } else {
                carry = 0;
                output[i] = i1;
                outputWithCarry[i+1] = i1;
            }
        }

        if (carry > 0) {
            outputWithCarry[0] = 1;
            return outputWithCarry;
        }

        return output;
    }

}
