package com.trendcore.problems.leetcode;

import java.util.List;

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
        printOthers(nums, i);
        printOthersReverse(nums, i);

        traverse(nums, i + 1);
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
