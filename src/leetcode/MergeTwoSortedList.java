package leetcode;

/**
 * Merge two sorted linked list e.g. l1: 1->2->4 l2: 1->3->4
 *
 * after merge: 1->1->2->3->4
 */
public class MergeTwoSortedList {

  public ListNode merge(ListNode l1, ListNode l2) {
    if (l1 == null) {
      return l2;
    }

    if (l2 == null) {
      return l1;
    }

    ListNode result = new ListNode(-1);
    ListNode p = l1;
    ListNode q = l2;
    ListNode h = result;

    while (p != null && q != null) {
      if (p.val <= q.val) {
        h.next = p;
        p = p.next;
      } else {
        h.next = q;
        q = q.next;
      }

      h = h.next;
    }

    if (p != null) {
      h.next = p;
    }

    if (q != null) {
      h.next = q;
    }

    return result.next;
  }
}
