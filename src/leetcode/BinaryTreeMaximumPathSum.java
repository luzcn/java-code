package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

//Given a non-empty binary tree, find the maximum path sum.
//
// For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.
//
// Example 1:
//
// Input: [1,2,3]
//
//        1
//       / \
//      2   3
//
// Output: 6
// Example 2:
//
// Input: [-10,9,20,null,null,15,7]
//
//    -10
//    / \
//   9  20
//     /  \
//    15   7
//
// Output: 42
public class BinaryTreeMaximumPathSum {

    private int ans;

    // for each node, we can see the  path  sum could be
    // node.val, node.val + max(left, right), or left + right + node
    public int maxPathSum(TreeNode root) {

        ans = Integer.MIN_VALUE;

        dfs(root);
        return ans;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftSub = dfs(node.left);
        int rightSub = dfs(node.right);

        // int maxPathThroughCurrent = Math
        //         .max(Math.max(node.val, Math.max(leftSub, rightSub) + node.val), node.val + leftSub + rightSub);

        int currentMaxPath = Stream.of(node.val, leftSub + node.val, rightSub + node.val, leftSub + rightSub + node.val)
                .max(Comparator.comparingInt(x -> x)).get();

        int toReturn = Math.max(node.val, Math.max(leftSub, rightSub) + node.val);

        ans = Math.max(ans, currentMaxPath);

        return toReturn;
    }


    private int dfs2(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftSub = dfs2(node.left);
        int rightSub = dfs2(node.right);

        int maxPathThroughCurrent = 0;
        int toReturn = 0;

        // the max sum value could be node.val, node.val + leftSub or node.val + rightSub
        if (leftSub <= 0 && rightSub <= 0) {

            // if the max sum value from left and right sub trees are both negative
            // adding any of these sum values are useless
            maxPathThroughCurrent = node.val;

            // we should return the current node value;
            toReturn = node.val;
        } else if (leftSub <= 0) {
            // take the rightSub + current Node as the path
            maxPathThroughCurrent = node.val + rightSub;
            toReturn = node.val + rightSub;
        } else if (rightSub <= 0) {

            // take the leftSub + current node as the path
            maxPathThroughCurrent = node.val + leftSub;
            toReturn = node.val + leftSub;
        } else {
            // both leftSub, rightSub are positive
            // the max path sum could be left+current+right
            // but we can only return one path, either left+current or current + right

            maxPathThroughCurrent = leftSub + node.val + rightSub;
            toReturn = node.val + Math.max(leftSub, rightSub);
        }

        ans = Math.max(ans, maxPathThroughCurrent);
        return toReturn;
    }

}
