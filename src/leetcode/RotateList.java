package leetcode;

// Given a linked list, rotate the list to the right by k places, where k is non-negative.
//
// Example 1:
// Input: 1->2->3->4->5->NULL, k = 2
// Output: 4->5->1->2->3->NULL
//
// Explanation:
// rotate 1 steps to the right: 5->1->2->3->4->NULL
// rotate 2 steps to the right: 4->5->1->2->3->NULL
//
// Example 2:
// Input: 0->1->2->NULL, k = 4
// Output: 2->0->1->NULL
//
// Explanation:
// rotate 1 steps to the right: 2->0->1->NULL
// rotate 2 steps to the right: 1->2->0->NULL
// rotate 3 steps to the right: 0->1->2->NULL
// rotate 4 steps to the right: 2->0->1->NULL
public class RotateList {

  public ListNode rotateRight(ListNode head, int k) {
    // 1. get the total length n
    // 2. k = k % n
    // 3. find the last k nodes

    if (head == null || k <= 0) {
      return head;
    }

    int length = 0;
    ListNode p = head;
    ListNode tail = null;

    while (p != null) {
      tail = p;
      length++;
      p = p.next;
    }

    k = k % length;
    if (k == 0) {
      return head;
    }

    int count = 0;
    ListNode q = head;
    // move  k steps first
    while (q != null && count < k) {
      q = q.next;
      count++;
    }

    // move two pointer together
    // the distance between p and q should be k
    p = head;
    while (q != null && q.next != null) {
      p = p.next;
      q = q.next;
    }

    // p.next is the new head now
    // save it and make p.next point to null
    ListNode newHead = p.next;
    p.next = null;

    // rotate
    tail.next = head;

    return newHead;
  }
}
