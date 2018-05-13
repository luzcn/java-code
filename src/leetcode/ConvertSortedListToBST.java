package leetcode;

import java.util.*;

// Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
//
// For this problem, a height-balanced binary tree is defined as a binary tree
// in which the depth of the two subtrees of every node never differ by more than 1.
//
// Example:
//
// Given the sorted linked list: [-10,-3,0,5,9],
//
// One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
//
//       0
//      / \
//    -3   9
//    /   /
//  -10  5
public class ConvertSortedListToBST {


    // similar to convert bst from sorted array
    // divide conquer, count the total length of the input list
    // each recursive, divide to half of the size.
    private TreeNode convert(ListNode head, int size) {
        if (head == null || size <= 0) {
            return null;
        }

        int count = 0;
        ListNode p = head;
        while (p != null && count < size / 2) {
            p = p.next;
            count++;
        }

        // divide-conquer
        TreeNode root = new TreeNode(p.val);
        root.left = convert(head, size / 2);
        root.right = convert(p.next, size - size / 2 - 1);

        return root;
    }

    public TreeNode sortedListToBST(ListNode head) {

        // head is null
        if (head == null) {
            return null;
        }

        // only one node, directly return
        if (head.next == null) {
            return new TreeNode(head.val);
        }

        // get the total length of the input list
        int length = 0;
        ListNode p = head;
        while (p != null) {
            length++;
            p = p.next;
        }
        return convert(head, length);
    }
}
