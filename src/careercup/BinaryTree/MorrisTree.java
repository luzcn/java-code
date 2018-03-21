package careercup.BinaryTree;

import leetcode.TreeNode;

/**
 * Morris Tree Traversal
 * O(n) time, each edge is traversed 2 times
 *
 * O(1) space
 *
 * not recursive, no stack
 *
 * Thought:
 * 1. Initialize current as root
 * 2. While current is not NULL
 *
 * 3. if current node has no left child
 * - print out the node value
 * - go to right child
 *
 * 4. else,
 * - find the rightmost node in current's left subtree save as "prev":
 *
 * 4.1 if prev.right == null,
 * -- make "current" as the right child of the "prev" i.e. prev.right = current
 * -- move current to its left child: current = current.left
 * 4.2 else if prev.right == current
 * -- print out the current node value, since all the left sub tree nodes have been traversed.
 * -- rebuild the prev.right = null
 * -- move current to its right child: current = current.right;
 */
public class MorrisTree {
    private void morrisTreeTraversal(TreeNode root) {
        if (root == null)
            return;

        TreeNode current = root;
        TreeNode prev = null;

        while (current != null) {
            if (current.left == null) {
                // no left children, print out this current value
                System.out.println(current.val);
                current = current.right;
            } else {
                // find the right most node in left children subtree
                prev = current.left;
                while (prev.right != null && prev.right != current) {
                    prev = prev.right;
                }

                if (prev.right == null) {
                    // make the current as the right child of prev
                    prev.right = current;

                    // move current to its left child
                    current = current.left;
                } else {

                    // rebuild the tree
                    prev.right = null;
                    System.out.println(current.val);

                    // move current to its right child
                    current = current.right;
                }
            }
        }
    }
}
