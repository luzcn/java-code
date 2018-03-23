package datastructure.BinaryTree;

import leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class LevelOrder {

    private List<List<Integer>> result = new ArrayList<>();

    // dfs recursive top down
    private void dfs(TreeNode node, int level) {
        if (node == null) {
            return;
        }

        if (this.result.size() == level) {
            this.result.add(new ArrayList<>());
        }

        result.get(level).add(node.val);

        dfs(node.left, level + 1);
        dfs(node.right, level + 1);
    }


    public List<List<Integer>> levelOrder(TreeNode root) {

        dfs(root, 0);

        return this.result;
    }


    /**
     * Given a binary tree, return the bottom-up level order traversal of its nodes' values.
     * (ie, from left to right, level by level from leaf to root).
     */
    //  For example:
    //  Given binary tree [3,9,20,null,null,15,7],
    //       3
    //      / \
    //     9  20
    //   /  \
    //  15   7
    // return its bottom-up level order traversal as:
    //  [
    //    [15,7],
    //    [9,20],
    //   [3]
    //  ]
    private void dfsBottomUp(TreeNode node, int level) {
        if (node == null) {
            return;
        }

        if (level >= this.result.size()) {
            // need bottom up order, so always put new list on top
            this.result.add(0, new ArrayList<>());
        }

        dfsBottomUp(node.left, level + 1);
        dfsBottomUp(node.right, level + 1);

        this.result.get(this.result.size() - 1 - level).add(node.val);
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root, int level) {
        dfsBottomUp(root, 0);
        return this.result;
    }


    /**
     * Binary Tree ZigZag level order
     * Given a binary tree, return the zigzag level order traversal of its nodes' values.
     * (ie, from left to right, then right to left for the next level and alternate between).
     *
     * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/description/
     */
    // For example:
    // Given binary tree [3,9,20,null,null,15,7],
    //     3
    //    / \
    //   9  20
    //     /  \
    //    15   7
    // return its zigzag level order traversal as:
    // [
    //   [3],
    //   [20,9],
    //   [15,7]
    // ]
    private void dfsZigZag(TreeNode node, int level) {
        if (node == null)
            return;

        if (level >= this.result.size()) {
            this.result.add(new ArrayList<>());
        }

        if (level % 2 == 0) {
            this.result.get(level).add(node.val);
        } else {
            this.result.get(level).add(0, node.val);
        }

        dfsZigZag(node.left, level + 1);
        dfsZigZag(node.right, level + 1);
    }

}
