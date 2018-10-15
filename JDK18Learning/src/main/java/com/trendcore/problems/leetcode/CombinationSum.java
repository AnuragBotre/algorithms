package com.trendcore.problems.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {

    public static void main(String[] args) {
        CombinationSum c = new CombinationSum();
        c.testCase(new int[]{2, 3, 6, 7}, 7);
        c.testCase(new int[]{2, 3, 5}, 8);
        c.testCase(new int[]{3, 5, 7}, 15);
    }

    private void testCase(int[] candidates, int target) {
        System.out.println("target -> " + target);
        List<List<Integer>> lists = combinationSum(candidates, target);
        lists.forEach(integers -> {
                    integers.forEach(integer -> System.out.print(" " + integer));
                    System.out.println();
                }
        );
    }

    //Not working for some test cases
    private List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> secondList = new ArrayList();
        /*for (int i = 0; i < candidates.length; i++) {
            List list = new ArrayList();
            traverse(candidates, target, i , list , secondList);
            printList(secondList);
        }*/
        List list = new ArrayList();
        traverse(candidates, target, 0 , list , secondList);

        return secondList;
    }

    private void printList(List<List> lists) {
        System.out.println();
        lists.forEach(integers -> {
                    integers.forEach(integer -> System.out.print(" " + integer));
                    System.out.println();
                }
        );
        System.out.println();
    }

    private boolean traverse(int[] candidates, int target, int i, List list, List secondList) {

        if(i >= candidates.length){
            if(target == 0){
                return true;
            }
            return false;
        }

        if (target <= 0) {
            if(target == 0){
                return true;
            }
            return false;
        }
        list.add(candidates[i]);
        boolean traverse = traverse(candidates, target - candidates[i], i, list, secondList);
        if(traverse){
            secondList.add(new ArrayList<>(list));
        }

        list.remove(list.size()-1);
        //remove one element and then take next element
        traverse(candidates,target,i+1,list,secondList);

        return traverse;
    }

    private List<List<Integer>> approach2(int[] candidates, int target) {
        System.out.println("Starting");
        int result;
        List finalList = new ArrayList();

        for (int i = 0; i < candidates.length; i++) {
            //substract
            result = target;
            List list = new ArrayList();
            List searchList = new ArrayList();

            while (result > 0) {
                result = result - candidates[i];
                if (result < 0) {
                    break;
                }

                list.add(candidates[i]);

                //search the result
                Integer search = search(result, candidates, i);
                if (search != null) {
                    searchList.addAll(list);
                    searchList.add(search);
                    finalList.add(searchList);
                    searchList = new ArrayList();
                }
            }

            if (result == 0) {
                //need to check the list
                finalList.add(list);
            }
        }

        return finalList;
    }

    private Integer search(int result, int[] candidates, int position) {
        for (int i = 0; i < candidates.length; i++) {
            if (candidates[i] == result) {
                return candidates[i];
            }
        }
        return null;
    }

    private List<List<Integer>> approach1(int[] candidates, int target) {
        //sort the candidates
        Arrays.sort(candidates);

        for (int i = 0; i < candidates.length; i++) {
            if (isDivisible(candidates[i], target)) {
                int r = target / candidates[i];
                //1st combination
                insertNoRTimes(candidates[i], r);
                //need to check multiple combination of r.
                findWithSubtraction(target, i, candidates);
            }

            List list = new ArrayList();
            subtractAndSearch(target, candidates, candidates[i], list);
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

    //write all the combination
    private boolean isDivisible(int no, int target) {
        if (target % no == 0) {
            return true;
        }
        return false;
    }

    private boolean subtractAndSearch(int target, int[] candidates, int currentCandidate, List list) {
        if (target < 0) {
            return false;
        }

        list.add(currentCandidate);
        int result = target - currentCandidate;

        if (result == 0) {
            return true;
        }
        //find the result in

        return subtractAndSearch(result, candidates, currentCandidate, list);
    }

    private Integer find(int result, int[] candidates, int currentCandidate) {
        for (int i = 0; i < candidates.length; i++) {
            if (candidates[i] != currentCandidate && result % candidates[i] == 0) {
                return candidates[i];
            }
        }

        return null;
    }


}
