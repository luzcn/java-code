package leetcode;

/**
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln, reorder it to: L0->Ln->L1->Ln-1->L2->Ln-2->…
 *
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 *
 * Example 1:
 *
 * Given 1->2->3->4, reorder it to 1->4->2->3. Example 2:
 *
 * Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
 */
public class ReorderList {


/*    private ListNode newHead = new ListNode(-1);
    private ListNode p;
    private ListNode prev = newHead;
    private int length = 0;
    private int count = 0;

    private boolean dfs(ListNode node) {
        if (node == null) {
            return false;
        }

        boolean isFinished = dfs(node.next);

        if (count > this.length / 2) {
            prev.next = node;
            // node.next = null;
            p.next = null;
            return true;
        }

        if (!isFinished) {
            prev.next = p;
            p = p.next;

            prev = prev.next;
            prev.next = node;
            prev = prev.next;
            count++;
        }
        // System.out.println(node.val);
        return isFinished;
    }*/


  // reverse linked list
  private ListNode reverse(ListNode head) {
    ListNode p = head;
    ListNode prev = null;

    while (p != null) {
      ListNode temp = p.next;
      p.next = prev;
      prev = p;
      p = temp;
    }
    return prev;
  }

  private void merge(ListNode head1, ListNode head2) {
    boolean isFirst = true;

    ListNode prev = head1;
    ListNode p = head1.next;
    ListNode q = head2;

    while (p != null && q != null) {

      if (isFirst) {
        prev.next = q;
        q = q.next;

      } else {
        prev.next = p;
        p = p.next;
      }
      prev = prev.next;
      isFirst = !isFirst;
    }

    if (q == null) {
      prev.next = p;
    }

    if (p == null) {
      prev.next = q;
    }
  }

  public void reorderList(ListNode head) {
    if (head == null || head.next == null) {
      return;
    }

    // count the total length of the list
    // - reverse the second half
    // - then merge these two list

    int length = 0;
    ListNode p = head;
    while (p != null) {
      length++;
      p = p.next;
    }

    p = head;
    int count = 1;
    while (p != null && count < length / 2) {
      p = p.next;
      count++;
    }

    // split into two linked list
    ListNode secondHead = p.next;
    // set the first half list end with null
    p.next = null;

    // reverse the second list
    merge(head, reverse(secondHead));
  }
}
