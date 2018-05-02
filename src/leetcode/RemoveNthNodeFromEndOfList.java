package leetcode;

/**
 * Given a linked list, remove the n-th node from the end of list and return its head.
 *
 * Example:
 *
 * Given linked list: 1->2->3->4->5, and n = 2.
 *
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 */
public class RemoveNthNodeFromEndOfList {

    // Two pointer
    // p move n steps first,
    // then both p and q move to end, until p.next is null,
    // now q.next is the element need to remove
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n <= 0) {
            return head;
        }

        ListNode p = head;
        int count = 0;

        while (p != null && count < n) {
            p = p.next;
            count++;
        }

        if (p == null) {
            // need to remove the head node
            return head.next;
        }

        // keep the "p" pointer unchanged,
        // the distance between q and p is always n
        ListNode q = head;

        while (p.next != null) {
            q = q.next;
            p = p.next;
        }

        // now q.next is the element need to remove
        ListNode toRemove = q.next;
        q.next = toRemove.next;
        toRemove.next = null;

        return head;
    }
}
