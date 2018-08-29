package com.trendcore.problems.leetcode;

/**
 * https://leetcode.com/problems/swap-nodes-in-pairs/description/
 * 24. Swap Nodes in Pairs
 * Given a linked list, swap every two adjacent nodes and return its head.
 * <p>
 * Example:
 * <p>
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 * Note:
 * <p>
 * Your algorithm should use only constant extra space.
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 */
public class SwapNodesInPairs {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        SwapNodesInPairs s = new SwapNodesInPairs();
        ListNode l = s.new ListNode(1);
        l.next = s.new ListNode(2);
        l.next.next = s.new ListNode(3);
        l.next.next.next = s.new ListNode(4);
        print(s.swapPairs(l));
    }

    private static void print(ListNode listNode) {
        System.out.println();
        while (listNode != null) {
            System.out.print(" " + listNode.val);
            listNode = listNode.next;
        }
    }

    public ListNode swapPairs(ListNode head) {

        ListNode l = head;

        ListNode prevNode = null;

        while (l != null) {

            //double hop
            ListNode temp = null;
            if(l.next != null){
                temp = l.next.next;
            }

            //node current node
            ListNode currNode = l;
            //next node
            if(l.next != null) {
                ListNode nextNode = l.next;


                nextNode.next = currNode;
                currNode.next = temp;
                if (prevNode != null) {
                    prevNode.next = nextNode;
                }


                if (currNode == head) {
                    //need to assign new head
                    head = nextNode;
                }
            }

            prevNode = currNode;
            l = temp;
        }
        return head;
    }

    /**
     *Leet Code Solution
     */
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) { val = x; }
     * }
     * Leet Code Solution
     */
    class Solution {
        public ListNode swapPairs(ListNode head) {
            ListNode dummy = new ListNode(0);
            dummy.next = head;
            ListNode curr = dummy;
            while(curr.next != null && curr.next.next != null){
                swap(curr);
                curr = curr.next.next;
            }
            return dummy.next;
        }

        private void swap(ListNode prev){
            ListNode dummy = prev.next;
            prev.next = dummy.next;
            dummy.next = dummy.next.next;
            prev.next.next = dummy;
        }
    }

}
