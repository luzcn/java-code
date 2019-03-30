package leetcode;

import java.util.HashMap;

// A linked list is given such that each node contains an additional random pointer
// which could point to any node in the list or null.
//
// Return a deep copy of the list.
public class CopyListWithRandomPointer {

  public Node copyRandomList(Node head) {
    if (head == null) {
      return null;
    }

    // use hash map to save the mapping of original node and new copied node
    HashMap<Node, Node> map = new HashMap<>();
    Node p = head;
    Node prev = null;

    while (p != null) {

      map.putIfAbsent(p, new Node(p.val));

      if (prev != null) {
        map.get(prev).next = map.get(p);
      }

      if (p.random != null) {
        map.putIfAbsent(p.random, new Node(p.random.val));
        map.get(p).random = map.get(p.random);
      }

      prev = p;
      p = p.next;
    }

    return map.get(head);
  }

  // O(1) space
  public Node copyRandomList2(Node head) {
    if (head == null) {
      return null;
    }

    Node p = head;
    while (p != null) {
      Node newNode = new Node(p.val);

      Node temp = p.next;

      // make the new copied node next pointing to p next
      newNode.next = temp;

      // current p next pointing to new copied node
      p.next = newNode;

      // move the p to next node of the original list
      p = temp;
    }

    Node newHead = head.next;

    //  construct the random pointer in copied list
    p = head;
    while (p != null) {
      if (p.random != null) {
        p.next.random = p.random.next;
      }
      p = p.next.next;
    }

    // rebuild the original list next pinters
    p = head;
    while (p != null) {
      Node copiedNode = p.next;

      if (copiedNode != null) {
        p.next = copiedNode.next;
      }

      p = copiedNode;
    }

    return newHead;
  }

  private class Node {

    public int val;
    public Node next;
    public Node random;

    public Node() {
    }

    public Node(int _val) {
      val = _val;
      next = null;
      random = null;
    }
  }
}
