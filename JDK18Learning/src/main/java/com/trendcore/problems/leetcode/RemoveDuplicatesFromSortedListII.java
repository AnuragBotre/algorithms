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

        @Override
        public String toString() {
            return "" + val;
        }
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode temp = head;

        ListNode previousNode = null;

        while (temp != null) {

            if (temp.next != null && temp.val == temp.next.val) {
                ListNode t = temp;
                while (t != null) {
                    if (!(t.next != null && t.val == t.next.val)) {
                        break;
                    }
                    t = t.next;
                }
                temp = t;

                if (head.val == t.val) {
                    head = t.next;
                }

                if (previousNode != null) {
                    if (t.next != null) {
                        previousNode.next = t.next;
                    } else {
                        previousNode.next = null;
                    }
                } else {
                    previousNode = t.next;
                }

            } else {
                previousNode = temp;
            }

            if (temp != null) {
                temp = temp.next;
            }
        }
        return head;
    }

}
