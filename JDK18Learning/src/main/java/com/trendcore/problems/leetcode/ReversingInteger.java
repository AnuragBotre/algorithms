package com.trendcore.problems.leetcode;

/**
 *
 * https://leetcode.com/problems/reverse-integer/description/
 * 7. Reverse Integer
 DescriptionHintsSubmissionsDiscussSolution
 Given a 32-bit signed integer, reverse digits of an integer.

 Example 1:

 Input: 123
 Output: 321
 Example 2:

 Input: -123
 Output: -321
 Example 3:

 Input: 120
 Output: 21
 Note:
 Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1].
 For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
 */
public class ReversingInteger {

    public static void main(String[] args) {
        ReversingInteger r = new ReversingInteger();
        r.reverse(123);
        r.reverse(1234);
        r.reverse(12345);
        r.reverse(123456);
    }

    public int reverse(int x) {
        reverseApproach1(x);
        return 0;
    }

    private void reverseApproach1(int x) {
        int number = x;
        int length = 0;
        int divisor = 10;

        int result=0;

        while(number > 0){

            result = result*10 + number%10;

            number = x / divisor;
            divisor = divisor*10;
            length++;

        }
        System.out.println(length + " "+ result);
    }

}
