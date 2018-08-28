package com.trendcore.problems;

public class ReverseLinkedList {

    public class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        ReverseLinkedList r = new ReverseLinkedList();
        ListNode l = r.new ListNode(1);
        l.next = r.new ListNode(2);
        l.next.next = r.new ListNode(3);

        ListNode t = l;
        ListNode prev = null;

        print(l);

        while(t != null){
            ListNode cur = t;
            t = t.next;
            cur.next = prev;
            prev = cur;
        }

        l = prev;

        print(l);
    }

    private static void print(ListNode t) {
        System.out.println();
        while (t != null){
            System.out.print(" " + t.val);
            t = t.next;
        }
    }

}
