package com.trendcore.problems.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/binary-tree-inorder-traversal/description/
 * Given a binary tree, return the inorder traversal of its nodes' values.

 Example:

 Input: [1,null,2,3]
 1
 \
 2
 /
 3

 Output: [1,3,2]
 Follow up: Recursive solution is trivial, could you do it iteratively?
 */
public class BinaryTreeInOrderTraversal {

    public static void main(String[] args) {
        BinaryTreeInOrderTraversal b = new BinaryTreeInOrderTraversal();

        TreeNode root = b.new TreeNode(1);
        root.right = b.new TreeNode(2);
        root.right.left = b.new TreeNode(3);

        System.out.println(b.inorderTraversal(root));
        System.out.println(b.inorderTraversal(b.new TreeNode(1)));

        root = b.new TreeNode(2);
        root.left = b.new TreeNode(3);
        root.left.left = b.new TreeNode(1);
        System.out.println(b.inorderTraversal(root));

        root = b.new TreeNode(3);
        root.left = b.new TreeNode(1);
        root.right = b.new TreeNode(2);
        System.out.println(b.inorderTraversal(root));
    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //approach - 2
    //iterative
    public List<Integer> inorderTraversal(TreeNode root) {

        if(root == null){
            return new ArrayList<>();
        }

        List list = new ArrayList();
        Stack stack = new Stack();
        stack.push(root);
        TreeNode t = root;
        while (!stack.empty()) {
            //traverse to the left most
            t = (TreeNode) stack.pop();
            while (t != null) {
                stack.push(t);
                t = t.left;
            }
            t = (TreeNode) stack.pop();
            list.add(t.val);
            t = t.right;
            if(t != null) {
                stack.push(t);
            }else{
                while (!stack.empty()){
                    t = (TreeNode) stack.pop();
                    list.add(t.val);
                }
            }
        }

        return list;
    }

    private List<Integer> solutionUsintRecursive(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        usingRecursive(root, list);
        return list;
    }

    private void usingRecursive(TreeNode root, List<Integer> list) {
        if (root != null) {
            usingRecursive(root.left, list);
            list.add(root.val);
            usingRecursive(root.right, list);
        }
    }

}
