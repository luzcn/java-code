package leetcode;

// Given a sorted linked list, delete all nodes that have duplicate numbers,
// leaving only distinct numbers from the original list.
//
// Example 1:
//
// Input: 1->2->3->3->4->4->5
// Output: 1->2->5
// Example 2:
//
// Input: 1->1->1->2->3
// Output: 2->3
public class RemoveDuplicatesFromSortedList2_82 {

  public ListNode deleteDuplicates(ListNode head) {

    if (head == null || head.next == null) {
      return head;
    }

    // use a test head node to indicate the prev
    ListNode newHead = new ListNode(-1);
    newHead.next = head;
    ListNode prev = newHead;

    ListNode p = head;
    ListNode q = head.next;

    // use a counter to check if we need to change the prev or move the prev,
    // when we see a non-duplicate p and q
    int count = 0;
    while (true) {
      if (q == null) {
        if (count > 0) {
          prev.next = null;

        }

        break;
      }

      if (q.val == p.val) {
        count++;
      } else {

        // not duplicate pair
        if (count > 0) {

          // there are duplicates to delete
          // modify the prev.next pointing to q
          // and reset the counter.
          prev.next = q;
          count = 0;
        } else {
          // no duplicate nodes to remove, simply move the prev node to current p
          prev = p;
        }
        p = q;
      }

      q = q.next;

    }

    return newHead.next;
  }
}
