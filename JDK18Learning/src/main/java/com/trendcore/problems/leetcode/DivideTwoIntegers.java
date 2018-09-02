package com.trendcore.problems.leetcode;

/**
 * 29. Divide Two Integers
 * https://leetcode.com/problems/divide-two-integers/description/
 * Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.
 * <p>
 * Return the quotient after dividing dividend by divisor.
 * <p>
 * The integer division should truncate toward zero.
 * <p>
 * Example 1:
 * <p>
 * Input: dividend = 10, divisor = 3
 * Output: 3
 * Example 2:
 * <p>
 * Input: dividend = 7, divisor = -3
 * Output: -2
 * Note:
 * <p>
 * Both dividend and divisor will be 32-bit signed integers.
 * The divisor will never be 0.
 * Assume we are dealing with an environment which could only store integers
 * within the 32-bit signed integer range: [−231,  231 − 1].
 * For the purpose of this problem, assume that your function returns 231 − 1
 * when the division result overflows.
 */
public class DivideTwoIntegers {

    public static void main(String[] args) {
        testCase(10, 5);
        testCase(7, 3);
        testCase(7, -3);

        testCase(Integer.MAX_VALUE, 1);
        testCase(Integer.MIN_VALUE, 1);
        testCase(Integer.MIN_VALUE, -1);
    }

    private static void testCase(int dividend, int divisor) {
        DivideTwoIntegers d = new DivideTwoIntegers();
        System.out.println(d.divide(dividend, divisor));
    }

    public int divide(int dividend, int divisor) {

        boolean negative = false;
        if (dividend < 0 && divisor > 0) {
            negative = true;
        } else if (divisor < 0 && dividend > 0) {
            negative = true;
        }

        long dividendL = 0;
        long divisorL = 0;

        if(dividend < 0 && dividend == Integer.MIN_VALUE){
            dividend = -1*  Integer.MIN_VALUE;
        }

        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);

        long count = 0;
        for (long i = divisor; i <= dividend; ) {
            i = i + divisor;
            count++;
        }

        if (negative) {

            if(count > Integer.MAX_VALUE){
                return Integer.MIN_VALUE;
            }

            return -1 * (int)count;
        }

        if(count > Integer.MAX_VALUE){
            return Integer.MAX_VALUE;
        }

        return (int) count;
    }
}
