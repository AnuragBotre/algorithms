package com.trendcore.problems.leetcode;

import java.util.function.Consumer;

/**
 * https://leetcode.com/problems/add-two-numbers/description/
 *
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

 You may assume the two numbers do not contain any leading zero, except the number 0 itself.

 Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 Output: 7 -> 0 -> 8
 Explanation: 342 + 465 = 807.

 /**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }

 */
public class TwoNumbers {

    class ListNode{
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    class Solution{
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode solution = null;
            ListNode prevNode = null;
            ListNode firstNode = null;
            int carry = 0;
            while(notFinished(l1) | notFinished(l2)){

                int x = getValue(l1);
                int y = getValue(l2);

                int result = x + y + carry;
                if(result >= 10){
                    carry = 1;
                    result = result % 10;
                }else{
                    carry = 0;
                }

                solution = new ListNode(result);
                if(prevNode != null){
                    assignNode(solution, prevNode);
                }else {
                    firstNode = solution;
                }
                prevNode = solution;

                l1 = traverseNext(l1);
                l2 = traverseNext(l2);
            }

            if(carry > 0){
                solution = new ListNode(carry);
                assignNode(solution, prevNode);
            }

            return firstNode;
        }

        private void assignNode(ListNode solution, ListNode prevNode) {
            if(prevNode != null)
                prevNode.next = solution;
        }

        private ListNode traverseNext(ListNode l) {
            if(l != null){
                return l.next;
            }
            return null;
        }

        private int getValue(ListNode l) {
            if(l != null){
                return l.val;
            }
            return 0;
        }

        private boolean notFinished(ListNode l1) {
            return l1 != null;
        }
    }

    public static void main(String[] args) {
        TwoNumbers t = new TwoNumbers();
        Solution s = t.new Solution();

        ListNode n1 = t.new ListNode(1);
        ListNode n2 = t.new ListNode(2);
        n1.next = n2;
        ListNode n3 = t.new ListNode(3);
        n2.next = n3;

        Consumer<ListNode> printList = listNode -> {
            ListNode l = listNode;
            while (l != null){
                System.out.println(l.val);
                l = l.next;
            }
        };

        //printList.accept(n1);
        printList.accept(s.addTwoNumbers(n1,n2));
    }
}
