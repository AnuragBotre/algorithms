package com.trendcore.problems.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/combinations/
 * <p>
 * 77. Combinations
 * <p>
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 * <p>
 * Example:
 * <p>
 * Input: n = 4, k = 2
 * Output:
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 */
public class Combinations {

    public List<List<Integer>> combine(int n, int k) {

        //recursive exponential problem

        //long awaited wait is over


        List<List<Integer>> list = new ArrayList<>();

        if (k == 1) {
            for (int i = 0; i < n; i++) {
                ArrayList<Integer> e = new ArrayList<>();
                e.add(i + 1);
                list.add(e);
            }
            return list;
        }

        for (int i = 1; i <= n; i++) {
            List temp = new ArrayList();
            f(i, n, k, temp, list);
        }

        return list;
    }

    private void f(int current, int n, int k, List temp, List list) {

        if (current > n) {

            if (k == temp.size()) {
                list.add(new ArrayList<>(temp));
                //temp.remove(temp.size() - 1);
            }
            return;
        } else if (k == temp.size()) {
            list.add(new ArrayList<>(temp));
            //temp.remove(temp.size()-1);
            return;
        }

        temp.add(current);
        f(current + 1, n, k, temp, list);
        if (temp.size() > 1) {
            temp.remove(temp.size() - 1);
            f(current+1, n, k, temp, list);
        }
    }

}
