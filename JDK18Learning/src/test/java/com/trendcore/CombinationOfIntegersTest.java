package com.trendcore;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anurag
 */
public class CombinationOfIntegersTest {

    @Test
    public void testFor1stCombination() throws Exception {
        List list = printInputs(new int[]{1, 2}, 1);
        Assert.assertEquals(list.size(), 2);

        System.out.println(list);
    }

    @Test
    public void testFor1InputsFor2Combinations() throws Exception {
        List list = printInputs(new int[]{1}, 2);
        Assert.assertEquals(list.size(), 1);

        System.out.println(list);
    }

    @Test
    public void testFor2InputsFor2Combinations() throws Exception {
        List list = printInputs(new int[]{1,2}, 2);
        Assert.assertEquals(list.size(), 4);

        System.out.println(list);
    }

    @Test
    public void testFor2InputsWith3Combination() throws Exception {
        List list = printInputs(new int[]{1,2}, 3);
        Assert.assertEquals(list.size(), 8);

        System.out.println(list);
    }

    private List printInputs(int[] ints, int noOfCombinations) {

        List list = new ArrayList<>();

        if (noOfCombinations == 1) {
            for (int i = 0; i < ints.length; i++) {
                list.add("" + ints[i]);
            }
        }else if(noOfCombinations == 2){
            findCombinationsOfCharacters(ints, noOfCombinations, list);
        }else{
            findCombinationsOfCharacters(ints, noOfCombinations, list);
        }

        return list;

    }

    private void findCombinationsOfCharacters(int[] ints, int noOfCombinations, List list) {
        for(int i = 0 ; i < ints.length ; i++){
            String s = ""+ints[i];
            findOtherCombinations(list,ints,ints[i],noOfCombinations-1,s);
        }
    }

    private void findOtherCombinations(List list, int[] ints, int anInt, int noOfCombinations, String s) {
        if(noOfCombinations <= 0){
            list.add(s);
            return;
        }

        for(int i = 0 ; i < ints.length ; i++){
            s = s+ints[i];
            findOtherCombinations(list,ints,ints[i],noOfCombinations-1, s);
            s = s.substring(0,s.length()-1);
        }
    }
}
