package com.trendcore.problems;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anurag
 */
public class CombinationOfIntegers {

    public static void main(String[] args) {
        CombinationOfIntegers combinationOfIntegers = new CombinationOfIntegers();
        combinationOfIntegers.printInputs(new int[]{1, 2}, 1);
    }

    private void printInputs(int[] ints, int noOfCombinations) {

        List list = new ArrayList<>();

        if(noOfCombinations == 1){
            for(int i = 0; i < ints.length ; i++){
                list.add(""+ints[i]);
            }
        }

    }


}
