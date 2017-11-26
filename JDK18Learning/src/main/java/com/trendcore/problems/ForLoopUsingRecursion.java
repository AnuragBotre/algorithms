package com.trendcore.problems;

/**
 * Created by Anurag
 */
public class ForLoopUsingRecursion {

    public static void main(String[] args) {

        ForLoopUsingRecursion loopUsingRecursion = new ForLoopUsingRecursion();

        //input 1-5

        //generate sequence of how much length

        /**
         * Recursion is correct.
         */


        //loopUsingRecursion.recurse(5);
        loopUsingRecursion.printInputs(new int[]{1, 2}, 0, 0, 3, "");
    }


//    private String s = "";

    private void printInputs(int[] ints, int arrayIndex, int position, int length, String s) {

        if (arrayIndex >= ints.length) {
            return;
        }

        //String s = "";
        printChars(ints, s, arrayIndex, position, length);
        printInputs(ints,arrayIndex+1,position,length,s);
    }

    private void printChars(int[] ints, String s, int arrayIndex, int position, int length) {

        if (position == length) {
            return;
        }
        s = s + ints[arrayIndex];
        printChars(ints, s, arrayIndex, position + 1, length);
        if (s.length() == length) {
            System.out.println(s);
        }
        s = s.substring(0, s.length() - 1);
        printInputsOther(ints, arrayIndex + 1, position, length, s);
    }

    private void printInputsOther(int[] ints, int arrayIndex, int position, int length, String s) {
        if (arrayIndex >= ints.length) {
            return;
        }

        //String s = "";
        printChars(ints, s, arrayIndex, position, length);
    }
}
