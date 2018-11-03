package com.trendcore.problems.leetcode;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/permutations/
 * Given a collection of distinct integers, return all possible permutations.
 * <p>
 * Example:
 * <p>
 * Input: [1,2,3]
 * Output:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 */
public class Permutations {

    public static void main(String[] args) {
        Permutations p = new Permutations();
        p.testCase(new int[]{1, 2, 3, 4, 5});
    }

    private void testCase(int[] ints) {
        permute(ints);
    }

    public List<List<Integer>> permute(int[] nums) {
        //TODO This solution is incomplete.
        //create data structure to store unique combination

        Set set = new HashSet() {
            @Override
            public Iterator iterator() {
                return super.iterator();
            }
        };

        traverse(nums, 0, set);
        return null;
    }

    private void traverse(int[] nums, int i, Set set) {

        if (i >= nums.length) {
            //System.out.println();
            return;
        }

        int num = nums[i];

        //print the others
        /*printOthers(nums, i);
        printOthersReverse(nums, i);*/
        printCombinations(nums, i, set);

        traverse(nums, i + 1, set);
    }

    private void printCombinations(int[] nums, int position, Set set) {
        for (int i = 0; i < nums.length; i++) {
            //int temp[] = new int[nums.length];
            String str = "";
            for (int j = position + 1, cnt = 0; cnt < nums.length; cnt++) {
                if (j >= nums.length) {
                    j = 0;
                }
                if (i == cnt) {
                    System.out.print(" " + nums[position]);
                    str = str + nums[position] + ",";
                } else {
                    System.out.print(" " + nums[j]);
                    str = str + nums[j] + ",";
                    j++;
                }
            }

            set.add(str);
            //System.out.println();
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
