package leetcode;

/**
 * Given a complete binary tree, count the number of nodes.
 *
 * Definition of a complete binary tree from Wikipedia:
 * In a complete binary tree every level, except possibly the last,
 * is completely filled, and all nodes in the last level are as far left as possible.
 *
 * It can have between 1 and 2^h nodes inclusive at the last level h.
 */
public class CountCompleteTreeNodes {
    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int depth = 0;
        TreeNode l = node;
        TreeNode r = node;

        // count the depth of current sub tree
        while (l != null && r != null) {
            depth++;
            l = l.left;
            r = r.right;
        }

        // if leftmost node and rightmost node are both null
        // this sub tree is a full tree,
        // return 2^depth - 1
        if (l == null && r == null) {
            return (1 << depth) - 1;    // return 2^h -1
        }

        // divide conquer, O(nlogn)
        return dfs(node.left) + dfs(node.right) + 1;
    }

    public int countNodes(TreeNode root) {

        return dfs(root);
    }
}
