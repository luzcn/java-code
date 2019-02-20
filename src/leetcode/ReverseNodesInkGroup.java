package leetcode;

/**
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified
 * list.
 *
 * k is a positive integer and is less than or equal to the length of the linked list. If the number
 * of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 *
 * Example:
 *
 * Given this linked list: 1->2->3->4->5
 *
 * For k = 2, you should return: 2->1->4->3->5
 *
 * For k = 3, you should return: 3->2->1->4->5
 */
public class ReverseNodesInkGroup {

  public ListNode reverseKGroup(ListNode head, int k) {

    if (head == null || k <= 0) {
      return head;
    }

    int length = 0;
    ListNode p = head;
    while (p != null) {
      length++;
      p = p.next;
    }

    return dfs(head, k, length);
  }

  private ListNode dfs(ListNode head, int k, int size) {

    if (size < k) {
      return head;
    }

    if (head == null) {
      return null;
    }

    int count = 0;
    ListNode p = head;

    // get the (p + k)th node
    while (p != null && count++ < k) {
      p = p.next;
    }

    ListNode prev = dfs(p, k, size - k);

    ListNode q = head;
    count = 0;
    while (q != null && count++ < k) {
      // reverse the k-group list nodes
      ListNode temp = q.next;
      q.next = prev;
      prev = q;
      q = temp;
    }

    return prev;
  }
}
