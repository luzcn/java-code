package leetcode;

import java.util.Random;

/**
 * Given a singly linked list, return a random node's value from the linked list. Each node must
 * have the same probability of being chosen.
 *
 * Follow up: What if the linked list is extremely large and its length is unknown to you? Could you
 * solve this efficiently without using extra space?
 *
 * Example:
 *
 * // Init a singly linked list [1,2,3]. ListNode head = new ListNode(1); head.next = new
 * ListNode(2); head.next.next = new ListNode(3); Solution solution = new Solution(head);
 *
 * // getRandom() should return either 1, 2, or 3 randomly. // Each element should have equal
 * probability of returning. solution.getRandom();
 */
public class LinkedListRandomNode {

  private int totalLength = 0;
  private Random random = new Random();
  private ListNode head;

  public LinkedListRandomNode(ListNode head) {
    this.head = head;

    ListNode p = head;
    while (p != null) {
      this.totalLength++;
      p = p.next;
    }
  }

  /**
   * Returns a random node's value.
   */
  public int getRandom() {
    int index = random.nextInt(this.totalLength);

    int count = 0;
    ListNode p = this.head;

    while (p != null && count < index) {
      p = p.next;
      count++;
    }

    return p == null ? 0 : p.val;
  }
}
