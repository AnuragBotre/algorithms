package com.trendcore.problems.leetcode;

/**
 * https://leetcode.com/problems/merge-two-sorted-lists/
 * 21. Merge Two Sorted Lists
 * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
 * <p>
 * Example:
 * <p>
 * Input: 1->2->4, 1->3->4
 * Output: 1->1->2->3->4->4
 */
public class Merge2SortedList {

    public static void main(String[] args) {
        Merge2SortedList m = new Merge2SortedList();

        ListNode l1 = m.new ListNode(1);
        l1.next = m.new ListNode(2);
        ListNode l2 = m.new ListNode(1);
        l2.next = m.new ListNode(3);
        print(m.mergeTwoLists(l1, l2));
        print(m.mergeTwoLists(null, l2));
        print(l1);
        print(m.mergeTwoLists(l1, null));
        print(m.mergeTwoLists(null, null));
    }

    private static void print(ListNode listNode) {
        System.out.println();
        while (listNode != null) {
            System.out.print(" " + listNode.val);
            listNode = listNode.next;
        }
        System.out.println();
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode mergedListNode = null;
        ListNode mergedListNodeRoot = null;

        mergedListNodeRoot = mergedListNode = new ListNode(0);

        while (l1 != null || l2 != null) {

            if (l1 != null && l2 != null) {
                if (l1.val <= l2.val) {
                    mergedListNode.next = l1;
                    l1 = l1.next;
                } else {
                    mergedListNode.next = l2;
                    l2 = l2.next;
                }
            } else {
                if (l1 != null && l2 == null) {
                    mergedListNode.next = l1;
                    l1 = l1.next;
                } else if (l1 == null && l2 != null) {
                    mergedListNode.next = l2;
                    l2 = l2.next;
                }
            }
            mergedListNode = mergedListNode.next;
        }

        mergedListNodeRoot = mergedListNodeRoot.next;

        return mergedListNodeRoot;
    }

}
