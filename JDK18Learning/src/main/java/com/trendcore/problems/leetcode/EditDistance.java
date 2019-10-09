package com.trendcore.problems.leetcode;

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

    int minHeight = 0;

    boolean initialized = false;

    public int minDistance(String word1, String word2) {

        //compare word by word
        //if words are equal
        //proceed
        //else
        //perform one of three operation
            //while performing 3 operation
            //replace and add can be done easily
            //delete may not work
        //then proceed

        int height = 0;
        traverse(word1, 0, word2, height);

        return minHeight;
    }

    private void traverse(String word1, int index, String word2, int height) {
        if (word1.equals(word2)) {
            if (!initialized) {
                minHeight = height;
            } else {
                if (height < minHeight) {
                    minHeight = height;
                }
            }
        } else {

            if (index > word2.length()) {
                return;
            }

            if (index < word1.length() && index < word2.length()) {
                String s = replaceChar(word1, index, word2);
                traverse(s, index + 1, word2, height + 1);
            }

            if (index < word2.length()) {
                String s = addChar(word1, index, word2);
                traverse(s, index + 1, word2, height + 1);
            }
        }
    }

    private String addChar(String word1, int index, String word2) {
        char c = word2.charAt(index);
        char c1 = word1.charAt(index);

        char[] chars = word1.toCharArray();

        char temp[] = new char[chars.length + 1];
        //copy first part
        int i;
        int j = 0;
        for (i = 0; i < index; i++) {
            temp[i] = chars[j];
            j++;
        }

        //copy index
        temp[i] = c;

        //copy rest of the part
        for(i = i+1 ; i < word1.length() ; i++){
            temp[i] = chars[j];
        }

        return new String(temp);
    }

    private String replaceChar(String word1, int index, String word2) {
        char c = word2.charAt(index);
        char[] chars = word1.toCharArray();
        chars[index] = c;
        return new String(chars);
    }
}
