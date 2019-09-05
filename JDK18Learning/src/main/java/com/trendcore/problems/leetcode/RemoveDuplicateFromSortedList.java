package com.trendcore.problems.leetcode;

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
