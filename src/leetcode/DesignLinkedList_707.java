package leetcode;

import java.util.*;

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

    private ListNode head;
    private ListNode tail;

    /**
     * Initialize your data structure here.
     */
    public DesignLinkedList_707() {
        head = null;
        tail = null;
    }

    /**
     * Get the value of the index-th node in the linked list.
     * If the index is invalid, return -1.
     */
    public int get(int index) {
        if (head == null) {
            return -1;
        }

        ListNode p = head;
        int count = 0;

        while (p != null && count < index) {
            p = p.next;
            count++;
        }

        return p == null ? -1 : p.value;
    }

    /**
     * Add a node of value val before the first element of the linked list.
     * After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {

        if (head == null) {
            head = new ListNode(val);
            tail = head;
            return;
        }

        ListNode newHead = new ListNode(val);
        head.prev = newHead;
        newHead.next = head;

        head = newHead;
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        if (tail == null) {
            tail = new ListNode(val);
            head = tail;
            return;
        }
        ListNode newTail = new ListNode(val);
        tail.next = newTail;
        newTail.prev = tail;

        tail = newTail;
    }

    /**
     * Add a node of value val before the index-th node in the linked list.
     * If index equals to the length of linked list, the node will be appended to the end of linked list.
     * If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {
        if (head == null) {
            addAtHead(val);
            return;
        }

        ListNode p = head;
        int count = 0;

        while (p != null && count < index) {
            p = p.next;
            count++;
        }

        if (p == null && count == index) {
            addAtTail(val);
            return;
        }

        if (p != null) {
            ListNode newNode = new ListNode(val);

            if (p.prev != null) {
                p.prev.next = newNode;
            }

            newNode.prev = p.prev;
            newNode.next = p;
            p.prev = newNode;
        }

    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        ListNode p = head;
        int count = 0;

        while (p != null && count < index) {
            p = p.next;
            count++;
        }

        if (p == null) {
            return;
        }

        if (p == head) {
            head = head.next;
            if (head == null) {
                tail = null;
            } else {
                head.prev = null;
            }
            return;
        }

        if (p == tail) {
            tail = tail.prev;
            if (tail == null) {
                head = null;
            } else {
                tail.next = null;
            }
            return;
        }

        if (p.prev != null) {
            p.prev.next = p.next;
        }

        if (p.next != null) {
            p.next.prev = p.prev;
        }

    }

    private class ListNode {

        ListNode prev;
        ListNode next;

        int value;

        ListNode(int val) {
            value = val;
        }
    }

}
