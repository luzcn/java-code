package leetcode;

import java.util.*;

// Given a binary tree, you need to find the length of Longest Consecutive Path in Binary Tree.
//
// Especially, this path can be either increasing or decreasing.
// For example, [1,2,3,4] and [4,3,2,1] are both considered valid, but the path [1,2,4,3] is not valid.
//
// On the other hand, the path can be in the child-Parent-child order, where not necessarily be parent-child order.
//
// Example 1:
// Input:
//         1
//        / \
//       2   3
// Output: 2
// Explanation: The longest consecutive path is [1, 2] or [2, 1].
// Example 2:
// Input:
//         2
//        / \
//       1   3
// Output: 3
// Explanation: The longest consecutive path is [1, 2, 3] or [3, 2, 1].
public class BinaryTreeLongestConsecutiveSequence_549 {

    private int res = 0;

    // x[0] is the count of increasing
    // x[1] is the count of decreasing
    private int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }

        int[] leftSub = dfs(node.left);
        int[] rightSub = dfs(node.right);

        if (node.left != null) {
            if (node.left.val + 1 != node.val) {
                leftSub[0] = 0;
            }

            if (node.left.val - 1 != node.val) {
                leftSub[1] = 0;
            }
        }

        if (node.right != null) {
            if (node.right.val + 1 != node.val) {
                rightSub[0] = 0;
            }

            if (node.right.val - 1 != node.val) {
                rightSub[1] = 0;
            }
        }

        int value = 0;
        if (node.left != null && node.right != null) {
            if (node.left.val + 1 == node.val && node.val + 1 == node.right.val) {
                value = leftSub[0] + rightSub[1] + 1;
            } else if (node.left.val - 1 == node.val && node.val - 1 == node.right.val) {
                value = leftSub[1] + rightSub[0] + 1;
            }
        }

        int maxLeft = Math.max(leftSub[0], leftSub[1]) + 1;
        int maxRight = Math.max(rightSub[0], rightSub[1]) + 1;

        res = Math.max(res, Math.max(value, Math.max(maxLeft, maxRight)));

        return new int[]{maxLeft, maxRight};
    }

    public int longestConsecutive(TreeNode root) {
        dfs(root);

        return res;
    }
}
