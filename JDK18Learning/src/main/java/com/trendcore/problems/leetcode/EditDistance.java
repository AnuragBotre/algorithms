package com.trendcore.problems.leetcode;

import java.util.ArrayList;
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

    class References {
        int position;
        char c;
    }

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

        List<References> positionList1 = new ArrayList<>();
        List<References> positionList2 = new ArrayList<>();

        String list3[] = new String[SIZE];

        for (int i = word2.length() - 1; i >= 0; i--) {
            char c = word2.charAt(i);
            int inList1WithPos = findInList1WithPos(c, list1, pointer1);
            if (inList1WithPos != -1) {
                pointer1 = inList1WithPos;
                list2[pointer1] = "" + c;

                list3[pointer1] = "" + c;

                References r1 = new References();
                r1.position = inList1WithPos;
                r1.c = c;
                positionList1.add(r1);

                References r2 = new References();
                r2.c = c;
                r2.position = i;
                positionList2.add(r2);
            }
        }


        int operation = 0;
        int previousPosition = 0;

        int listCounter = 0;

        int lastProcessedPosition = 0;

        for (int l = positionList2.size() - 1; l >= 0; l--) {
            References references2 = positionList2.get(l);
            References references1 = positionList1.get(l);

            if (references1.position == references2.position) {
                int delta = references1.position - lastProcessedPosition;
                operation = operation + delta;
                //no-op
            } else if (references1.position < references2.position) {
                //move towards right
                int delta = references1.position - lastProcessedPosition;
                int diff = references2.position - references1.position;
                operation = operation + diff + delta;

                for (int j = l; j >= 0; j--) {
                    References references = positionList1.get(j);
                    references.position = references.position + diff;
                }

            } else {
                //move towards left
                int delta = references2.position - lastProcessedPosition;
                int diff = references1.position - references2.position;
                operation = operation + diff + delta;

                for (int j = l; j >= 0; j--) {
                    References references = positionList1.get(j);
                    references.position = references.position - diff;
                }
            }

            lastProcessedPosition = references2.position + 1;
        }


        //finally after loop delete rest of elements
        //handle this case and problem is done.


        return operation;
    }


    public int getOperationApproach1(List<References> positionList2, String[] list3, int operation, int listCounter) {
        for (int j = positionList2.size() - 1; j >= 0; j--) {
            References reference = positionList2.get(j);
            for (int i = listCounter; i < list3.length; i++) {
                if (list3[i] != null && list3[i].equals("" + list3[i])) {

                    if (i == reference.position) {
                        //no - op
                    } else if (i < reference.position) {
                        int diff = reference.position - i;
                        //move towards right by that many steps
                        list3 = shiftRight(list3, i, diff);
                        operation = operation + diff;
                    } else {
                        int diff = i - reference.position;
                        //move towards left by that many steps
                        list3 = shiftLeft(list3, i, diff);
                        operation = operation + diff;
                    }
                    listCounter = reference.position + 1;
                    break;
                } else {
                    operation++;
                }
            }
        }
        return operation;
    }

    private String[] shiftLeft(String[] list3, int pos, int noOfTimes) {
        String temp[] = new String[list3.length];
        for (int i = pos, j = pos - noOfTimes; i < list3.length; i++, j++) {
            if (j >= 0) {
                temp[j] = list3[i];
            }
        }
        return temp;
    }

    private String[] shiftRight(String[] list3, int pos, int noOfTimes) {
        String temp[] = new String[list3.length];
        for (int i = pos, j = pos + noOfTimes; i < list3.length; i++, j++) {
            if (j < temp.length) {
                temp[j] = list3[i];
            }
        }
        return temp;
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
