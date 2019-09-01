package com.trendcore.problems.leetcode;

/**
 * https://leetcode.com/problems/sqrtx/
 * <p>
 * 69. Sqrt(x)
 * <p>
 * Implement int sqrt(int x).
 * <p>
 * Compute and return the square root of x,
 * where x is guaranteed to be a non-negative integer.
 * <p>
 * Since the return type is an integer,
 * the decimal digits are truncated and only the integer
 * part of the result is returned.
 * <p>
 * Example 1:
 * <p>
 * Input: 4
 * Output: 2
 * Example 2:
 * <p>
 * Input: 8
 * Output: 2
 * Explanation: The square root of 8 is 2.82842..., and since
 * the decimal part is truncated, 2 is returned.
 */
public class Sqrt {

    public int mySqrt(int x) {

        if(x == 0){
            return 0;
        }

        //input 2

        //input 3

        int previous = 1;

        for (int i = 1; i <= x / 2; i++) {

            //i*i == x

            //i*i < x -> then continue

            //i*i > x -> then previous i*i is the solution

            //i*i

            //try to find using binary search
            long temp = i * i;

            if(temp < 0){
                return previous;
            }

            if (temp == x) {
                return i;
            } else if (temp < x) {
                previous = i;
            } else {
                return previous;
            }

        }

        return previous;
    }

}
