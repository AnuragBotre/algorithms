package com.trendcore.problems.leetcode;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class RemoveDuplicatesFromSortedListIITest {

    RemoveDuplicatesFromSortedListII r = new RemoveDuplicatesFromSortedListII();

    @Test
    public void input1() {
        act(toList(1, 2, 3, 3, 4, 4, 5), toList(1, 2, 5));
    }

    @Test
    public void duplicateElementAtStart() {
        act(toList(1, 1, 1, 3, 3, 4, 4, 5), toList(5));
    }

    @Test
    public void allDuplicate() {
        act(toList(1, 1, 2, 2), toList());
    }

    @Test
    public void input4() {
        act(toList(1, 2, 2), toList(1));
    }

    @Test
    public void input5() {
        act(toList(1), toList(1));
    }

    @Test
    public void input6() {
        act(toList(1, 2, 3), toList(1, 2, 3));
    }

    private void act(RemoveDuplicatesFromSortedListII.ListNode inputListNode, RemoveDuplicatesFromSortedListII.ListNode expectedListNode) {
        RemoveDuplicatesFromSortedListII.ListNode outputListNode = r.deleteDuplicates(inputListNode);

        while (expectedListNode != null) {
            System.out.print(expectedListNode.val + ":" + outputListNode.val + ",");
            assertEquals(expectedListNode.val, outputListNode.val);
            expectedListNode = expectedListNode.next;
            outputListNode = outputListNode.next;
        }

    }

    private RemoveDuplicatesFromSortedListII.ListNode toList(int... nums) {

        RemoveDuplicatesFromSortedListII.ListNode head = null;
        RemoveDuplicatesFromSortedListII.ListNode listNode = null;

        for (int i = 0; i < nums.length; i++) {
            if (head == null) {
                head = r.new ListNode(nums[i]);
                listNode = head;
            } else {
                if (listNode.next == null) {
                    listNode.next = r.new ListNode(nums[i]);
                }
                listNode = listNode.next;

            }
        }


        return head;
    }
}