package leetcode;

// You are given a doubly linked list which in addition to the next and previous pointers,
// it could have a child pointer, which may or may not point to a separate doubly linked list.
//
// These child lists may have one or more children of their own, and so on, to produce a multilevel data structure, as shown in the example below.
//
// Flatten the list so that all the nodes appear in a single-level, doubly linked list.
// You are given the head of the first level of the list.
//
//
//
// Example:
//
// Input:
//  1---2---3---4---5---6--NULL
//          |
//          7---8---9---10--NULL
//              |
//              11--12--NULL
//
// Output:
// 1-2-3-7-8-11-12-9-10-4-5-6-NULL
public class FlattenAMultilevelDLL_430 {


  private class Node {

    public int val;
    public Node prev;
    public Node next;
    public Node child;

    public Node() {
    }

    public Node(int _val, Node _prev, Node _next, Node _child) {
      val = _val;
      prev = _prev;
      next = _next;
      child = _child;
    }
  }


  public Node flatten(Node head) {

    Node newHead = new Node();
    prev = newHead;

    return newHead.next;
  }

  private Node prev = null;

  private void dfs(Node node) {
    if (node == null) {
      return;
    }

    Node newNode = new Node();
    newNode.val = node.val;

    prev.next = newNode;
    newNode.prev = prev;
    prev = newNode;

    dfs(node.child);
    dfs(node.next);

  }
}
