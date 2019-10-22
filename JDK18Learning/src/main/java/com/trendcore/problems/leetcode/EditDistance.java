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


    class Traversal {
        int minHeight = 0;

        boolean initialized = false;


        private void traverse(String word1, int index, String word2, int height) {

            //terminating conditions.
            if (word1.equals(word2)) {
                if (!initialized) {
                    minHeight = height;
                    initialized = true;
                } else {
                    if (minHeight > height) {
                        minHeight = height;
                    }
                }
                return;
            } else {
                //if index goes beyond word1
                if (initialized) {
                    if (height > minHeight)
                        return;
                }

                ifEqualThenProceed(word1, index, word2, height);
                replaceIfPermitted(word1, index, word2, height);
                addIfPermitted(word1, index, word2, height);
                removeIfPermitted(word1, index, word2, height);
            }
        }

        private void removeIfPermitted(String word1, int index, String word2, int height) {
            if (word1.length() < word2.length()) {
                return;
            }

            String s = removeChar(word1, index);
            traverse(s, index, word2, height + 1);
        }

        private void addIfPermitted(String word1, int index, String word2, int height) {
            if (index >= word2.length()) {
                return;
            }
            String s = addChar(word1, index, word2);
            traverse(s, index + 1, word2, height + 1);
        }

        private void ifEqualThenProceed(String word1, int index, String word2, int height) {
            if (index >= word2.length() || index >= word1.length()) {
                return;
            }

            if (word1.charAt(index) == word2.charAt(index)) {
                traverse(word1, index + 1, word2, height);
            }
        }

        private void replaceIfPermitted(String word1, int index, String word2, int height) {
            if (index >= word2.length() || index >= word1.length()) {
                return;
            }

            String s = replaceChar(word1, index, word2);
            traverse(s, index + 1, word2, height + 1);
        }

    }

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

        Traversal t = new Traversal();

        int height = 0;
        t.traverse(word1, 0, word2, height);

        return t.minHeight;
    }


    String removeChar(String word1, int index) {
        int i = 0;
        int cnt;

        char[] chars = word1.toCharArray();
        char[] temp = new char[word1.length() - 1];

        if (index >= word1.length()) {

            for (i = 0; i < word1.length() - 1; i++) {
                temp[i] = chars[i];
            }

        } else {

            int j = 0;
            for (; i < index; ) {
                temp[j] = chars[i];
                j++;
                i++;
            }

            i++;

            for (; i < word1.length(); ) {
                temp[j] = chars[i];
                i++;
                j++;
            }

        }

        return new String(temp);
    }

    String addChar(String word1, int index, String word2) {
        char c = word2.charAt(index);

        char[] chars = word1.toCharArray();

        char temp[] = new char[chars.length + 1];

        if (index >= chars.length) {
            //add to last
            int i = 0;
            for (i = 0; i < chars.length; ) {
                temp[i] = chars[i];
                i++;
            }

            temp[i] = c;
            return new String(temp);
        } else {

            //copy first part
            int i;
            int j = 0;
            for (i = 0; j < index; ) {
                temp[i] = chars[j];
                i++;
                j++;
            }

            //copy index
            temp[i] = c;
            i++;
            //copy rest of the part
            for (; j < word1.length(); ) {
                temp[i] = chars[j];
                i++;
                j++;
            }

            return new String(temp);
        }

    }

    String replaceChar(String word1, int index, String word2) {
        char c = word2.charAt(index);
        char[] chars = word1.toCharArray();
        chars[index] = c;
        return new String(chars);
    }

}
