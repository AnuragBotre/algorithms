package com.trendcore.problems;

/**
 * Created by Anurag
 */
public class StringPermutationWithFixStringApproach1 {


    public static void main(String[] args) {
        String abc = "abcd";

        /*for(int j = 0 ; j < abc.length() ; j++){
            for (int i = 0; i < abc.length(); i++) {
                if(i != j){
                    printSequence(abc.charAt(j),abc.substring(0,i+1),abc.substring(i+1,abc.length()));
                }
            }
        }*/


        traverse(abc);
    }

    private static void traverse(String abc) {
        for (int i = 0; i < abc.length(); i++) {
            //go character by character
            char c = abc.charAt(i);
            //fix one character

            //do all combination of character
            combinationOfOtherCharacter(abc.length(),Character.toString(c), getOtherCharcter(i, abc),i);

        }


    }

    private static String getOtherCharcter(int pos, String abc) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < abc.length(); i++) {
            if (i != pos) {
                buffer.append(abc.charAt(i));
            }
        }

        return buffer.toString();
    }

    private static void combinationOfOtherCharacter(int originalLength, String c, String otherCharcter, int i) {
        traverse(otherCharcter);

        System.out.println(c+otherCharcter);

    }


}
