package com.trendcore.problems.leetcode;

/**
 * https://leetcode.com/problems/palindrome-number/description/
 * 9. Palindrome Number
 Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.

 Example 1:

 Input: 121
 Output: true
 Example 2:

 Input: -121
 Output: false
 Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
 Example 3:

 Input: 10
 Output: false
 Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
 Follow up:

 Coud you solve it without converting the integer to a string?
 */
public class PalindromeNumber {

    public static void main(String[] args) {
        PalindromeNumber p = new PalindromeNumber();
        System.out.println(p.isPalindrome(-12));
        System.out.println(p.isPalindrome(123));
        System.out.println(p.isPalindrome(121));
        System.out.println(p.isPalindrome(1221));
        System.out.println(p.isPalindrome(1212));
    }

    public boolean isPalindrome(int x) {
        if(x < 0)
            return false;


        int reverse = x;

        int result = 0;

        while(reverse != 0){
            result = result*10+reverse % 10;
            reverse = reverse / 10;
        }

        return x == result;
    }

}
