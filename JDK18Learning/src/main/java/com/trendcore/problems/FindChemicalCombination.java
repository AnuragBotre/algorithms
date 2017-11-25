package com.trendcore.problems;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anurag
 */
public class FindChemicalCombination {

    public static void main(String[] args) {
        solution(3, 4);
    }

    private static void solution(int input1, int input2) {

        //find all possible integer values
        List<Integer> list = getAllPossibleIntegers(input1);

        //do all combination of integer values
        doAllCombinationOfIntegerValues(list);

    }

    private static void doAllCombinationOfIntegerValues(List<Integer> list) {

        int sum = 0;

        //iterate through values
        for (int i = 0; i < list.size(); i++) {
            int value = list.get(i);

            List newList = new ArrayList<>();
            newList.add(value);
            //get sum All combination of this value with given criteria
            sum = getAllPosibleCombinationOfValuesWithGivenCriteria(value, list.size(), newList);
        }


    }

    private static int getAllPosibleCombinationOfValuesWithGivenCriteria(int i, int noOfTimes, List newList) {

        return 0;
    }

    private static List getAllPossibleIntegers(int input1) {
        List list = new ArrayList();
        for (int i = 1; i <= input1; i++) {
            list.add(i);
        }
        return list;
    }

}
