package leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MergeKSortedList {

  // merge two sorted list solution
  // TLE
  private ListNode merge(ListNode l1, ListNode l2) {
    if (l1 == null) {
      return l2;
    }

    if (l2 == null) {
      return l1;
    }

    ListNode head = new ListNode(-1);
    ListNode prev = head;
    ListNode p = l1;
    ListNode q = l2;

    while (p != null && q != null) {
      if (p.val <= q.val) {
        prev.next = p;
        p = p.next;
      } else {
        prev.next = q;
        q = q.next;
      }
      prev = prev.next;
    }

    if (p != null) {
      prev.next = p;
    }

    if (q != null) {
      prev.next = q;
    }

    return head.next;
  }


  // merge with heap
  private ListNode mergeWithHeadp(ListNode[] lists) {

    PriorityQueue<ListNode> minHeap = new PriorityQueue<>();

    Arrays.stream(lists).forEach(x -> {
      if (x != null) {
        minHeap.add(x);
      }
    });

    ListNode head = new ListNode(-1);
    ListNode prev = head;

    while (!minHeap.isEmpty()) {
      ListNode node = minHeap.poll();

      prev.next = node;
      prev = prev.next;

      if (node.next != null) {
        minHeap.add(node.next);
      }
    }

    return head.next;
  }

  public ListNode mergeKLists(ListNode[] lists) {
    if (lists.length == 0) {
      return null;
    }

    return mergeWithHeadp(lists);
  }
}
