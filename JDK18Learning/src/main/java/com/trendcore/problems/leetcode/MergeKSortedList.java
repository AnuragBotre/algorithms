package com.trendcore.problems.leetcode;

/**
 * https://leetcode.com/problems/merge-k-sorted-lists/description/
 * 23. Merge k Sorted Lists
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 * <p>
 * Example:
 * <p>
 * Input:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * Output: 1->1->2->3->4->4->5->6
 */
public class MergeKSortedList {

    public static void main(String[] args) {
        MergeKSortedList m = new MergeKSortedList();

        ListNode[] lists = new ListNode[10];

        m.mergeKLists(lists);
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        return null;
    }

}
