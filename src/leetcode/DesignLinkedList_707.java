package leetcode;

// Design your implementation of the linked list.
// You can choose to use the singly linked list or the doubly linked list.
//
// A node in a singly linked list should have two attributes: val and next.
// val is the value of the current node, and next is a pointer/reference to the next node.
//
// If you want to use the doubly linked list, you will need one more attribute prev to indicate the previous node in the linked list.
// Assume all nodes in the linked list are 0-indexed.
//
// Implement these functions in your linked list class:
//
// - get(index) : Get the value of the index-th node in the linked list. If the index is invalid, return -1.
//
// - addAtHead(val) : Add a node of value val before the first element of the linked list.
// After the insertion, the new node will be the first node of the linked list.
//
// - addAtTail(val) : Append a node of value val to the last element of the linked list.
//
// - addAtIndex(index, val) : Add a node of value val before the index-th node in the linked list.
// If index equals to the length of linked list, the node will be appended to the end of linked list.
// If index is greater than the length, the node will not be inserted.
//
// - deleteAtIndex(index) : Delete the index-th node in the linked list, if the index is valid.
// Example:
//
// MyLinkedList linkedList = new MyLinkedList();
// linkedList.addAtHead(1);
// linkedList.addAtTail(3);
// linkedList.addAtIndex(1, 2);  // linked list becomes 1->2->3
// linkedList.get(1);            // returns 2
// linkedList.deleteAtIndex(1);  // now the linked list is 1->3
// linkedList.get(1);            // returns 3
public class DesignLinkedList_707 {

  public DListNode head;
  private DListNode tail;
  private int length;

  /**
   * Initialize your data structure here.
   */
  public DesignLinkedList_707() {
    head = new DListNode(0);
    tail = new DListNode(0);

    head.next = tail;
    tail.prev = head;

    length = 0;
  }

  /**
   * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
   */
  public int get(int index) {
    if (index >= length) {
      return -1;
    }

    DListNode p = head.next;
    int count = 0;
    while (p != tail && count < index) {
      p = p.next;
      count++;
    }

    return p.value;
  }

  /**
   * Add a node of value val before the first element of the linked list. After the insertion, the
   * new node will be the first node of the linked list.
   */
  public void addAtHead(int val) {
    DListNode newNode = new DListNode(val);

    newNode.next = head.next;
    newNode.prev = head;
    head.next.prev = newNode;
    head.next = newNode;

    length++;
  }

  /**
   * Append a node of value val to the last element of the linked list.
   */
  public void addAtTail(int val) {
    DListNode newNode = new DListNode(val);

    newNode.next = tail;
    newNode.prev = tail.prev;
    tail.prev.next = newNode;
    tail.prev = newNode;

    length++;
  }

  /**
   * Add a node of value val before the index-th node in the linked list. If index equals to the
   * length of linked list, the node will be appended to the end of linked list. If index is greater
   * than the length, the node will not be inserted.
   */
  public void addAtIndex(int index, int val) {

    if (index > length) {
      return;
    }

    DListNode newNode = new DListNode(val);
    DListNode p = head.next;
    int count = 0;
    while (p != tail && count < index) {
      p = p.next;
      count++;
    }

    newNode.next = p;
    newNode.prev = p.prev;
    p.prev.next = newNode;
    p.prev = newNode;

    length++;
  }

  /**
   * Delete the index-th node in the linked list, if the index is valid.
   */
  public void deleteAtIndex(int index) {
    if (index < 0 || index >= length) {
      return;
    }

    DListNode p = head.next;
    int count = 0;
    while (p != tail && count < index) {
      p = p.next;
      count++;
    }

    if (p != tail) {
      p.prev.next = p.next;
      p.next.prev = p.prev;

      p.next = null;
      p.prev = null;
    }

    length--;
  }

  public class DListNode {

    public DListNode prev;
    public DListNode next;
    public int value;

    DListNode(int val) {
      this.value = val;
      prev = null;
      next = null;
    }
  }
}