package leetcode;

/**
 * Given a binary tree and a sum,
 * determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
 */
//For example:
// Given the below binary tree and sum = 22,
//
//               5
//              / \
//             4   8
//            /   / \
//           11  13  4
//          /  \      \
//         7    2      1
// return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
public class PathSum {
    private boolean dfs(TreeNode node, int pathSum, int sum) {
        if (node == null) {
            return false;
        }

        // leaf node
        if (node.left == null && node.right == null) {
            return pathSum + node.val == sum;
        }

        return dfs(node.left, pathSum + node.val, sum) || dfs(node.right, pathSum + node.val, sum);

    }

    public boolean hasPathSum(TreeNode root, int sum) {
        return dfs(root, 0, sum);
    }
}
