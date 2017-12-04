package leetcode;

/**
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 * <p>
 * Add the two numbers and return it as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 */
public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode result = new ListNode(-1);

        ListNode h = result;
        ListNode p1 = l1;
        ListNode p2 = l2;

        int carry = 0;

        while (p1 != null && p2 != null) {
            int sum = p1.val + p2.val + carry;
            carry = sum / 10;

            ListNode n = new ListNode(sum % 10);

            h.next = n;
            h = n;

            p1 = p1.next;
            p2 = p2.next;

        }

        while (p1 != null) {
            int sum = p1.val + carry;
            carry = sum / 10;
            ListNode n = new ListNode(sum % 10);

            h.next = n;
            h = n;

            p1 = p1.next;
        }

        while (p2 != null) {
            int sum = p2.val + carry;
            carry = sum / 10;
            ListNode n = new ListNode(sum % 10);

            h.next = n;
            h = n;

            p2 = p2.next;
        }

        return result.next;
    }
}
