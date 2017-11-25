package com.trendcore.problems;

/**
 * Created by Anurag
 */
public class VerticalSumOfBinaryTree {

    private static class Node<T> {
        Node left;
        Node right;
        Comparable<T> value;

        public Node(Comparable comparable) {
            value = comparable;
        }
    }

    private static class Tree {
        Node root;

        public void addNode(Comparable comparable) {
            if (root == null) {
                root = new Node<Integer>(comparable);
            } else {
                addNode(root, comparable);
            }
        }

        private Node addNode(Node node, Comparable comparable) {
            if (node == null) {
                return new Node(comparable);
            }

            if (comparable.compareTo(node.value) < 0) {
                node.left = addNode(node.left, comparable);
            } else {
                node.right = addNode(node.right, comparable);
            }

            return node;
        }

        public void inorder() {
            Node node = root;
            inorder(node);
        }

        public void preOrder(){
            Node node = root;
            preOrder(node);
        }

        private void preOrder(Node node) {
            if(node == null)
                return;
            System.out.println(node.value);
            preOrder(node.left);
            preOrder(node.right);
        }

        private void inorder(Node node) {
            if(node == null)
                return;
            inorder(node.left);
            System.out.println(node.value);
            inorder(node.right);
        }
    }


    public static void main(String[] args) {

        Tree tree = new Tree();

        tree.addNode(5);
        tree.addNode(4);
        tree.addNode(3);
        tree.addNode(1);
        tree.addNode(6);
        tree.addNode(2);
        tree.addNode(2);

        tree.preOrder();
    }

}
