package com.trendcore.problems.leetcode;

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list/
 *
 * 83. Remove Duplicates from Sorted List
 *
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 *
 * Example 1:
 *
 * Input: 1->1->2
 * Output: 1->2
 * Example 2:
 *
 * Input: 1->1->2->3->3
 * Output: 1->2->3
 */
public class RemoveDuplicateFromSortedList {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode deleteDuplicates(ListNode head) {

        ListNode temp = head;

        ListNode current = null;
        ListNode previous = null;

        while (temp != null) {

            current = temp;

            if (previous != null) {
                if (previous.val == current.val) {
                    previous.next = current.next;
                    temp=current.next;
                } else {
                    previous = temp;
                    temp = temp.next;
                }
            } else {
                previous = temp;
                temp = temp.next;
            }


        }

        return head;
    }

}
