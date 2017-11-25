package com.trendcore.problems;

import java.util.LinkedList;

/**
 * Created by Anurag
 */
public class MergeTwoLinkedListInAscendingOrder {

    static class LinkedList {

        Node root;
        Node end;

        public void add(Node node) {
            if (root == null) {
                root = node;
                end = node;
            } else {
                end.next = node;
                end = end.next;
            }
        }

    }

    static class Node {
        Node next;
        Integer data;

        public Node(int i) {
            data = i;
        }
    }

    public static void main(String[] args) {
        LinkedList list1 = new LinkedList();

        LinkedList list2 = new LinkedList();

        list1.add(new Node(1));
        list1.add(new Node(3));


        list2.add(new Node(3));
        list2.add(new Node(4));

        LinkedList list3 = merge(list1, list2);
        printList(list3);
    }

    private static void printList(LinkedList list1) {
        Node node = list1.root;
        while (node != null) {
            System.out.println(node.data);
            node = node.next;
        }
    }

    private static LinkedList merge(LinkedList list1, LinkedList list2) {

        Node node1 = list1.root;
        Node node2 = list2.root;

        LinkedList list3 = new LinkedList();


        while (node1 != null || node2 != null) {

            if (node1 == null && node2 == null) {
                break;
            } else if (node1 == null) {
                list3.add(node2);
                node2 = node2.next;
            } else if (node2 == null) {
                list3.add(node1);
                node1 = node1.next;
            } else if (node1.data <= node2.data) {
                list3.add(node1);
                node1 = node1.next;
            } else {
                list3.add(node2);
                node2 = node2.next;
            }
        }

        return list3;
    }

}
