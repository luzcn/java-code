package leetcode;

import java.util.Stack;

//Given a binary tree, flatten it to a linked list in-place.
//
// For example,
// Given
//
//          1
//         / \
//        2   5
//       / \   \
//      3   4   6
// The flattened tree should look like:
//    1
//     \
//      2
//       \
//        3
//         \
//          4
//           \
//            5
//             \
//              6
public class FlattenBinaryTreeToLinkedList {

    private void preorder(TreeNode root) {
        // pre-order non recursive
        if (root == null)
            return;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode current = null;
        TreeNode prev = null;

        while (!stack.isEmpty()) {
            current = stack.pop();

            if (current.right != null) {
                stack.push(current.right);
            }

            if (current.left != null) {
                stack.push(current.left);
            }

            if (prev != null) {
                prev.left = null;
                prev.right = current;
            }

            prev = current;
            // current = current.left;
        }
    }


    /**
     * similar to morris tree
     * for each node "n",
     * 1. if n has left child,
     * 1.1 find the rightmost node "r" in its left sub tree
     * 1.2  make r.right = n.right;
     * 1.3  make n right child point to its left child i.e. n.right = n.left, n.left = null
     *
     * 2. move to right child. (either no left child, or left child has been moved to right position).
     */

    public void flatten(TreeNode root) {
        TreeNode current = root;

        while (current != null) {

            if (current.left != null) {
                // have left child, find the rightmost child in left sub tree
                TreeNode rightNode = current.left;
                while (rightNode.right != null) {
                    rightNode = rightNode.right;
                }

                // make the rightmost node right child point to current node right child
                rightNode.right = current.right;

                current.right = current.left;
                current.left = null;
            }

            // if no left child, go directly to right child
            // if has left child, the left child has been moved to its right
            current = current.right;
        }
    }
}
