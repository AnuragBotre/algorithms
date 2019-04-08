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

        for(int i = 1 ; i < 5 ; i++){

            ListNode head = main.new ListNode(1);
            head.next = main.new ListNode(2);
            head.next.next = main.new ListNode(3);
            head.next.next.next = main.new ListNode(4);
            head.next.next.next.next = main.new ListNode(5);

            r.testCase(head,i);
        }

    }

    private void testCase(ListNode head, int k) {
        ListNode listNode = rotateRight(head, k);

        System.out.println();
        while (listNode!= null) {
            System.out.print(" " + listNode.val);
            listNode = listNode.next;
        }
    }


    public ListNode rotateRight(ListNode head, int k) {

        ListNode tempHead = head;

        int i = 0;

        while (tempHead != null && i < k) {
            tempHead = tempHead.next;
            i++;
        }

        if(i == k){
            //1st case
            ListNode originalHead = head;
            ListNode t = tempHead.next;

            ListNode prev = null;
            while (t.next != null){
                prev = t;
                t = t.next;
            }
            t.next = originalHead;
            head = t;

            if(prev != null){
                prev.next = null;
            }

            return head;
        }else{
            //2nd case
        }

        return null;
    }
}
