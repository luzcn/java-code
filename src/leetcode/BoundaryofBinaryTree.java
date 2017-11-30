package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given a binary tree, return the values of its boundary in anti-clockwise direction starting from root.
 * Boundary includes left boundary, leaves, and right boundary in order without duplicate nodes.
 * <p>
 * Left boundary is defined as the path from root to the left-most node.
 * <p>
 * Right boundary is defined as the path from root to the right-most node.
 * If the root doesn't have left subtree or right subtree, then the root itself is left boundary or right boundary.
 * <p>
 * Note this definition only applies to the input binary tree, and not applies to any subtrees.
 * <p>
 * The left-most node is defined as a leaf node you could reach when you always firstly travel to the left subtree if exists.
 * If not, travel to the right subtree. Repeat until you reach a leaf node.
 * <p>
 * The right-most node is also defined by the same way with left and right exchanged.
 */
public class BoundaryofBinaryTree {

    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        if (root == null) {
            return result;
        }
        result.add(root.val);

        // left boundary
        getLeftBoundary(root.left, result);

        // left subtree leaf nodes
        dfs(root.left, result);
        dfs(root.right, result);

        // right boundary
        getRightBoundary(root.right, result);

        return result;
    }

    private void getLeftBoundary(TreeNode node, List<Integer> result) {

        while (node != null) {
            if (!isLeaveNode(node))
                result.add(node.val);

            if (node.left != null) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
    }

    private void getRightBoundary(TreeNode node, List<Integer> result) {

        List<Integer> temp = new ArrayList<>();

        while (node != null) {
            if (!isLeaveNode(node))
                temp.add(node.val);

            if (node.right != null) {
                node = node.right;
            } else {
                node = node.left;
            }
        }

        Collections.reverse(temp);
        result.addAll(temp);
    }

    private void dfs(TreeNode node, List<Integer> result) {

        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            result.add(node.val);
        }

        dfs(node.left, result);
        dfs(node.right, result);
    }

    private boolean isLeaveNode(TreeNode n) {
        return n.left == null && n.right == null;
    }
}
