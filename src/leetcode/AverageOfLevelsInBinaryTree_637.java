package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.
 */
//Input:
//     3
//    / \
//   9  20
//     /  \
//    15   7
// Output: [3, 14.5, 11]
// Explanation:
// The average value of nodes on level 0 is 3,  on level 1 is 14.5, and on level 2 is 11.
// Hence return [3, 14.5, 11].
public class AverageOfLevelsInBinaryTree_637 {

    // similar to level-order
    // but save a double typed array of [sum, count]
    private List<double[]> res = new ArrayList<>();

    private void dfs(TreeNode node, int level) {
        if (node == null) {
            return;
        }

        if (res.size() == level) {
            res.add(new double[]{0.0, 0.0});
        }
        // sum of nodes on the same level
        res.get(level)[0] += node.val;

        // node count on same level
        res.get(level)[1]++;

        dfs(node.left, level + 1);
        dfs(node.right, level + 1);
    }

    public List<Double> averageOfLevels(TreeNode root) {
        dfs(root, 0);

        List<Double> averages = new ArrayList<>();

        res.forEach(x -> averages.add((x[0] / x[1])));

        return averages;
    }
}
