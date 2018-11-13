package com.trendcore.problems.leetcode;


/**
 * 50. Pow(x, n)
 * <p>
 * https://leetcode.com/problems/powx-n/
 * <p>
 * Implement pow(x, n), which calculates x raised to the power n (xn).
 * <p>
 * Example 1:
 * <p>
 * Input: 2.00000, 10
 * Output: 1024.00000
 * Example 2:
 * <p>
 * Input: 2.10000, 3
 * Output: 9.26100
 * Example 3:
 * <p>
 * Input: 2.00000, -2
 * Output: 0.25000
 * Explanation: 2-2 = 1/22 = 1/4 = 0.25
 * Note:
 * <p>
 * -100.0 < x < 100.0
 * n is a 32-bit signed integer, within the range [−231, 231 − 1]
 */
public class PowXToN {

    public static void main(String[] args) {

        PowXToN p = new PowXToN();
        p.testCase(2.000, 10);
        p.testCase(2.10000, 3);
        p.testCase(2, Integer.MAX_VALUE);
        p.testCase(2, -2);
        p.testCase(0.00001, 2147483647);
        main1(null);
    }

    private void testCase(double x, int n) {
        System.out.println(" x:- " + x + " n :- " + n + " Power :- " + myPow(x, n));
    }

    public double myPow(double x, int n) {

        if (n == 0) {
            return 1;
        } else if (n > 0) {

            double r = 1;

            for (int i = 0; i < n; i++) {
                r = r * x;
            }
            return r;

        } else {
            double r = 1;

            for (int i = 0; i < Math.abs(n); i++) {
                r = r / x;
            }
            return r;

        }

    }

    static float power(float x, int y) {
        float temp;
        if (y == 0)
            return 1;
        temp = power(x, y / 2);

        if (y % 2 == 0)
            return temp * temp;
        else {
            if (y > 0)
                return x * temp * temp;
            else
                return (temp * temp) / x;
        }
    }

    /* Program to test function power */
    public static void main1(String[] args) {
        float x = 2;
        int y = -3;
        System.out.printf("%f", power(x, y));
        System.out.printf("%f", power(2, 4));
    }

}
