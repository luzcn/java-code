package leetcode;

/////
// You are given two non-empty linked lists representing two non-negative integers.
// The digits are stored in reverse order and each of their nodes contain a single digit.
//
// Add the two numbers and return it as a linked list.
//
// You may assume the two numbers do not contain any leading zero, except the number 0 itself.
// Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
// Output: 7 -> 0 -> 8
///
public class AddTwoNumbers {

  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

    ListNode result = new ListNode(-1);

    ListNode h = result;
    ListNode p1 = l1;
    ListNode p2 = l2;

    int carry = 0;

    while (p1 != null || p2 != null) {
      int sum = carry + (p1 == null ? 0 : p1.val) + (p2 == null ? 0 : p2.val);
      carry = sum / 10;

      h.next = new ListNode(sum % 10);
      h = h.next;

      if (p1 != null) {
        p1 = p1.next;
      }

      if (p2 != null) {
        p2 = p2.next;
      }
    }

    if (carry > 0) {
      h.next = new ListNode(carry);
    }

    return result.next;
  }
}
