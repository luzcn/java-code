package careercup.BinaryTree;

import leetcode.TreeNode;

import java.util.Stack;

/**
 * Check if the given binary search tree is a valid BST
 */
public class ValidBST {

    private boolean validRec(TreeNode node, int l, int r) {
        if (node == null)
            return true;

        return node.val >= l && node.val < r
                && validRec(node.left, l, node.val)
                && validRec(node.right, node.val, r);
    }


    // use stack, iterative in-order traverse
    private boolean validIterative(TreeNode root) {
        if (root == null)
            return true;

        Stack<TreeNode> stack = new Stack<>();
        boolean done = false;
        TreeNode currentNode = root;
        TreeNode prev = null;

        while (!done) {
            if (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.left;
            } else if (!stack.isEmpty()) {
                currentNode = stack.pop();
                // print out the current node
                // or valid the node with "prev" which is the previous smallest element
                if (prev != null && prev.val > currentNode.val)
                    return false;

                prev = currentNode;
                currentNode = currentNode.right;

            } else
                done = true;
        }

        return true;
    }

    public boolean isValid(TreeNode root) {
        return this.validRec(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
}
