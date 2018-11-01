package com.trendcore.problems.learning;

import java.util.*;
import java.util.stream.Collectors;

public class CombinationOfNumbers {

    public static void main(String[] args) {
        CombinationOfNumbers p = new CombinationOfNumbers();
        p.testCase(new int[]{1, 2, 3});
    }

    private void testCase(int[] ints) {
        permute(ints);
    }

    public List<List<Integer>> permute(int[] nums) {
        //TODO This solution is incomplete.
        //may need to rotate array and then have to re-traverse.
        traverse(nums, 0);
        return null;
    }

    private void traverse(int[] nums, int i) {

        if (i >= nums.length) {
            System.out.println();
            return;
        }

        int num = nums[i];

        //print the others
        /*printOthers(nums, i);
        printOthersReverse(nums, i);*/
        printCombinations(nums, i);

        traverse(nums, i + 1);
    }

    private void printCombinations(int[] nums, int position) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {

                if (i == j) {
                    System.out.print(nums[position] + " ");
                } else {
                    System.out.print(nums[j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private void printOthersReverse(int[] nums, int location) {
        System.out.println();

        for (int i = 0; i < nums.length; i++) {
            if (i != location) {
                System.out.print(nums[i]);
            }
        }
        System.out.print(nums[location]);
    }

    private void printOthers(int[] num, int location) {
        System.out.println();
        System.out.print(num[location]);

        for (int i = 0; i < num.length; i++) {
            if (i != location) {
                System.out.print(num[i]);
            }
        }
    }
}
