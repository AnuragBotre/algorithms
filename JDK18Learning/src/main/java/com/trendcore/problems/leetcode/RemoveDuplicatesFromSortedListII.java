package com.trendcore.problems.leetcode;

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
 * <p>
 * 82. Remove Duplicates from Sorted List II
 * <p>
 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
 * <p>
 * Example 1:
 * <p>
 * Input: 1->2->3->3->4->4->5
 * Output: 1->2->5
 * Example 2:
 * <p>
 * Input: 1->1->1->2->3
 * Output: 2->3
 */
public class RemoveDuplicatesFromSortedListII {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode temp = head;

        ListNode previousNode = null;

        while (temp != null) {

            if (temp.next != null && temp.val == temp.next.val) {


            }

            previousNode = temp;
            temp = temp.next;
        }
        return head;
    }

}
