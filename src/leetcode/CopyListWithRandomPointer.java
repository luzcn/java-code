package leetcode;

import java.util.*;

// A linked list is given such that each node contains an additional random pointer
// which could point to any node in the list or null.
//
// Return a deep copy of the list.
public class CopyListWithRandomPointer {

    // use hash map to save the mapping of original node and new copied node
    private HashMap<RandomListNode, RandomListNode> map = new HashMap<>();

    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }

        RandomListNode p = head;
        while (p != null) {
            // RandomListNode newNode = new RandomListNode(p.label);
            // map.putIfAbsent(p, newNode);

            map.put(p, new RandomListNode(p.label));
            p = p.next;
        }

        p = head;
        while (p != null) {

            if (p.next != null) {
                map.get(p).next = map.get(p.next);
            }
            if (p.random != null) {
                map.get(p).random = map.get(p.random);
            }

            p = p.next;
        }

        return map.get(head);
    }

    // O(1) space
    public RandomListNode copyRandomList2(RandomListNode head) {
        if (head == null) {
            return null;
        }

        RandomListNode p = head;
        while (p != null) {
            RandomListNode newNode = new RandomListNode(p.label);

            RandomListNode temp = p.next;

            // make the new copied node next pointing to p next
            newNode.next = temp;

            // current p next pointing to new copied node
            p.next = newNode;

            // move the p to next node of the original list
            p = temp;
        }

        RandomListNode newHead = head.next;

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
            RandomListNode copiedNode = p.next;

            if (copiedNode != null) {
                p.next = copiedNode.next;
            }

            p = copiedNode;
        }

        return newHead;
    }

    private class RandomListNode {

        int label;
        RandomListNode next;
        RandomListNode random;

        RandomListNode(int x) {
            this.label = x;
            next = null;
            random = null;
        }
    }
}
