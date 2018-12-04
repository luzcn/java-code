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


  public static class Node {

    public int val;
    public Node prev;
    public Node next;
    public Node child;

    public Node() {
    }

    public Node(int _val) {
      val = _val;
    }

    public Node(int _val, Node _prev, Node _next, Node _child) {
      val = _val;
      prev = _prev;
      next = _next;
      child = _child;
    }
  }


  // dfs idea, but no recursive needed
  // we just append/insert the child linked list into the current level list
  // set the current node child to null and keep moving
  public Node flatten(Node head) {

    Node p = head;

    while (p != null) {

      if (p.child == null) {
        p = p.next;
        continue;
      }

      int c = p.val;
      Node next = p.next;

      Node child = p.child;
      while (child.next != null) {
        child = child.next;
      }

      p.next = p.child;
      p.child.prev = p;
      p.child = null;

      child.next = next;

      if (next != null) {
        next.prev = child;
      }
    }

    return head;
  }


}

// public static void main(String[] args) {
//     FlattenAMultilevelDLL_430 sol = new FlattenAMultilevelDLL_430();
//
//     FlattenAMultilevelDLL_430.Node n1 = new Node(1);
//     FlattenAMultilevelDLL_430.Node n2 = new Node(2);
//     FlattenAMultilevelDLL_430.Node n3 = new Node(3);
//     FlattenAMultilevelDLL_430.Node n4 = new Node(4);
//     FlattenAMultilevelDLL_430.Node n5 = new Node(5);
//     FlattenAMultilevelDLL_430.Node n6 = new Node(6);
//     FlattenAMultilevelDLL_430.Node n7 = new Node(7);
//     FlattenAMultilevelDLL_430.Node n8 = new Node(8);
//     FlattenAMultilevelDLL_430.Node n9 = new Node(9);
//     FlattenAMultilevelDLL_430.Node n10 = new Node(10);
//     FlattenAMultilevelDLL_430.Node n11 = new Node(11);
//     FlattenAMultilevelDLL_430.Node n12 = new Node(12);
//
//     n1.next = n2;
//     n2.prev = n1;
//
//     n2.next = n3;
//     n3.prev = n2;
//
//     n3.next = n4;
//     n4.prev = n3;
//
//     n4.next = n5;
//     n5.prev = n4;
//
//     n5.next = n6;
//     n6.prev = n5;
//
//     n3.child = n7;
//
//     n7.next = n8;
//     n8.prev = n7;
//
//     n8.next = n9;
//     n9.prev = n8;
//
//     n8.child = n10;
//     n10.next = n11;
//     n11.prev = n10;
//     n11.next = n12;
//     n12.prev = n11;
//
//     var res = sol.flatten(n1);
//
//     while (res != null) {
//       System.out.println(res.val);
//       res = res.next;
//     }
//   }