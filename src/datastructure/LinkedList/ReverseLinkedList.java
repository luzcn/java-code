package datastructure.LinkedList;

import leetcode.ListNode;

public class ReverseLinkedList {

  // iterative solution
  private ListNode reverseIterative(ListNode head) {
    ListNode prev = null;
    ListNode p = head;
    while (p != null) {
      ListNode q = p.next;
      p.next = prev;
      prev = p;
      p = q;
    }

    return prev;
  }


  // recursive solution
  private ListNode reverseRec(ListNode node, ListNode prev) {

    if (node == null) {
      return null;
    }

    if (node.next == null) {
      node.next = prev;
      return node;
    }

    // this returned res is the last element,
    // which would be head of reversed list
    ListNode res = reverseRec(node.next, node);

    node.next = prev;

    return res;
  }

  public ListNode reverse(ListNode head) {
    return this.reverseRec(head, null);
  }


  /**
   * Reverse a linked list from position m to n. Do it in-place and in one-pass.
   *
   * For example: Given 1->2->3->4->5->NULL, m = 2 and n = 4,
   *
   * return 1->4->3->2->5->NULL.
   *
   * Note: Given m, n satisfy the following condition: 1 ≤ m ≤ n ≤ length of list.
   */
  public ListNode reverseInRange(ListNode head, int m, int n) {
    if (m >= n || head == null) {
      return head;
    }

    ListNode preM = null;
    ListNode afterN = null;
    ListNode nodeM = null;

    int count = 1;
    ListNode p = head;

    // find the node previous node at m
    // and when count == n, p is the node at n, which would be the new head of reversed m to n list
    while (p != null && count < n) {
      if (count < m) {
        preM = p;
      }

      p = p.next;
      count++;
    }

    // if preM is null, the input m is 1
    // need to reverse the list from beginning to node at n
    // so, the node "prev" which is the head of reversed list will be the new head to return
    // otherwise, return the original head.
    if (preM != null) {
      nodeM = preM.next;
    } else {
      nodeM = head;
    }

    // if p is null, n is the end node of original list
    // there is no "afterN" node.
    if (p != null) {
      afterN = p.next;
      p.next = null;
    }

    // reverse the list from m to n
    // prev will be the new head
    ListNode prev = null;
    p = nodeM;
    while (p != null) {
      ListNode temp = p.next;
      p.next = prev;
      prev = p;
      p = temp;
    }

    // rebuild the list
    if (preM != null) {
      preM.next = prev;
    }
    nodeM.next = afterN;

    if (m == 1) {
      return prev;
    } else {
      return head;
    }
  }
}
