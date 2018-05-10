package leetcode;

// Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?
//
// Example:
//
// Input: 3
// Output: 5
// Explanation:
// Given n = 3, there are a total of 5 unique BST's:
//
//    1         3     3      2      1
//     \       /     /      / \      \
//      3     2     1      1   3      2
//     /     /       \                 \
//    2     1         2                 3

import java.util.ArrayList;
import java.util.List;

public class UniqueBinarySearchTrees {

    // if n == 0, only one bst tree, empty tree
    // if n == 1, only one node tree.
    //
    // if n == 2, number of trees with "1" as root + number of trees with "2" as root.
    // s => numTree(0)*numTree(1) + numTree(1)*numTree(0).
    //
    // if n == 3, number of trees with "1" as root + number of trees with "2" as root + number of trees with "3" as root
    // s =>  numTree(0)*numTree(2) + numTree(1)*numTree(1) + numTree(2)*numTree(0)
    //
    // so, f(0) = f(1) = 1; f(n) = f(0)*f(n-1) + f(1)*f(n-2) + ... + f(i-1)*f(n-i) + ... + f(n-1)* f(0)
    // 当数组为 1,2,3,4...n时，基于以下原则的BST建树具有唯一性:
    // 以i为根节点的树: 1.其左子树由[0, i-1]构成 2.其右子树由[i+1, n]构成
    public int numTrees(int n) {

        if (n == 0 || n == 1) {
            // empty tree or 1 node tree
            return 1;
        }

        int num = 0;
        for (int i = 1; i <= n; i++) {
            num += numTrees(i - 1) * numTrees(n - i);
        }

        return num;
    }

    public int numTreesDP(int n) {
        // similar to fibonacci
        // use array to save the previous counting result
        int[] count = new int[n + 1];
        count[0] = 1;
        count[1] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                count[i] += count[j] * count[i - j - 1];
            }
        }

        return count[n];
    }

    // Unique Binary Search Tree II
    // Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.
    //
    // Example:
    //
    // Input: 3
    // Output:
    // [
    //   [1,null,3,2],
    //   [3,2,null,1],
    //   [3,1,null,null,2],
    //   [2,1,3],
    //   [1,null,2,null,3]
    // ]
    // Explanation:
    // The above output corresponds to the 5 unique BST's shown below:
    //
    //    1         3     3      2      1
    //     \       /     /      / \      \
    //      3     2     1      1   3      2
    //     /     /       \                 \
    //    2     1         2                 3
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }

        return dfs(1, n);
    }


    private List<TreeNode> dfs(int min, int max) {
        List<TreeNode> res = new ArrayList<>();

        if (min > max) {

            // need to add a null node here
            // otherwise, one root node will be missing.
            res.add(null);
            return res;
        }

        List<TreeNode> leftSub;
        List<TreeNode> rightSub;

        for (int i = min; i <= max; i++) {
            leftSub = dfs(min, i - 1);
            rightSub = dfs(i + 1, max);

            for (TreeNode leftNode : leftSub) {
                for (TreeNode rightNode : rightSub) {
                    // create a new node as the root of leftSub and rightSub, the value is "i"
                    TreeNode root = new TreeNode(i);
                    root.left = leftNode;
                    root.right = rightNode;

                    res.add(root);
                }
            }
        }
        return res;
    }
}
