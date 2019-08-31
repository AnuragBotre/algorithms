package com.trendcore.problems.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/text-justification/
 * <p>
 * 68. Text Justification
 * <p>
 * Given an array of words and a width maxWidth,
 * format the text such that each line has exactly maxWidth characters and
 * is fully (left and right) justified.
 * <p>
 * You should pack your words in a greedy approach;
 * that is, pack as many words as you can in each line.
 * Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.
 * <p>
 * Extra spaces between words should be distributed as evenly as possible.
 * If the number of spaces on a line do not divide evenly between words,
 * the empty slots on the left will be assigned more spaces than the slots on the right.
 * <p>
 * For the last line of text, it should be left justified and no extra space
 * is inserted between words.
 * <p>
 * Note:
 * <p>
 * A word is defined as a character sequence consisting of non-space characters only.
 * Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
 * The input array words contains at least one word.
 * Example 1:
 * <p>
 * Input:
 * words = ["This", "is", "an", "example", "of", "text", "justification."]
 * maxWidth = 16
 * Output:
 * [
 * "This    is    an",
 * "example  of text",
 * "justification.  "
 * ]
 * Example 2:
 * <p>
 * Input:
 * words = ["What","must","be","acknowledgment","shall","be"]
 * maxWidth = 16
 * Output:
 * [
 * "What   must   be",
 * "acknowledgment  ",
 * "shall be        "
 * ]
 * Explanation: Note that the last line is "shall be    " instead of "shall     be",
 * because the last line must be left-justified instead of fully-justified.
 * Note that the second line is also left-justified becase it contains only one word.
 * Example 3:
 * <p>
 * Input:
 * words = ["Science","is","what","we","understand","well","enough","to","explain",
 * "to","a","computer.","Art","is","everything","else","we","do"]
 * maxWidth = 20
 * Output:
 * [
 * "Science  is  what we",
 * "understand      well",
 * "enough to explain to",
 * "a  computer.  Art is",
 * "everything  else  we",
 * "do                  "
 * ]
 */
public class TextJustification {

    public List<String> fullJustify(String[] words, int maxWidth) {

        List<String> resultList = new ArrayList();

        for (int i = 0; i < words.length; ) {

            int temp = maxWidth;

            List<String> intermediateWordList = new ArrayList<>();

            int selectedWordsSize = 0;

            //find how may words can be fit in
            for (; i < words.length && temp >= 0; ) {
                String word = words[i];
                if (word.length() > temp) {
                    if (intermediateWordList.isEmpty()) {
                        intermediateWordList.add(word);
                        i++;
                        selectedWordsSize = selectedWordsSize + word.length();
                        temp = temp - (word.length() + 1);
                    }
                    break;
                }
                temp = temp - (word.length() + 1);
                intermediateWordList.add(word);
                i++;
                selectedWordsSize = selectedWordsSize + word.length();
            }

            int noOfWords = intermediateWordList.size();

            //we cant justify single word
            //and dont justify last sentence
            if (noOfWords > 1 && i < words.length) {

                //padding with spaces
                int availableSpaces = maxWidth - selectedWordsSize;
                int spacesToBeAdded = availableSpaces / (noOfWords - 1);
                int remainingSpaces = availableSpaces % (noOfWords - 1);

                String s = "";
                for (int k = 0; k < intermediateWordList.size(); k++) {
                    if (k == 0) {
                        s = s + intermediateWordList.get(k);
                    } else {
                        //append no of spaces
                        //then append word
                        for (int aa = 0; aa < spacesToBeAdded; aa++) {
                            s = s + " ";
                        }
                        if (remainingSpaces > 0) {
                            s = s + " ";
                            remainingSpaces--;
                        }
                        s = s + intermediateWordList.get(k);

                    }
                }

                resultList.add(s);
            } else {
                String s = "";
                for (int k = 0; k < intermediateWordList.size(); k++) {
                    if (k == 0) {
                        s = s + intermediateWordList.get(k);
                    } else {
                        s = s + " ";
                        s = s + intermediateWordList.get(k);
                    }
                }
                if (s.length() < maxWidth) {
                    for (int m = s.length(); m < maxWidth; m++) {
                        s = s + " ";
                    }
                }

                resultList.add(s);
            }

        }


        return resultList;
    }

}
