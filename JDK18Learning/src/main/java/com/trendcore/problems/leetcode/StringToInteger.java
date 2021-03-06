package com.trendcore.problems.leetcode;

/**
 * https://leetcode.com/problems/string-to-integer-atoi/description/
 * 8. String to Integer (atoi)
 * Implement atoi which converts a string to an integer.
 * <p>
 * The function first discards as many whitespace characters as necessary until the
 * first non-whitespace character is found.
 * Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible,
 * and interprets them as a numerical value.
 * <p>
 * The string can contain additional characters after those that form the integral number,
 * which are ignored and have no effect on the behavior of this function.
 * <p>
 * If the first sequence of non-whitespace characters in str is not a valid integral number,
 * or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.
 * <p>
 * If no valid conversion could be performed, a zero value is returned.
 * <p>
 * Note:
 * <p>
 * Only the space character ' ' is considered as whitespace character.
 * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer
 * range: [−231,  231 − 1]. If the numerical value is out of the range of representable values,
 * INT_MAX (231 − 1) or INT_MIN (−231) is returned.
 * Example 1:
 * <p>
 * Input: "42"
 * Output: 42
 * Example 2:
 * <p>
 * Input: "   -42"
 * Output: -42
 * Explanation: The first non-whitespace character is '-', which is the minus sign.
 * Then take as many numerical digits as possible, which gets 42.
 * Example 3:
 * <p>
 * Input: "4193 with words"
 * Output: 4193
 * Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.
 * Example 4:
 * <p>
 * Input: "words and 987"
 * Output: 0
 * Explanation: The first non-whitespace character is 'w', which is not a numerical
 * digit or a +/- sign. Therefore no valid conversion could be performed.
 * Example 5:
 * <p>
 * Input: "-91283472332"
 * Output: -2147483648
 * Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer.
 * Thefore INT_MIN (−231) is returned.
 */
public class StringToInteger {


    public static void main(String[] args) {
        StringToInteger s = new StringToInteger();
        System.out.println(s.myAtoi("42"));
        System.out.println(s.myAtoi("-42"));
        System.out.println(s.myAtoi("--42"));
        System.out.println(s.myAtoi("    -42"));
        System.out.println(s.myAtoi("word 42"));
        System.out.println(s.myAtoi("4 2"));
        System.out.println(s.myAtoi("-91283472332"));
        System.out.println(s.myAtoi("+-2"));
        System.out.println(s.myAtoi("+1"));
        System.out.println(s.myAtoi("-2147483647"));
        System.out.println(s.myAtoi("9223372036854775808"));
        System.out.println(s.myAtoi("-9223372036854775808"));
        System.out.println(s.myAtoi("0-1"));
    }

    public int myAtoi(String str) {

        boolean negativeSign = false;
        boolean repeated=false;
        long result = 0;

        //using tokenizer approach
        for (int i = 0; i < str.length(); i++) {
            //locate + or - or 0-9
            //then parse till 0-9
            if(!((str.charAt(i) >= '0' && str.charAt(i) <= '9') || str.charAt(i) == '+' || str.charAt(i) == '-') && str.charAt(i) != ' '){
                break;
            }


            if ((str.charAt(i) >= '0' && str.charAt(i) <= '9') || str.charAt(i) == '+' || str.charAt(i) == '-') {
                for (int pointer = i; pointer < str.length(); pointer++) {
                    if (!((str.charAt(pointer) >= '0' && str.charAt(pointer) <= '9') || str.charAt(pointer) == '+' || str.charAt(pointer) == '-')) {
                        break;
                    }

                    if (repeated && (str.charAt(pointer) == '-' || str.charAt(pointer) == '+')) {
                        break;
                    }

                    if (str.charAt(pointer) == '-' && !negativeSign) {
                        negativeSign = true;
                        repeated=true;
                        continue;
                    }

                    if (str.charAt(pointer) == '+') {
                        repeated=true;
                        continue;
                    }

                    if (str.charAt(pointer) >= '0' && str.charAt(pointer) <= '9') {

                        for(;pointer < str.length() ; pointer++){

                            if(!(str.charAt(pointer) >= '0' && str.charAt(pointer) <= '9')){
                                break;
                            }

                            result = result * 10 + (str.charAt(pointer) - 48);


                            if (result <= Integer.MIN_VALUE || result >= Integer.MAX_VALUE) {
                                if(negativeSign) {
                                    result = result * -1;
                                }
                                if(result <= Integer.MIN_VALUE){
                                    return Integer.MIN_VALUE;
                                }else if(result >= Integer.MAX_VALUE){
                                    return Integer.MAX_VALUE;
                                }else{
                                    return (int) result;
                                }
                            }
                        }

                        break;

                    } else {
                        break;
                    }
                }
                break;
            }
        }

        if (negativeSign) {
            result = result * -1;
        }

        if (result <= Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        } else if (result >= Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }

        return (int) result;
    }

    private int skelaton(String str) {
        int charPointer = -1;

        long result = 0;
        boolean negativeSign = false;

        for (int i = 0; i < str.length(); i++) {

            if ((str.charAt(i) <= '0' || str.charAt(i) >= '9') && str.charAt(i) != '-' && str.charAt(i) != ' ' && result == 0) {
                break;
            }

            if (negativeSign && str.charAt(i) == '-') {
                break;
            }

            if (str.charAt(i) == '-' && !negativeSign) {
                negativeSign = true;
            }

            if (str.charAt(i) >= '0' && str.charAt(i) <= '9' && charPointer + 1 == i) {
                result = result * 10 + (str.charAt(i) - 48);
            }
            charPointer = i;
        }

        if (negativeSign) {
            result = result * -1;
        }

        if (result <= Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        } else if (result >= Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }

        return (int) result;
    }


}
