package com.trendcore.problems.leetcode;

/**
 * https://leetcode.com/problems/length-of-last-word/
 * <p>
 * Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.
 * <p>
 * If the last word does not exist, return 0.
 * <p>
 * Note: A word is defined as a character sequence consists of non-space characters only.
 * <p>
 * Example:
 * <p>
 * Input: "Hello World"
 * Output: 5
 */
public class LengthOfLastWord {

    public static void main(String[] args) {
        LengthOfLastWord l = new LengthOfLastWord();
        l.testCase("Hello World");
        l.testCase("HelloWorld");
        l.testCase("a b c d");
        l.testCase("a b c ddd");
        l.testCase("a b ccc d");
        l.testCase("aaa bbb ccc d");
        l.testCase("a ");
    }

    private void testCase(String word) {
        int i = lengthOfLastWord(word);
        System.out.println(i);
    }

    public int lengthOfLastWord(String word) {
        String s = word.trim();
        int cnt = 0;

        for (int i = s.length() - 1; i >= 0; i--, cnt++) {
            if (s.charAt(i) == ' ') {

                break;
            }
        }
        return cnt;
    }

}
