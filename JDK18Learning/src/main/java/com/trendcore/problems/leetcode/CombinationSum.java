package com.trendcore.problems.leetcode;

import java.util.Arrays;
import java.util.List;

public class CombinationSum {

    public static void main(String[] args) {
        CombinationSum c = new CombinationSum();
        c.testCase(new int[]{2, 3, 6, 7}, 7);
        //c.testCase(new int[]{2, 3, 5}, 8);
    }

    private void testCase(int[] candidates, int target) {
        System.out.println(combinationSum(candidates, target));
    }

    private List<List<Integer>> combinationSum(int[] candidates, int target) {

        //sort the candidates
        Arrays.sort(candidates);

        for (int i = 0; i < candidates.length; i++) {
            if (target % candidates[i] == 0) {
                int r = target / candidates[i];
                //1st combination
                insertNoRTimes(candidates[i], r);
                //need to check multiple combination of r.
                findWithSubtraction(target, i, candidates);
            } else {
                int i1 = target % candidates[i];
                if (isNumberPresent(i1, candidates)) {
                    System.out.println(candidates[i] + " " + i1);
                }
                findWithSubtraction(target,i,candidates);
            }
        }

        return null;
    }

    private void findWithSubtraction(int target, int ithPos, int[] candidates) {
        int result = 0;
        for (int i = 0; i < candidates.length; i++) {
            result = target - candidates[i];
            if (isNumberPresent(result, candidates)) {

            }
        }
    }

    private boolean isNumberPresent(int result, int[] candidates) {
        for (int i = 0; i < candidates.length; i++) {
            if (candidates[i] == result) {
                return true;
            } else if (candidates[i] > result) {
                return false;
            }
        }
        return false;
    }

    private void insertNoRTimes(int no, int r) {
        for (int i = 0; i < r; i++) {
            System.out.print(no + ",");
        }
        System.out.println();
    }

}
