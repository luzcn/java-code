package leetcode;

/**
 * Given a binary tree, you need to compute the length of the diameter of the tree.
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
 *
 * This path may or may not pass through the root.
 */
//Example:
//Given a binary tree
//          1
//         / \
//        2   3
//       / \
//      4   5
//Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
public class DiameterOfBinaryTree {

    private int diameter = 0;


    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftSub = dfs(root.left);
        int rightSub = dfs(root.right);

        this.diameter = Math.max(this.diameter, leftSub + rightSub + 1);
        return Math.max(leftSub, rightSub) + 1;
    }

    public int diameterOfBinaryTree(TreeNode root) {

        dfs(root);
        return this.diameter - 1;
    }

}
