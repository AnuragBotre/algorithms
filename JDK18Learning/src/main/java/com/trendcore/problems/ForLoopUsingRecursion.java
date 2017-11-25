package com.trendcore.problems;

/**
 * Created by Anurag
 */
public class ForLoopUsingRecursion {

    public static void main(String[] args) {

        ForLoopUsingRecursion loopUsingRecursion = new ForLoopUsingRecursion();

        //input 1-5

        //generate sequence of how much length


        //loopUsingRecursion.recurse(5);
        loopUsingRecursion.printInputs(new int[]{1, 2}, 3);
    }

    private void printInputs(int[] ints, int length) {
        for(int i = 0 ; i < ints.length ; i++){
            System.out.print(ints[i]);
            printCombinations(i,ints,length-1);
            System.out.println();
        }
    }

    private void printCombinations(int arrayIndex, int[] ints, int length) {
        for(int i=0;i<length;i++){
            printInputs(ints,length);
        }
    }
}
