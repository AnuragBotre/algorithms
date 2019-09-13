package com.trendcore.problems.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/edit-distance/
 * <p>
 * 72. Edit Distance
 * <p>
 * Given two words word1 and word2, find the minimum number of operations
 * required to convert word1 to word2.
 * <p>
 * You have the following 3 operations permitted on a word:
 * <p>
 * Insert a character
 * Delete a character
 * Replace a character
 * Example 1:
 * <p>
 * Input: word1 = "horse", word2 = "ros"
 * Output: 3
 * Explanation:
 * horse -> rorse (replace 'h' with 'r')
 * rorse -> rose (remove 'r')
 * rose -> ros (remove 'e')
 * Example 2:
 * <p>
 * Input: word1 = "intention", word2 = "execution"
 * Output: 5
 * Explanation:
 * intention -> inention (remove 't')
 * inention -> enention (replace 'i' with 'e')
 * enention -> exention (replace 'n' with 'x')
 * exention -> exection (replace 'n' with 'c')
 * exection -> execution (insert 'u')
 */
public class EditDistance {

    public int minDistance(String word1, String word2) {

        String BLANK = "#B";

        //need 2 arrays of equal size
        int SIZE = 0;

        if (word1.length() > word2.length()) {
            SIZE = word1.length();
        } else {
            SIZE = word2.length();
        }

        String list1[] = new String[SIZE];
        String list2[] = new String[SIZE];

        //align word2 with word1 from end
        //  h   o   r   s   e
        //  r   o       s

        //abc

        //whenever there is BLANK then that character needs to be removed.

        int list1Counter = 0;
        if (word1.length() < word2.length()) {

            for (; list1Counter < word2.length() - word1.length(); list1Counter++) {
                list1[list1Counter] = BLANK;
            }
        }

        for (; list1Counter < word1.length(); list1Counter++) {
            list1[list1Counter] = "" + word1.charAt(list1Counter);
        }

        /*for (int j = 0; j < word2.length(); j++) {
            list2[j] = BLANK;
        }*/

        //pointing to list1
        int pointer1 = SIZE;

        for (int i = word2.length() - 1; i >= 0; i--) {
            char c = word2.charAt(i);
            int inList1WithPos = findInList1WithPos(c, list1, pointer1);
            if (inList1WithPos != -1) {
                pointer1 = inList1WithPos;
                list2[pointer1] = "" + c;
            }
        }

        Arrays.stream(list2).forEach(System.out::println);

        int operation = 1;
        for (int i = 0, j = 0; i < word2.length() && j < list2.length; ) {
            char c = word2.charAt(i);
            if (("" + c).equals(list2[j])) {
                i++;
                j++;
            } else if (isPresentInList(list2, c, i)) {
                //char is present in the later section
                j++;
                operation++;
            } else {
                //not present at all
                operation++;
                j++;
                i++;
            }
        }

        return operation;
    }

    private boolean isPresentInList(String[] list2, char c, int pos) {

        for (int i = pos; i < list2.length; i++) {
            if (list2[i] == null) {
                if (("" + c).equals(list2[i]))
                    return true;
            } else {
                return false;
            }
        }

        return false;
    }

    private int findInList1WithPos(char c, String[] list1, int pointer1) {

        for (int i = pointer1 - 1; i >= 0; i--) {
            if (list1[i].equals("" + c)) {
                return i;
            }
        }

        return -1;
    }
}
