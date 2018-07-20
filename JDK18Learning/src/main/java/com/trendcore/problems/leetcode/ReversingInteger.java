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
        System.out.println(r.reverse(-2147483648));
        System.out.println(r.reverse(-120));
        System.out.println(r.reverse(-123));
        System.out.println(r.reverse(-1234));
        System.out.println(r.reverse(-12345));
        System.out.println(r.reverse(1534236469));


    }

    public int reverse(int x) {
        return reverseApproach1(x);
    }

    private int reverseApproach1(int number) {

        if(number >= Integer.MAX_VALUE || number <= Integer.MIN_VALUE)
            return 0;

        long result=0;
        long tempResult;

        while(number != 0){
            tempResult = result * 10 + number % 10;
            if(tempResult >= Integer.MAX_VALUE || tempResult <= Integer.MIN_VALUE)
                return 0;

            result = tempResult;
            number = (number / 10);
        }

        return (int) result;
    }

}
