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
        j.testCase(new int[]{5, 1, 1, 1, 4});
        j.testCase(new int[]{2, 1, 3, 2, 7, 1, 1, 1, 1, 1, 1, 1});
        j.testCase(new int[]{0});
        j.testCase(new int[]{1, 2});

        j.testCase(new int[]{1});

        j.testCase(new int[]{2, 0, 2, 4, 6, 0, 0, 3});
        j.testCase(new int[]{5, 9, 3, 2, 1, 0, 2, 3, 3, 1, 0, 0});
        j.testCase(new int[]{2, 1, 1, 1, 1});

        j.testCase(new int[]{5, 6, 4, 4, 6, 9, 4, 4, 7, 4, 4, 8, 2, 6, 8, 1, 5, 9, 6, 5, 2, 7, 9, 7, 9, 6, 9, 4, 1, 6, 8, 8, 4, 4, 2, 0, 3, 8, 5});

    }

    private void testCase(int[] nums) {
        System.out.println(jump(nums));
    }


    class Graph {
        Node root;

        int minLength;
        boolean minLengthInitialized;

        public void print() {
            traverse(root, 1);
        }

        private void traverse(Node root, int length) {
            if (root == null) {
                //System.out.println();
                return;
            } else if (root.list == null || root.list.isEmpty()) {
                if (!minLengthInitialized) {
                    minLength = length;
                    minLengthInitialized = true;
                } else {
                    if (minLength > length) {
                        minLength = length;
                    }
                }
                /*System.out.print(" " + root.step);
                System.out.println();*/
                return;
            }

            //System.out.print(" " + root.step);
            for (Node node : root.list) {
                traverse(node, length + 1);
            }
        }


    }

    class Node {
        int step;
        List<Node> list = new ArrayList();

        public Node(int step) {
            this.step = step;
        }

        @Override
        public String toString() {
            return "" + step;
        }
    }


    public int jump(int[] nums) {

        if (nums.length < 2) {
            return 0;
        }

        Graph g = new Graph();
        Node n = new Node(0);

        int length = 1;

        class MethodImpl implements Method{
            boolean minLengthInitialized = false;
            int minLength = 1;

            @Override
            public <T> T execute(int length) {
                if (!minLengthInitialized) {
                    minLength = length;
                    minLengthInitialized = true;
                } else {
                    if (minLength > length) {
                        minLength = length;
                    }
                }
                return null;
            }
        }

        MethodImpl method = new MethodImpl();

        traverse(nums, 0, n, 1, g, length, method);

        //g.print();

        return method.minLength;
    }

    private interface Method {
        <T> T execute(int length);
    }

    private boolean traverse(int[] nums, int step, Node node, int total, Graph g, int length, Method method) {

        if (nums[node.step] <= 0) {
            return false;
        }

        if (g.root == null) {
            g.root = node;
        }

        if (total >= nums.length - 1) {
            method.execute(length);
            return true;
        } else if (step + nums[node.step] >= nums.length - 1) {
            method.execute(length);
            return true;
        }


        /**
         * TODO :-
         * In this loop check if destination can be reached with current step
         */
        boolean destCanBeReached = false;
        for (int i = step + 1, cnt = 0; cnt < nums[step]; i++, cnt++) {
            if (nums[i] > 0) {
                Node e = new Node(i);
                /*TODO :-
                    While putting nodes into list need to make sure if this node can be
                    reached to end.
                */
                //node.list.add(e);
                int r = step + nums[e.step];
                boolean traverse = traverse(nums, i, e, r, g, length + 1, method);
                if (!traverse) {
                    //node.list.remove(node.list.size() - 1);
                } else {
                    destCanBeReached = true;
                }
                //return traverse;

            }
        }

        return destCanBeReached;
    }

    private void traverseFurther(int[] nums, int i, int position, List list) {

    }

    /**
     * Algo
     * from step 0 find out the no of options
     * then select furthest step that can be reached
     * repeat the same procedure and check if destination can be reached
     * if yes then this is shortest path.
     * for the given input
     *  2,3,1,0,4
     *  output should be
     *  2
     *  2 3
     *  If from any step dest cannot be reached then algo should continue with other options.
     */

}
