package com.trendcore.problems.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/binary-tree-inorder-traversal/description/
 * Given a binary tree, return the inorder traversal of its nodes' values.
 * <p>
 * Example:
 * <p>
 * Input: [1,null,2,3]
 * 1
 * \
 * 2
 * /
 * 3
 * <p>
 * Output: [1,3,2]
 * Follow up: Recursive solution is trivial, could you do it iteratively?
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

        if (root == null) {
            return new ArrayList<>();
        }

        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            //traverse left nodes
            TreeNode treeNode = traverseLeft(root, stack);
            if (treeNode != null) {
                stack.pop();
                list.add(treeNode.val);
                if (treeNode.right != null) {
                    root = treeNode.right;
                    stack.push(root);
                } else {
                    while(!stack.empty()) {
                        root = stack.pop();
                        list.add(root.val);
                        if(root.right != null){
                            root = root.right;
                            stack.push(root);
                            break;
                        }
                    }

                }
            }

        }

        return list;
    }

    private TreeNode getRightTreeNodeToTraverse(TreeNode root, Stack<TreeNode> stack, List<Integer> list) {
        if (root != null) {
            if (root.right != null) {
                stack.push(root.right);
                return root.right;
            } else {
                while (!stack.empty()) {
                    TreeNode t = stack.pop();
                    if (t.right != null) {
                        return t.right;
                    } else {
                        list.add(t.val);
                    }
                }
            }
        }
        return null;
    }

    private TreeNode traverseLeft(TreeNode root, Stack<TreeNode> stack) {
        if (root != null) {
            while (root.left != null) {
                root = root.left;
                stack.push(root);
            }
            return root;
        }
        return null;
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
