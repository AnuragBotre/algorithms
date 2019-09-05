package com.trendcore.problems.leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

public class RemoveDuplicateFromSortedListTest {

    RemoveDuplicateFromSortedList removeDuplicateFromSortedList = new RemoveDuplicateFromSortedList();

    @Test
    public void forGivenInput() {
        act(new int[]{1, 1, 2}, new int[]{1, 2});
        act(new int[]{1, 1, 1, 1, 2}, new int[]{1, 2});
        act(new int[]{1, 1, 1, 1, 2 , 2}, new int[]{1, 2});
        act(new int[]{1}, new int[]{1});
        act(new int[]{1,2}, new int[]{1,2});
    }

    public void act(int[] ints, int[] expected) {

        System.out.println();
        RemoveDuplicateFromSortedList.ListNode head = null;
        RemoveDuplicateFromSortedList.ListNode temp = null;


        for (int i = 0; i < ints.length; i++) {
            RemoveDuplicateFromSortedList.ListNode node = new RemoveDuplicateFromSortedList.ListNode(ints[i]);

            if (temp != null) {
                temp.next = node;
                temp = temp.next;
            }

            if (i == 0) {
                head = node;
                temp = node;
            }
        }

        RemoveDuplicateFromSortedList.ListNode listNode = removeDuplicateFromSortedList.deleteDuplicates(head);

        /*while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }*/

        int i = 0;
        while (listNode != null) {
            assertEquals(expected[i], listNode.val);
            i++;
            listNode = listNode.next;
        }
    }
}