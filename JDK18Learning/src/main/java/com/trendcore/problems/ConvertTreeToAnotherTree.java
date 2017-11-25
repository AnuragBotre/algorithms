package com.trendcore.problems;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anurag
 */
public class ConvertTreeToAnotherTree {

    private static List<Node> list = new ArrayList();

    static class Tree {
        Node root;

        int count;

        public void addNode(Node node) {
            traverseAndAdd(root, root, node);
        }

        private void traverseAndAdd(Node node, Node prevNode, Node nodeTobeAdded) {
            if (node == null) {
                if (prevNode == null) {
                    root = nodeTobeAdded;
                } else {
                    if (prevNode.data > nodeTobeAdded.data) {
                        prevNode.left = nodeTobeAdded;
                    } else {
                        prevNode.right = nodeTobeAdded;
                    }
                }
                return;
            }

            if (node.data > nodeTobeAdded.data) {
                traverseAndAdd(node.left, node, nodeTobeAdded);

            } else {
                traverseAndAdd(node.right, node, nodeTobeAdded);

            }
        }

        public void inOrderTraversal() {
            traverse(root, root, 0);
        }

        private void traverse(Node node, Node prevNode, Integer data) {

            if (node == null) {
                //System.out.print(prevNode.data + " " );
                return;
            }

            traverse(node.right, node, data);
            count = node.data + count;
            node.data = count;
            System.out.println(node.data);
            traverse(node.left, node, data);
            //node.data = node.data + prevNode.data;
        }

        public void inOrderTraversalApproach2() {
            traverseApproach2(root, root);
        }

        private Node traverseApproach2(Node node, Node prevNode) {

            Node node1 = null;
            //travel rightmost
            if (node.right != null) {
                node1 = traverseApproach2(node.right, node);// 50 70 80
            }

            System.out.println(node.data + " "+prevNode.data);

            if (node.left != null) {
                node1 = traverseApproach2(node.left, node);// 50 70 80
            }

            //then pop
            return node;
        }
    }

    static class Node {
        Integer data;
        Node left;
        Node right;

        public Node(int i) {
            data = i;
        }
    }

    public static void main(String[] args) {
        Tree tree = new Tree();

        tree.addNode(new Node(50));
        tree.addNode(new Node(40));
        tree.addNode(new Node(20));
        tree.addNode(new Node(30));
        tree.addNode(new Node(70));
        tree.addNode(new Node(60));
        tree.addNode(new Node(80));

        //tree.inOrderTraversal();

        tree.inOrderTraversalApproach2();


        for (int i = 1; i < list.size(); i++) {
            Node node = list.get(i);
            Node prevNode = list.get(i - 1);
            node.data = node.data + prevNode.data;
        }

        System.out.println();
        list.forEach(node -> System.out.println(node.data));

        //tree.inOrderTraversal();
    }

}
