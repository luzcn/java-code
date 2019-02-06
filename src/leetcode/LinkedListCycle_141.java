package leetcode;

// Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
//
// Note: Do not modify the linked list.
//
// Follow up:
// Can you solve it without using extra space?
public class LinkedListCycle_141 {

  // two pointer
  // 快慢指针
  public ListNode detectCycle(ListNode head) {
    if (head == null || head.next == null) {
      return null;
    }

    ListNode p = head;
    ListNode q = head;

    while (q != null && q.next != null) {
      p = p.next;
      q = q.next.next;

      if (p == q) {
        break;
      }
    }

    if (q == null || q.next == null) {
      return null;
    }

    // now p == q and there is cycle
    p = head;
    while (p != q) {
      p = p.next;
      q = q.next;
    }

    return p;
  }
}
