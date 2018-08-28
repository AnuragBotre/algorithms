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

        ListNode l1 = m.new ListNode(1);
        l1.next = m.new ListNode(4);
        l1.next.next = m.new ListNode(5);

        ListNode l2 = m.new ListNode(1);
        l2.next = m.new ListNode(3);
        l2.next.next = m.new ListNode(4);

        ListNode l3 = m.new ListNode(2);
        l3.next = m.new ListNode(6);


        print(m.merge(l1, l2));
        print(m.merge(l1,null));
        print(m.merge(null,l1));

        ListNode[] lists = new ListNode[3];
        lists[0] = l1;
        lists[1] = l2;
        lists[2] = l3;
        print(m.mergeKLists(lists));
    }

    private static void print(ListNode merge) {
        System.out.println();
        ListNode l = merge;
        while(l != null){
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
        return bruteForce(lists);

    }

    private ListNode bruteForce(ListNode[] lists) {

        if(lists.length == 0)
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
            if(list1 != null && list2 != null){
                if(list1.val < list2.val){
                    mergeList.next = new ListNode(list1.val);
                    list1 = list1.next;
                }else{
                    mergeList.next = new ListNode(list2.val);
                    list2 = list2.next;
                }
            }else{
                if(list1 != null && list2 == null){
                    mergeList.next = new ListNode(list1.val);
                    list1 = list1.next;
                }else if(list1 == null && list2 != null){
                    mergeList.next = new ListNode(list2.val);
                    list2 = list2.next;
                }
            }
            mergeList = mergeList.next;
        }
        mergeListRoot = mergeListRoot.next;
        return mergeListRoot;
    }

}
