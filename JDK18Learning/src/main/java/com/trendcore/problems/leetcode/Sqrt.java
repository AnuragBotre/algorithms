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

        return usingLastMidApproach(x);
    }

    public int linearApproach(int x) {
        if (x == 0) {
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

            if (temp < 0) {
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

    public int usingBinarySearchLeetCodeApproach(int x) {

        if (x == 0 || x == 1) return x;

        // Binary Search
        int left = 0, right = x;
        while (left < right) {
            // mid = (left + right) / 2 can overflow if right > Integer.MAX_VALUE - left ( right + left > Integer.MAX_VALUE)
            int mid = left + (right - left) / 2;
            //int mid = left + (right) / 2;

            // same thing here , mid * mid > x can overflow. replace by mid > x / mid
            if (mid > x / mid) {
                right = mid - 1;
            } else {
                left = mid + 1;
                // if mid * mid < x but (mid + 1) * (mid + 1) > x then mid was then right answer
                if (left > x / left) {
                    return mid;
                }
            }
        }

        return left;
    }

    private int usingLastMidApproach(int x) {

        if (x < 2) {
            return x;
        }

        int no = x;
        int mid;
        int lastMid = mid = x / 2;

        int executionOrder = 1;

        for (; true; ) {


            if (mid * mid == x) {
                return mid;
            } else if ((mid * mid > x) || (mid * mid < 0)) {

                lastMid = mid;
                mid = mid / 2;

                System.out.println(" executionOrder :- " + executionOrder + " mid = " + mid);

                if (mid * mid < x && (mid + 1) * (mid + 1) > x) {
                    return mid;
                }

            } else {

                String x1 = " executionOrder :- " + executionOrder + " mid = " + mid + " lastMid = " + lastMid + " addition of :- " + (mid + lastMid);
                System.out.println(x1);

                mid = (lastMid + mid) / 2;

                if (mid * mid > x && ((mid + 1) * (mid + 1) > x)) {
                    return mid;
                } else {
                    //need to find minimal no in case of overflow
                    break;
                }


            }

            executionOrder++;
        }


        //everything is overflown after mid+1;
        //find the smallest no which not overflow and closest to x
        System.out.println("executionOrder "+(executionOrder++)+" Loop Break, mid ->  " + mid + ", lastMid " + lastMid);

        return mid;

    }

}
