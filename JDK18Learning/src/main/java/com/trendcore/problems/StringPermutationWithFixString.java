package com.trendcore.problems;

/**
 * Created by Anurag
 */
public class StringPermutationWithFixString {

    public static void main(String[] args) {
        //String abc = "abcd";
        String abc = "abc";

        /*for(int j = 0 ; j < abc.length() ; j++){
            for (int i = 0; i < abc.length(); i++) {
                if(i != j){
                    printSequence(abc.charAt(j),abc.substring(0,i+1),abc.substring(i+1,abc.length()));
                }
            }
        }*/


        for (int i = 0; i < abc.length(); i++) {
            //go character by character
            char c = abc.charAt(i);
            //fix one character

            //do all combination of character
            combinationOfOtherCharacter(abc.length(),Character.toString(c), getOtherCharcter(i, abc));

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

    private static void combinationOfOtherCharacter(int originalLength, String c, String otherCharcter) {
        for (int i = 0; i < otherCharcter.length(); i++) {
            //go character by character
            char c1 = otherCharcter.charAt(i);
            //fix one character

            //do all combination of character
            combinationOfOtherCharacter(originalLength, c.concat(String.valueOf(c1)), getOtherCharcter(i, otherCharcter));
        }

        /**
         * This is working
         */
        if(c.length() == originalLength){
            System.out.println(c);
        }
    }

}
