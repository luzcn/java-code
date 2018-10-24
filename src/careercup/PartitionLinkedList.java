package careercup;

import leetcode.ListNode;

// partition a linked list by a give value X
// such that all node less than X come before all nodes larger or equal than X
public class PartitionLinkedList {

  public ListNode partition(ListNode head, int x) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode lessHead = new ListNode(-1);
    ListNode largeHead = new ListNode(-1);

    ListNode prevLess = lessHead;
    ListNode prevLarge = largeHead;

    ListNode p = head;

    while (p != null) {
      if (p.val < x) {
        prevLess.next = p;
        prevLess = prevLess.next;
      } else {
        prevLarge.next = p;
        prevLarge = prevLarge.next;
      }

      p = p.next;
    }

    prevLess.next = largeHead.next;
    prevLarge.next = null;

    return lessHead.next;
  }

  // public static void main(String[] args) {
  //     var head = ListNode.buildList(new int[]{3, 5, 8, 5, 10, 2, 2, 1});
  //     PartitionLinkedList par = new PartitionLinkedList();
  //
  //     ListNode p = par.partition(head, 5);
  //
  //     ListNode.print(p);
  //
  // }
}
