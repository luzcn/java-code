package leetcode;

/**
 * Remove all elements from a linked list of integers that have value val.
 *
 * Example Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6 Return: 1 --> 2 --> 3 --> 4 --> 5
 *
 * Credits: Special thanks to @mithmatt for adding this problem and creating all test cases.
 */
public class RemoveLinkedListElements {

  // two pointer
  // be careful it may need to remove all nodes.
  public ListNode removeElements(ListNode head, int val) {
    ListNode p = head;
    ListNode dummyHead = new ListNode(0);
    dummyHead.next = head;
    ListNode prev = dummyHead;

    while (p != null && p.val != val) {
      prev = p;
      p = p.next;
    }

    if (p == null) {
      // no value found
      return head;
    }

    ListNode q = p.next;
    while (q != null) {
      if (q.val != val) {
        p.val = q.val;
        prev = p;
        p = p.next;
      }
      q = q.next;
    }

    // all the next elements equal val
    prev.next = null;

    return dummyHead.next;
  }
}
