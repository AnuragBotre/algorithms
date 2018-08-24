package com.trendcore.problems.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/
 * 19. Remove Nth Node From End of List
 * Given a linked list, remove the n-th node from the end of list and return its head.
 * <p>
 * Example:
 * <p>
 * Given linked list: 1->2->3->4->5, and n = 2.
 * <p>
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 * Note:
 * <p>
 * Given n will always be valid.
 * <p>
 * Follow up:
 * <p>
 * Could you do this in one pass?
 */
public class RemoveNthNodeFromEndOfList {

    public static void main(String[] args) {
        RemoveNthNodeFromEndOfList r = new RemoveNthNodeFromEndOfList();

        ListNode l = r.new ListNode(1);
        l.next = r.new ListNode(2);
        l.next.next = r.new ListNode(3);
        l.next.next.next = r.new ListNode(4);
        l.next.next.next.next = r.new ListNode(5);

        printList(r.removeNthFromEnd(l, 2));
        printList(r.removeNthFromEnd(l, 1));

        l = r.new ListNode(1);
        printList(r.removeNthFromEnd(l, 1));

        l = r.new ListNode(1);
        l.next = r.new ListNode(2);
        printList(r.removeNthFromEnd(l, 2));
    }

    private static void printList(ListNode listNode) {
        while(listNode != null){
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

    /**
     * Definition for singly-linked list.
     */
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return ""+val;
        }
    }

    /**
     * Leet Code has different Approach
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //traverse till n
        ListNode firstPointer = head;
        ListNode secondPointer = head;
        int index = 0;
        while(index < n+1 && firstPointer != null){
            firstPointer = firstPointer.next;
            index++;
        }

        if(firstPointer == null && index < n+1){
            //we could not cover n nodes
            while(index < n+1 && secondPointer != null){
                secondPointer = secondPointer.next;
                index++;
            }
            head = secondPointer;
            return head;
        }else {

            while (firstPointer != null) {
                firstPointer = firstPointer.next;
                secondPointer = secondPointer.next;
            }

            if (secondPointer.next != null) {
                secondPointer.next = secondPointer.next.next;
            } else {
                head = null;
            }
            return head;
        }

    }

    //Too much of space complexity
    private ListNode approach1UsingStoringInList(ListNode head, int n) {
        List<ListNode> list = new ArrayList<>();
        int i = 0;
        ListNode temp = head;
        while (temp != null) {
            list.add(temp);
            temp = temp.next;
            i++;
        }

        //size 2 and removing 1st node
        ListNode prevNode ;

        if(list.size() - (n + 1) >= 0){
            prevNode = list.get(list.size() - (n + 1));
            ListNode nextNode ;
            if(list.size() - (n - 1) >= 0 && list.size() - (n - 1) < list.size()){
                nextNode = list.get(list.size() - (n - 1));
            }else{
                nextNode = null;
            }
            prevNode.next = nextNode;
        }else{
            prevNode = null;
            ListNode nextNode ;
            if(list.size() - (n - 1) >= 0 && list.size() - (n - 1) < list.size()){
                nextNode = list.get(list.size() - (n - 1));
            }else{
                nextNode = null;
            }
            head = nextNode;
        }

        return head;
    }
}
