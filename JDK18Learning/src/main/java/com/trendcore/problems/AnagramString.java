package com.trendcore.problems;

/**
 * Created by Anurag
 */
public class AnagramString {

    public static void main(String[] args) {
        String abc = "geeksforgeeks";

        String pqr = "geeksforgeeks";

        int length = 0;

        for (int i = 0; i < abc.length(); i++) {
            if (pqr.indexOf(abc.charAt(i)) > -1) {
                length++;
            }
        }

        System.out.println(length == abc.length() ? (abc.length() == pqr.length() ? "Anagram" : "Not Anagram") : "Not anagram");
    }

}
