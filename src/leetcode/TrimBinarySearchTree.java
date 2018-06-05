package leetcode;

import java.util.*;

// Given a binary search tree and the lowest and highest boundaries as L and R,
// trim the tree so that all its elements lies in [L, R] (R >= L).
//
// You might need to change the root of the tree,
// so the result should return the new root of the trimmed binary search tree.
// Example 1:
// Input:
//     1
//    / \
//   0   2
//
//   L = 1
//   R = 2
//
// Output:
//     1
//       \
//        2
public class TrimBinarySearchTree {

    private TreeNode dfs(TreeNode node, int L, int R) {
        if (node == null) {
            return null;
        }

        if (node.val >= L && node.val <= R) {
            node.left = dfs(node.left, L, R);
            node.right = dfs(node.right, L, R);
        } else if (node.val < L) {
            node = node.right;
            return dfs(node, L, R);
        } else {
            node = node.left;
            return dfs(node, L, R);
        }

        return node;
    }

    public TreeNode trimBST(TreeNode root, int L, int R) {

        return dfs(root, L, R);
    }

}
