package careercup.LinkedList;

import leetcode.ListNode;

public class ReverseLinkedList {

    // iterative solution
    private ListNode reverseIterative(ListNode head) {
        ListNode prev = null;
        ListNode p = head;
        while (p != null) {
            ListNode q = p.next;
            p.next = prev;
            prev = p;
            p = q;
        }

        return prev;
    }


    // recursive solution
    private ListNode reverseRec(ListNode node, ListNode prev) {

        if (node == null) {
            return null;
        }

        if (node.next == null) {
            node.next = prev;
            return node;
        }

        // this returned res is the last element,
        // which would be head of reversed list
        ListNode res = reverseRec(node.next, node);

        node.next = prev;

        return res;
    }

    public ListNode reverse(ListNode head) {
        return this.reverseRec(head, null);
    }


    /**
     * Reverse a linked list from position m to n. Do it in-place and in one-pass.
     *
     * For example:
     * Given 1->2->3->4->5->NULL, m = 2 and n = 4,
     *
     * return 1->4->3->2->5->NULL.
     *
     * Note:
     * Given m, n satisfy the following condition:
     * 1 ≤ m ≤ n ≤ length of list.
     */
    public ListNode reverse2(ListNode head, int m, int n) {
        if (m > n || head == null) {
            return null;
        }

        ListNode preM = null;
        ListNode after = null;

        int count = 1;
        ListNode p = head;

        while (p != null && count < n) {
            if (count < m) {
                preM = p;
            } else {

            }

            p = p.next;
            count++;
        }
        // node after n th node, could be null
        after = p.next;


        return null;

    }
}
