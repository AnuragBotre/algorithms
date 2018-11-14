package com.trendcore.problems.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/jump-game-ii/
 * <p>
 * 45. Jump Game II
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * <p>
 * Each element in the array represents your maximum jump length at that position.
 * <p>
 * Your goal is to reach the last index in the minimum number of jumps.
 * <p>
 * Example:
 * <p>
 * Input: [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2.
 * Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Note:
 * <p>
 * You can assume that you can always reach the last index.
 */
public class JumpGameII {

    public static void main(String[] args) {
        JumpGameII j = new JumpGameII();
        j.testCase(new int[]{2, 3, 1, 1, 4});
    }

    private void testCase(int[] nums) {
        System.out.println(jump(nums));
    }

    class Graph {
        Node root;

        public void print() {
            traverse(root);
        }

        private void traverse(Node root) {
            if (root == null) {
                System.out.println();
                return;
            } else if(root.list == null || root.list.isEmpty()){
                System.out.print(" " + root.step);
                System.out.println();
                return;
            }

            System.out.print(" " + root.step);
            for (Node node : root.list) {
                traverse(node);
            }
        }


    }

    class Node {
        int step;
        List<Node> list = new ArrayList();

        public Node(int num) {
            step = num;
        }

        @Override
        public String toString() {
            return "" + step;
        }
    }


    public int jump(int[] nums) {

        int dest = nums.length - 1;

        List<List<Integer>> combinationList = new ArrayList<>();
        //List list1 = new ArrayList();

        Graph g = new Graph();
        g.root = new Node(0);

        traverse(nums, 0, g.root, 1);

        g.print();

        return 0;
    }

    private void traverse(int[] nums, int step, Node node, int total) {

        if (total >= nums.length - 1) {
            return;
        }


        for (int i = step + 1, cnt = 0; cnt < nums[step]; i++, cnt++) {

            Node e = new Node(i);
            node.list.add(e);
            int r = total + nums[e.step];
            traverse(nums, i, e, r);
        }

    }

    private void traverseFurther(int[] nums, int i, int position, List list) {

    }

}
