package datastructure.BinaryTree;

import leetcode.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Build a binary tree from its post order and inorder
 */
public class ConstructFromPostAndInOrder {

    private Map<Integer, Integer> map = new HashMap<>();

    private TreeNode dfs(int[] inorder, int[] postorder, int index, int l, int r) {
        if (l > r) {
            return null;
        }

        if (index < 0) {
            return null;
        }

        int value = postorder[index];
        int rootPos = map.get(value);

        TreeNode node = new TreeNode(value);

        node.right = dfs(inorder, postorder, index - 1, rootPos + 1, r);
        node.left = dfs(inorder, postorder, index - (r - rootPos) - 1, l, rootPos - 1);

        return node;
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length != postorder.length)
            return null;

        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return dfs(inorder, postorder, postorder.length - 1, 0, inorder.length - 1);
    }
}
