package leetcode;

import java.util.*;

// Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.
//
// Note:
//
// Given target value is a floating point.
// You are guaranteed to have only one unique value in the BST that is closest to the target.
// Example:
//
// Input: root = [4,2,5,1,3], target = 3.714286
//
//     4
//    / \
//   2   5
//  / \
// 1   3
//
// Output: 4
public class ClosestBinarySearchTreeValue_270 {

    private int res;

    private void dfs(TreeNode node, double t) {
        if (node == null) {
            return;
        }

        if (Math.abs(t - (double) node.val) < Math.abs(t - (double) res)) {
            res = node.val;
        }

        if (t < (double) node.val) {
            dfs(node.left, t);
        } else {
            dfs(node.right, t);
        }
    }

    public int closestValue(TreeNode root, double target) {

        if (root == null) {
            return 0;
        }

        res = root.val;

        // binary search
        while (root != null) {
            if (Math.abs((double) root.val - target) < Math.abs((double) res - target)) {
                res = root.val;
            }

            if ((double) root.val < target) {
                root = root.right;
            } else {
                root = root.left;
            }
        }

        return res;
    }


}
