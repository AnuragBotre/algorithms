package com.trendcore.problems.leetcode;

import java.util.ArrayList;
import java.util.List;

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



    class StepTaker{
        int steps;

        public void takeStep(int n) {

            if (n < 0) {
                return;
            }else if(n == 0){
                steps++;
                return;
            }

            //for given n we can take either 1 step or 2 step

            //one step
            takeStep(n - 1);

            //two step
            takeStep(n - 2);
        }
    }

    public int climbStairs(int n) {
        StepTaker step = new StepTaker();
        step.takeStep(n);
        return step.steps;
    }



}
