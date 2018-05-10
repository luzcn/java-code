package leetcode;

import java.util.HashMap;

// Given a binary tree, write a function to get the maximum width of the given tree.
// The width of a tree is the maximum width among all levels.
//
// The binary tree has the same structure as a full binary tree, but some nodes are null.
//
// The width of one level is defined as the length between the end-nodes
// (the leftmost and right most non-null nodes in the level,
// where the null nodes between the end-nodes are also counted into the length calculation.
//
// Example 1:
// Input:
//
//            1
//          /   \
//         3     2
//        / \     \
//       5   3     9
//
// Output: 4
// Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
// Example 2:
// Input:
//
//           1
//          /
//         3
//        / \
//       5   3
//
// Output: 2
// Explanation: The maximum width existing in the third level with the length 2 (5,3).
// Example 3:
// Input:
//
//           1
//          / \
//         3   2
//        /
//       5
//
// Output: 2
// Explanation: The maximum width existing in the second level with the length 2 (3,2).
// Example 4:
// Input:
//
//           1
//          / \
//         3   2
//        /     \
//       5       9
//      /         \
//     6           7
// Output: 8
// Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).
public class MaximumWidthOfBinaryTree {
    // similar to level order, assign each node a position, if goes left child position*2, right child position*2 + 1
    // save these two key values in a hashmap with level as the map key.

    private HashMap<Integer, Integer> map = new HashMap<>();
    private int ans = 0;

    private void dfs(TreeNode node, int position, int level) {

        if (node == null) {
            return;
        }

        map.putIfAbsent(level, position);
        ans = Math.max(ans, position - map.get(position) + 1);

        dfs(node.left, position * 2, level + 1);
        dfs(node.right, position * 2 + 1, level + 1);
    }

    public int widthOfBinaryTree(TreeNode root) {

        dfs(root, 0, 0);
        return this.ans;
    }
}
