package leetcode;

// Given a Binary Search Tree (BST),
// convert it to a Greater Tree such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.
//
// Example:
//
// Input: The root of a Binary Search Tree like this:
//               5
//             /   \
//            2     13
//
// Output: The root of a Greater Tree like this:
//              18
//             /   \
//           20     13
public class ConvertBSTtoGreaterTree {

    int sum = 0;

    // reverse in-order traverse
    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }

        dfs(node.right);

        sum += node.val;
        node.val = sum;

        dfs(node.left);
    }

    public TreeNode convertBST(TreeNode root) {

        dfs(root);

        return root;
    }
}
