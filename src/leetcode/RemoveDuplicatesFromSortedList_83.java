package leetcode;

// Given a sorted linked list, delete all duplicates such that each element appear only once.
//
// Example 1:
//
// Input: 1->1->2
// Output: 1->2
// Example 2:
//
// Input: 1->1->2->3->3
// Output: 1->2->3
public class RemoveDuplicatesFromSortedList_83 {

  public ListNode deleteDuplicates(ListNode head) {

    if (head == null || head.next == null) {
      return head;
    }

    ListNode p = head;
    ListNode q = p.next;

    while (q != null) {
      if (q.val != p.val) {
        p.next = q;
        p = q;
      }
      q = q.next;
    }

    p.next = null;
    return head;
  }
}
