package com.trendcore.problems.leetcode;

/**
 * https://leetcode.com/problems/climbing-stairs/
 * <p>
 * 70. Climbing Stairs
 * You are climbing a stair case.
 * It takes n steps to reach to the top.
 * <p>
 * Each time you can either climb 1 or 2 steps.
 * In how many distinct ways can you climb to the top?
 * <p>
 * Note: Given n will be a positive integer.
 * <p>
 * Example 1:
 * <p>
 * Input: 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 * Example 2:
 * <p>
 * Input: 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 */
public class ClimbingStairs {

    class StepTaker {
        int steps;

        int dest;

        public StepTaker() {
        }

        public StepTaker(int dest) {
            this.dest = dest;
            memory = new int[dest +1];
        }

        public void takeStepUsingReduction(int n) {

            if (n < 0) {
                return;
            } else if (n == 0) {
                steps++;
                return;
            }

            //for given n we can take either 1 step or 2 step

            //one step
            takeStepUsingReduction(n - 1);

            //two step
            takeStepUsingReduction(n - 2);
        }


        private int memory[];

        public int takeStepUsingAggregation(int n) {

            if (n == 2) {
                return 2;
            } else if (n == 1) {
                return 1;
            } else {
                //do we have ans for current step
                if (memory[n] > 0) {
                    return memory[n];
                } else {
                    //we need to calculate ans
                    int ans = 0;

                    ans = takeStepUsingAggregation(n - 2) + takeStepUsingAggregation(n - 1);

                    memory[n] = ans;

                    return ans;
                }
            }
        }
    }

    public int climbStairs(int n) {
        StepTaker step = new StepTaker(n);
        int i = step.takeStepUsingAggregation(n);
        return i;
    }

    /**
     * Uses fibo series.
     * output of previous step + permutation to get result for current step.
     *
     * @param n
     * @return
     */
    public int oneOfLeetCodeApproach(int n) {
        int a = 1, b = 2;

        if (n == 1) {
            return 1;
        }

        if (n == 2) {
            return 2;
        }

        int temp;

        for (int i = 3; i <= n; i++) {
            temp = a;
            a = b;
            b = b + temp;
        }

        return b;
    }


}
