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
public class AverageOfLevelsInBinaryTree {

    private List<Double> result = new ArrayList<>();
    private List<Double> sumInLevel = new ArrayList<>();
    private List<Double> countInLevel = new ArrayList<>();


    private void dfs(TreeNode node, int level) {
        if (node == null)
            return;

        if (level >= result.size()) {
            sumInLevel.add((double) node.val);
            countInLevel.add(1.0);
            result.add((double) node.val);
        } else {
            sumInLevel.set(level, sumInLevel.get(level) + node.val);
            countInLevel.set(level, countInLevel.get(level) + 1);
            result.set(level, sumInLevel.get(level) / countInLevel.get(level));
        }

        dfs(node.left, level + 1);
        dfs(node.right, level + 1);
    }

    public List<Double> averageOfLevels(TreeNode root) {

        dfs(root, 0);
        return this.result;
    }
}
