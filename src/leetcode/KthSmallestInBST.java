package leetcode;

import java.util.Stack;

// Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
//
// Note:
// You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
//
// Example 1:
//
// Input: root = [3,1,4,null,2], k = 1
// Output: 1
// Example 2:
//
// Input: root = [5,3,6,2,4,null,null,1], k = 3
// Output: 3
public class KthSmallestInBST {

    // in-order iterative solution
    public int kthSmallest(TreeNode root, int k) {
        if (root == null) {
            return 0;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        int count = 0;

        while (true) {
            if (current != null) {
                stack.push(current);
                current = current.left;
            } else if (!stack.isEmpty()) {
                current = stack.pop();

                if (++count == k) {
                    return current.val;
                }

                current = current.right;
            } else {
                break;
            }
        }

        return 0;
    }


    // dfs solution
    // use 3 member fields
    private int res = 0;
    // the in-order traversal visited node numbers
    private int count = 0;
    private boolean isFound = false;

    private void dfs(TreeNode node, int k) {
        if (node == null || isFound) {
            return;
        }

        dfs(node.left, k);

        if (++count == k) {
            res = node.val;
            isFound = true;
        }

        dfs(node.right, k);

    }
}
