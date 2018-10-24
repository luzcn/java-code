package leetcode;

import java.util.Stack;

// Convert a BST to a sorted circular doubly-linked list in-place.
// Think of the left and right pointers as synonymous to the previous and next pointers in a doubly-linked list.
//
// Let's take the following BST as an example, it may help you understand the problem better:
//
//
//
//
//
// We want to transform this BST into a circular doubly linked list.
// Each node in a doubly linked list has a predecessor and successor.
//
//
// For a circular doubly linked list, the predecessor of the first element is the last element,
// and the successor of the last element is the first element.
//
public class ConvertBSTDoublyLinkedList_426 {

  public Node treeToDoublyList(Node root) {

    if (root == null) {
      return root;
    }

    Node head = root;
    while (head.left != null) {
      head = head.left;
    }

    // inorder(root);

    head.left = prev;
    prev.right = head;

    return head;
  }

  private Node prev = null;


  private void inorderIterative(Node root) {
    Stack<Node> stack = new Stack<>();
    Node current = root;

    while (true) {
      if (current != null) {
        stack.push(current);
        current = current.left;
      } else if (!stack.isEmpty()) {
        current = stack.pop();

        if (prev != null) {
          current.left = prev;
          prev.right = current;
        }

        prev = current;

        current = current.right;
      } else {
        break;
      }
    }
  }

  private void inorder(Node node) {

    if (node == null) {
      return;
    }

    inorder(node.left);

    if (prev != null) {
      node.left = prev;
      prev.right = node;
    }

    prev = node;
    inorder(node.right);
  }

  private class Node {

    public int val;
    public Node left;
    public Node right;

    public Node() {
    }

    public Node(int _val, Node _left, Node _right) {
      val = _val;
      left = _left;
      right = _right;
    }
  }
}
