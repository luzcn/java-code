package leetcode;

import java.util.*;

// Given a non-negative integer represented as non-empty a singly linked list of digits, plus one to the integer.
//
// You may assume the integer do not contain any leading zero, except the number 0 itself.
//
// The digits are stored such that the most significant digit is at the head of the list.
//
// Example :
//
// Input: [1,2,3]
// Output: [1,2,4]
public class PlusOneLinkedList_369 {

  public ListNode plusOne(ListNode head) {
    if (head == null) {
      return null;
    }
    ListNode tail = reverse(head);

    ListNode p = tail;
    ListNode prev = null;
    while (p != null) {
      if (p.val == 9) {
        p.val = 0;
      } else {
        p.val++;
        break;
      }
      prev = p;
      p = p.next;
    }

    if (p == null) {
      prev.next = new ListNode(1);
    }

    return reverse(tail);
  }

  private ListNode reverse(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode prev = null;
    ListNode p = head;
    while (p != null) {
      ListNode next = p.next;
      p.next = prev;
      prev = p;
      p = next;
    }

    return prev;
  }
}
