package com.trendcore.problems.leetcode;

import com.trendcore.problems.ReverseLinkedList;

/**
 * https://leetcode.com/problems/reverse-nodes-in-k-group/description/
 * 25. Reverse Nodes in k-Group
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 * <p>
 * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 * <p>
 * Example:
 * <p>
 * Given this linked list: 1->2->3->4->5
 * <p>
 * For k = 2, you should return: 2->1->4->3->5
 * <p>
 * For k = 3, you should return: 3->2->1->4->5
 * <p>
 * Note:
 * <p>
 * Only constant extra memory is allowed.
 * You may not alter the values in the list's nodes, only nodes itself may be changed.
 */
public class ReverseNodesInKGroup {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        ReverseNodesInKGroup r = new ReverseNodesInKGroup();

        ListNode l = r.new ListNode(1);
        l.next = r.new ListNode(2);
        l.next.next = r.new ListNode(3);
        l.next.next.next = r.new ListNode(4);
        l.next.next.next.next = r.new ListNode(5);

        //print(r.reverseKGroup(l, 2));
        print(r.reverseKGroup(l, 3));
    }

    private static void print(ListNode listNode) {
        System.out.println();
        while (listNode != null) {
            System.out.print(" " + listNode.val);
            listNode = listNode.next;
        }
    }

    public ListNode reverseKGroup(ListNode head, int k) {

        int cnt = 0;

        ListNode l = head;
        ListNode end = null;

        boolean swapHead = false;

        while (l != null) {
            cnt = 0;

            ListNode prev = end;

            ListNode start = l;
            ListNode temp = null;
            while (cnt < k && l != null) {
                cnt++;
                temp = l;
                l = l.next;
            }
            end = l;

            if (cnt >= k) {
                //then need reverse from start and end
                ListNode h = start;

                ListNode endOfSublist = prev;

                while (h != end) {
                    ListNode next = h.next;
                    ListNode cur = h;

                    cur.next = prev;
                    prev = cur;
                    h = next;
                }

                start.next = l;

                if (!swapHead) {
                    head = prev;
                    swapHead = true;
                }

                end = start;

                if(endOfSublist != null){
                    endOfSublist.next = prev;
                }
            } else {
                //no reverse
                break;
            }

        }

        return head;
    }

}
