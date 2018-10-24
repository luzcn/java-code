package leetcode;

/**
 * Given a linked list, swap every two adjacent nodes and return its head.
 *
 * Example:
 *
 * Given 1->2->3->4, you should return the list as 2->1->4->3. Note:
 *
 * Your algorithm should use only constant extra space. You may not modify the values in the list's
 * nodes, only nodes itself may be changed.
 */
public class SwapNodesInPairs {

  public ListNode swapPairs(ListNode head) {

    if (head == null || head.next == null) {
      return head;
    }

    ListNode newHead = new ListNode(-1);
    ListNode prev = newHead;
    ListNode p = head;
    ListNode q = head.next;

    while (p != null && q != null) {

      // the pointer pointing to the next p needs to go
      ListNode temp = q.next;

      p.next = q.next;
      q.next = p;

      prev.next = q;
      p = temp;

      if (p != null) {
        q = p.next;
      }

      prev = prev.next.next;
    }

    return newHead.next;
  }
}
