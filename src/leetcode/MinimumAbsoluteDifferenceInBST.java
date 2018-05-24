package leetcode;

import java.util.*;

// Given a binary search tree with non-negative values, find the minimum absolute difference between values of any two nodes.
//
// Example:
//
// Input:
//
//    1
//     \
//      3
//     /
//    2
//
// Output:
// 1
//
// Explanation:
// The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).
public class MinimumAbsoluteDifferenceInBST {

    // BST in-order result is ordered array,
    // so the minimum absolute difference can only find between each consecutive pair
    public int getMinimumDifference(TreeNode root) {

        if (root == null) {
            return 0;
        }
        // inorder iterative
        TreeNode prev = null;
        TreeNode current = root;
        Stack<TreeNode> stack = new Stack<>();
        int res = Integer.MAX_VALUE;

        while(true) {
            if (current != null) {
                stack.push(current);

                current = current.left;
            } else if (!stack.isEmpty()) {
                current = stack.pop();

                if (prev != null) {
                    res = Math.min(res, current.val - prev.val);
                }

                prev = current;
                current = current.right;
            }else {
                break;
            }
        }

        return res;
    }

}
