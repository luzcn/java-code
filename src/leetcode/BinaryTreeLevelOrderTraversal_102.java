package leetcode;

import java.util.*;

// Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
//
// For example:
// Given binary tree [3,9,20,null,null,15,7],
//     3
//    / \
//   9  20
//     /  \
//    15   7
// return its level order traversal as:
// [
//   [3],
//   [9,20],
//   [15,7]
// ]
public class BinaryTreeLevelOrderTraversal_102 {

    private List<List<Integer>> res = new ArrayList<>();

    // dfs or bfs both work
    private void dfs(TreeNode node, int level) {
        if (node == null) {
            return;
        }

        if (res.size() == level) {
            res.add(new ArrayList<>());
        }
        res.get(level).add(node.val);

        dfs(node.left, level + 1);
        dfs(node.right, level + 1);
    }

    public List<List<Integer>> levelOrder(TreeNode root) {

        dfs(root, 0);

        return res;
    }
}
