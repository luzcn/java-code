package leetcode;

// Given a binary tree, count the number of uni-value subtrees.
//
// A Uni-value subtree means all nodes of the subtree have the same value.
//
// For example:
// Given binary tree,
//               5
//              / \
//             1   5
//            / \   \
//           5   5   5
// return 4.
public class CountUnivalueSubtrees {

    private int ans = 0;

    private boolean dfs(TreeNode node) {
        if (node == null) {
            return true;
        }

        // leave node is alwasy a uni-value subtree
        if (node.left == null && node.right == null) {
            ans++;
            return true;
        }

        boolean leftSub = dfs(node.left);
        boolean rightSub = dfs(node.right);

        if (leftSub && rightSub) {
            if (node.left != null && node.right != null) {
                if (node.val == node.left.val && node.val == node.right.val) {
                    ans++;
                    return true;
                }
            } else if (node.left != null) {
                // the right subtree is null
                // node.val == node.left.val, it is considered as a valid uni-value subtree
                if (node.val == node.left.val) {
                    ans++;
                    return true;
                }
            } else {
                if (node.val == node.right.val) {
                    ans++;
                    return true;
                }
            }
        }

        return false;
    }

    public int countUnivalSubtrees(TreeNode root) {

        dfs(root);

        return ans;
    }
}
