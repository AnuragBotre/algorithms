package com.trendcore.problems.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/permutation-sequence/
 * <p>
 * 60. Permutation Sequence
 * <p>
 * The set [1,2,3,...,n] contains a total of n! unique permutations.
 * <p>
 * By listing and labeling all of the permutations in order, we get the following sequence for n = 3:
 * <p>
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * Given n and k, return the kth permutation sequence.
 * <p>
 * Note:
 * <p>
 * Given n will be between 1 and 9 inclusive.
 * Given k will be between 1 and n! inclusive.
 * Example 1:
 * <p>
 * Input: n = 3, k = 3
 * Output: "213"
 * Example 2:
 * <p>
 * Input: n = 4, k = 9
 * Output: "2314"
 */
public class PermutationSequence {

    public static void main(String[] args) {
        PermutationSequence permutationSequence = new PermutationSequence();
        permutationSequence.testCase(4, 2);
        /*permutationSequence.testCase(4, 9);
        permutationSequence.testCase(1, 1);
        permutationSequence.testCase(2, 2);
        permutationSequence.testCase(9, 17223);*/
    }

    private void testCase(int i, int i1) {
        String permutation = getPermutation(i, i1);
        System.out.println(" Permutation :- " + permutation);
    }

    public String getPermutation(int n, int k) {

        int tempN = n - 1;
        int tempK =  k - 1;

        List list = new ArrayList();
        for(int i = 0 ; i < n ; i++){
            list.add(i+1);
        }

        String s = "";

        for(int cnt = 0 ; cnt < n ; cnt++){

            if(tempK < 0){
                //copy rest of the values in the string
                for(int i = 0 ; i < list.size() ; i++){
                    s = s + list.get(i);
                }
                break;
            }

            int factorial = factorial(tempN);
            int div = tempK / factorial;
            //int i = tempK % factorial == 0 ? div : div + 1;
            int i = div;
            s = s + list.remove(i);
            tempK = tempK - factorial*i;
            tempN = tempN - 1;
        }

        return s;
    }

    private int factorial(int n) {
        int fact = 1;
        for (int i = 1; i <= n; i++) {
            fact = fact * i;
        }
        return fact;
    }

    private String approach1(int n, int k) {
        String s = "";

        List<String> list = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            s = s + i;
            list.add("" + i);
        }

        for (int i = 0; i < n - 1; i++) {
            List anotherList = new ArrayList();
            for (int j = 0; j < list.size(); j++) {
                for (int cnt = 1; cnt <= n; cnt++) {
                    String o = list.get(j);
                    if (!o.contains("" + cnt)) {
                        anotherList.add(o + cnt);
                    }
                }
            }
            list = anotherList;
        }

        list.stream().forEach(System.out::println);

        System.out.println(list.size());

        return list.get(k - 1);
    }

    public void approach1(String s) {
        getSeq(s);
    }

    public void getSeq(String s) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            keepOneFixedAndPrintOther(i, s, s.length());
        }
    }

    private void keepOneFixedAndPrintOther(int charToBeFixed, String s, int length) {
        //keep the same and print the others

        //TODO Need recursion here

        int start = charToBeFixed == 0 ? charToBeFixed + 1 : 0;

        for (int cnt = 0; cnt < s.length() - 1; cnt++, start++) {

            System.out.print(s.charAt(charToBeFixed));

            if (start == s.length()) {
                start = 0;
            }

            if (start == charToBeFixed) {
                start++;
            }

            if (start == s.length()) {
                start = 0;
            }

            int pointer = start;

            for (int cnt1 = 0; cnt1 < s.length(); cnt1++) {

                if (pointer == s.length()) {
                    pointer = 0;
                }

                if (pointer == charToBeFixed) {
                    pointer++;
                }

                if (pointer == s.length()) {
                    pointer = 0;
                }

                if (cnt1 != charToBeFixed) {
                    System.out.print(s.charAt(pointer));
                    pointer++;
                }
            }

            System.out.println();
        }


    }

}
