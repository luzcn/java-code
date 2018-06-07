package leetcode;

import java.util.*;

// Two elements of a binary search tree (BST) are swapped by mistake.
//
// Recover the tree without changing its structure.
//
// Example 1:
//
// Input: [1,3,null,null,2]
//
//    1
//   /
//  3
//   \
//    2
//
// Output: [3,1,null,null,2]
//
//    3
//   /
//  1
//   \
//    2
public class RecoverBST {

    // inorder traverse, find the first wrong node
    // inorder traverse in reversed order, find the second wrong node
    // swap the first and second value

    private TreeNode first = null;
    private TreeNode second = null;
    private TreeNode prev = null;

    public void inOrder(TreeNode root) {
        TreeNode current = root;

        // Morris tree inOrder
        while (current != null) {
            if (current.left != null) {
                TreeNode node = current.left;

                while (node.right != null && node.right != current) {
                    node = node.right;
                }

                if (node.right == current) {
                    // recover the right pointer
                    node.right = null;

                    if (prev != null && prev.val > current.val) {
                        first = prev;
                        // break;
                    }
                    // System.out.println(current.val);
                    prev = current;
                    current = current.right;

                } else {
                    // make the right-most node of left subtree right pointer pointing to current
                    node.right = current;
                    prev = current;
                    current = current.left;
                }

            } else {
                if (prev != null && prev.val > current.val) {
                    first = prev;
                    // break;
                }

                // System.out.println(current.val);
                prev = current;
                current = current.right;
            }
        }
    }

    public void inOrderReversed(TreeNode root) {
        // Morris tree reversed inOrder
        TreeNode current = root;
        prev = null;
        while (current != null) {
            if (current.right != null) {
                TreeNode node = current.right;

                while (node.left != null && node.left != current) {
                    node = node.left;
                }

                // recover the right pointer
                if (node.left == current) {
                    node.left = null;
                    if (prev != null && prev.val < current.val) {
                        second = prev;
                        // break;
                    }
                    prev = current;
                    // System.out.println(current.val);
                    current = current.left;

                } else {
                    // make the right-most node of left subtree right pointer pointing to current
                    node.left = current;
                    prev = current;
                    current = current.right;
                }

            } else {
                if (prev != null && prev.val < current.val) {
                    second = prev;
                    // break;
                }
                // System.out.println(current.val);
                prev = current;
                current = current.left;
            }
        }
    }

    // use Morris tree
    public void recoverTree(TreeNode root) {

        if (root == null) {
            return;
        }

        inOrder(root);
        inOrderReversed(root);
        if (first != null && second != null) {
            // swap the first and second value
            int temp = first.val;
            first.val = second.val;
            second.val = temp;
        }
    }
}
