package com.trendcore.problems.leetcode;

import java.util.ArrayList;
import java.util.List;

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

        ListNode l1 = m.new ListNode(1);
        l1.next = m.new ListNode(4);
        l1.next.next = m.new ListNode(5);

        ListNode l2 = m.new ListNode(1);
        l2.next = m.new ListNode(3);
        l2.next.next = m.new ListNode(4);

        ListNode l3 = m.new ListNode(2);
        l3.next = m.new ListNode(6);


        print(m.merge(l1, l2));
        print(m.merge(l1, null));
        print(m.merge(null, l1));

        ListNode[] lists = new ListNode[3];
        lists[0] = l1;
        lists[1] = l2;
        lists[2] = l3;
        print(m.mergeKLists(lists));
    }

    private static void print(ListNode merge) {
        System.out.println();
        ListNode l = merge;
        while (l != null) {
            System.out.print(" " + l.val);
            l = l.next;
        }
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        //brute force
        return divideAndConquer(lists);

    }

    private ListNode divideAndConquer(ListNode[] lists) {
        //TODO : This needs to be implemented
        return null;
    }

    private ListNode approach2(ListNode[] lists) {

        if (lists.length == 0)
            return null;

        ListNode mergedListRoot;
        ListNode mergedList = mergedListRoot = new ListNode(0);

        boolean flag = true;

        int originalLength = lists.length;

        while (flag) {
            //List list = new ArrayList(originalLength);
            ListNode list[] = new ListNode[originalLength];
            boolean isEmpty = true;
            for (int i = 0; i < lists.length; i++) {
                //store elements in the list
                if (lists[i] != null) {
                    //list.add(i, lists[i].val);
                    list[i] = lists[i];
                    isEmpty = false;
                }
            }

            if (isEmpty) {
                break;
            }

            int returnArgs[] = findMinimum(list);
            int val = returnArgs[0];
            int pos = returnArgs[1];

            mergedList.next = new ListNode(lists[pos].val);
            lists[pos] = lists[pos].next;
            mergedList = mergedList.next;

        }

        mergedListRoot = mergedListRoot.next;

        return mergedListRoot;
    }

    private int[] findMinimum(ListNode[] list) {
        int min = 0;
        int cnt = 0;
        int pos = 0;
        for (int i = 0; i < list.length; i++) {
            if (list[i] == null) {
                continue;
            }
            if (cnt == 0) {
                min = list[i].val;
                pos = i;
                cnt++;
            } else {
                if (list[i].val < min) {
                    min = list[i].val;
                    pos = i;
                }
            }

        }


        return new int[]{min, pos};
    }

    private ListNode bruteForce(ListNode[] lists) {

        if (lists.length == 0)
            return null;

        ListNode mergedList = lists[0];
        for (int i = 1; i < lists.length; i++) {
            mergedList = merge(mergedList, lists[i]);
        }

        return mergedList;
    }

    private ListNode merge(ListNode list1, ListNode list2) {
        ListNode mergeList;
        ListNode mergeListRoot = mergeList = new ListNode(0);
        while (list1 != null || list2 != null) {
            if (list1 != null && list2 != null) {
                if (list1.val < list2.val) {
                    mergeList.next = new ListNode(list1.val);
                    list1 = list1.next;
                } else {
                    mergeList.next = new ListNode(list2.val);
                    list2 = list2.next;
                }
            } else {
                if (list1 != null && list2 == null) {
                    mergeList.next = new ListNode(list1.val);
                    list1 = list1.next;
                } else if (list1 == null && list2 != null) {
                    mergeList.next = new ListNode(list2.val);
                    list2 = list2.next;
                }
            }
            mergeList = mergeList.next;
        }
        mergeListRoot = mergeListRoot.next;
        return mergeListRoot;
    }

    /**
     * Leet Code Solution
     */
    class Solution {
        public ListNode mergeKLists(ListNode[] lists) {
            if (lists.length == 0) return null;
            return merge(lists, 0, lists.length - 1);
        }

        private ListNode merge(ListNode[] lists, int left, int right) {
            if (left == right) return lists[right];
            int mid = left + (right - left) / 2;
            ListNode node1 = merge(lists, left, mid);
            ListNode node2 = merge(lists, mid + 1, right);
            ListNode pre = new ListNode(0), p = pre;
            while (node1 != null && node2 != null) {
                if (node1.val <= node2.val) {
                    p.next = node1;
                    node1 = node1.next;
                } else {
                    p.next = node2;
                    node2 = node2.next;
                }
                p = p.next;
            }
            if (node1 != null) {
                p.next = node1;
            }
            if (node2 != null) {
                p.next = node2;
            }
            return pre.next;
        }
    }

}
