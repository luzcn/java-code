package leetcode;

// Given a binary tree, find its minimum depth.
//
// The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
//
// Note: A leaf is a node with no children.
//
// Example:
//
// Given binary tree [3,9,20,null,null,15,7],
//
//     3
//    / \
//   9  20
//     /  \
//    15   7
// return its minimum depth = 2.
public class MinimumDepthOfBinaryTree {

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // bottom-up recursive
        int leftSub = minDepth(root.left);
        int rightSub = minDepth(root.right);

        // if it is leaf node, return 1
        // if it has one child is null, return the not-null child depth + 1
        if (root.left == null) {
            return rightSub + 1;
        }

        if (root.right == null) {
            return leftSub + 1;
        }

        // when code reaches here, the node should have both children
        return Math.min(leftSub, rightSub) + 1;
    }
}
