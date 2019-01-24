package leetcode;

import java.util.HashSet;

// We are given head, the head node of a linked list containing unique integer values.
//
// We are also given the list G, a subset of the values in the linked list.
//
// Return the number of connected components in G, where two values are connected if they appear consecutively in the linked list.
//
// Example 1:
//
// Input:
// head: 0->1->2->3
// G = [0, 1, 3]
// Output: 2
// Explanation:
// 0 and 1 are connected, so [0, 1] and [3] are the two connected components.
// Example 2:
//
// Input:
// head: 0->1->2->3->4
// G = [0, 3, 1, 4]
// Output: 2
// Explanation:
// 0 and 1 are connected, 3 and 4 are connected, so [0, 1] and [3, 4] are the two connected components.
public class LinkedListComponents_817 {

  public int numComponents(ListNode head, int[] G) {

    if (head == null || G.length == 0) {
      return 0;
    }

    HashSet<Integer> set = new HashSet<>();
    for (int g : G) {
      set.add(g);
    }

    ListNode p = head;
    int count = 0;

    while (p != null) {

      if (!set.contains(p.val)) {
        p = p.next;
        continue;
      }

      count++;
      while (p != null && set.contains(p.val)) {
        p = p.next;
      }


    }
    return count;
  }
}
