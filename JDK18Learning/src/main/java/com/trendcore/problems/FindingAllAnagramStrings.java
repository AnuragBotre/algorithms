package com.trendcore.problems;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anurag
 */
public class FindingAllAnagramStrings {

    private static List<String> stateOfAlgorithm = new ArrayList<>();


    public static void main(String[] args) {
        String abc = "abc";

        for (int i = 0; i < abc.length(); i++) {
            char c = abc.charAt(i);

            for (int j = 0; j < abc.length(); j++) {
                /*if(i != j){
                    insertCharAtPosition(c, j, abc, i);
                }*/
                insertCharAtPosition(c, j, abc, i);
            }
        }


    }

    private static void insertCharAtPosition(char c, int positionToBeInserted, String abc, int originalPos) {

        boolean flag = true;

        StringBuilder s = new StringBuilder();

        String otherCharacters = getOtherCharacters(originalPos, abc);

        for (int i = 0 , j = 0; i < abc.length(); i++) {
            if (i == positionToBeInserted) {
                s.append(c);
            } else {
                s.append(otherCharacters.charAt(j));
                j++;
            }
        }

        System.out.println(s);
    }

    private static String getOtherCharacters(int c, String abc) {
        StringBuilder s = new StringBuilder();

        for (int i = 0; i < abc.length(); i++) {
            if (i != c) {
                s.append(abc.charAt(i));
            }
        }

        return s.toString();
    }

    private static void printStateOfAlgo(List<String> stateOfAlgorithm) {
        stateOfAlgorithm.forEach(System.out::println);
    }


}
