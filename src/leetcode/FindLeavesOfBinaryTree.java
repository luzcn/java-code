package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a binary tree, collect a tree's nodes as if you were doing this:
 * Collect and remove all leaves,
 *
 * repeat until the tree is empty.
 */
//Example:
// Given binary tree
//           1
//          / \
//         2   3
//        / \
//       4   5
// Returns [4, 5, 3], [2], [1].
public class FindLeavesOfBinaryTree {

    private List<List<Integer>> result = new ArrayList<>();
    private List<Integer> current = new ArrayList<>();

    private boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }


    private void dfs(TreeNode node, TreeNode parent) {
        if (node == null) {
            return;
        }

        if (isLeaf(node)) {
            if (parent != null) {
                if (node == parent.left)
                    parent.left = null;
                else
                    parent.right = null;
            }
            current.add(node.val);
            return;
        }

        dfs(node.left, node);
        dfs(node.right, node);
    }

    public List<List<Integer>> findLeaves(TreeNode root) {

        if (root == null) {
            return this.result;
        }

        while (!isLeaf(root)) {
            dfs(root, null);
            result.add(new ArrayList<>(current));
            current.clear();
        }

        result.add(Arrays.asList(root.val));

        result.forEach(System.out::println);

        return this.result;
    }
}
