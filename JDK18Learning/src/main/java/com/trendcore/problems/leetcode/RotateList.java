package com.trendcore.problems.leetcode;

/**
 * https://leetcode.com/problems/rotate-list/
 * <p>
 * 61. Rotate List
 * Medium
 * <p>
 * 547
 * <p>
 * 793
 * <p>
 * Favorite
 * <p>
 * Share
 * Given a linked list, rotate the list to the right by k places, where k is non-negative.
 * <p>
 * Example 1:
 * <p>
 * Input: 1->2->3->4->5->NULL, k = 2
 * Output: 4->5->1->2->3->NULL
 * Explanation:
 * rotate 1 steps to the right: 5->1->2->3->4->NULL
 * rotate 2 steps to the right: 4->5->1->2->3->NULL
 * Example 2:
 * <p>
 * Input: 0->1->2->NULL, k = 4
 * Output: 2->0->1->NULL
 * Explanation:
 * rotate 1 steps to the right: 2->0->1->NULL
 * rotate 2 steps to the right: 1->2->0->NULL
 * rotate 3 steps to the right: 0->1->2->NULL
 * rotate 4 steps to the right: 2->0->1->NULL
 */
public class RotateList {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {

        RotateList main = new RotateList();

        RotateList r = new RotateList();

        for (int i = 1; i < 6; i++) {

            ListNode head = main.new ListNode(1);
            head.next = main.new ListNode(2);
            head.next.next = main.new ListNode(3);
            head.next.next.next = main.new ListNode(4);
            head.next.next.next.next = main.new ListNode(5);

            r.testCase(head, i);
        }

        r.testCase(null, 0);
        r.testCase(main.new ListNode(1), 1);

        r.testCase(getList(main, 0, 1, 2), 4);
        r.testCase(getList(main, 1, 2), 3);
        r.testCase(getList(main, 1, 2), 2);
        r.testCase(getList(main, 1, 2), 1);
    }

    private static ListNode getList(RotateList main, Integer... args) {
        ListNode listNode = main.new ListNode(args[0]);
        ListNode head = listNode;
        for (int i = 1; i < args.length; i++) {
            listNode.next = main.new ListNode(args[i]);
            listNode = listNode.next;
        }
        return head;
    }

    private void testCase(ListNode head, int k) {
        ListNode listNode = rotateRight(head, k);

        System.out.println();
        while (listNode != null) {
            System.out.print(" " + listNode.val);
            listNode = listNode.next;
        }
    }


    public ListNode rotateRight(ListNode head, int k) {

        if (head == null) {
            return null;
        } else if (k == 0) {
            return head;
        }

        //go to last
        ListNode temp = head;
        ListNode pointer1 = head;
        ListNode pointer2 = head;
        int i = 0;
        int length = 1;

        while (pointer1.next != null) {
            pointer1 = pointer1.next;
            if (i >= k) {
                pointer2 = pointer2.next;
            }
            i++;
            length++;
        }

        if (k < length) {
            ListNode t = pointer2.next;
            pointer1.next = head;
            head = t;
            pointer2.next = null;
        } else {

            //TODO If k is greater than length handle these cases

            k = k % length;

            if(k == 0)
                return head;

            pointer1 = head;
            pointer2 = head;
            i = 0;
            length = 0;

            while (pointer1.next != null) {
                pointer1 = pointer1.next;
                if (i >= k) {
                    pointer2 = pointer2.next;
                }
                i++;
                length++;
            }

            ListNode t = pointer2.next;
            pointer1.next = head;
            head = t;
            pointer2.next = null;
        }
        return head;
    }
}
