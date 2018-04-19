package leetcode;

/**
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 *
 * k is a positive integer and is less than or equal to the length of the linked list.
 * If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 *
 * Example:
 *
 * Given this linked list: 1->2->3->4->5
 *
 * For k = 2, you should return: 2->1->4->3->5
 *
 * For k = 3, you should return: 3->2->1->4->5
 */
public class ReverseNodesInkGroup {

    private ListNode reverse(ListNode head, ListNode end) {
        return null;
    }


    public ListNode reverseKGroup(ListNode head, int k) {

        if (head == null || k <= 0) {
            return head;
        }

        ListNode newHead = new ListNode(-1);
        ListNode prev = newHead;
        ListNode p = head;
        int count = 1;


        return newHead.next;

    }
}
