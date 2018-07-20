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
        System.out.println(r.reverse(123));
        System.out.println(r.reverse(1234));
        System.out.println(r.reverse(12345));
        System.out.println(r.reverse(123456));

        System.out.println(r.reverse(1));
        System.out.println(r.reverse(-1));
        System.out.println(r.reverse(120));
        System.out.println(r.reverse(-2147483412));
        System.out.println(r.reverse(-120));
        System.out.println(r.reverse(-123));
        System.out.println(r.reverse(-1234));
        System.out.println(r.reverse(-12345));

    }

    public int reverse(int x) {
        return reverseApproach1(x);
    }

    private int reverseApproach1(int x) {

        int number = x;
        int newX = x;

        int divisor = 10;

        int result=0;

        while(number != 0){
            result = result*10 + number%10;
            number = newX / divisor;
            divisor = divisor*10;
        }

        /*if(x < 0){
            result = result*-1;
        }else if(x > 0 && result < 0){
            return 0;
        }*/
        return result;
    }

}
