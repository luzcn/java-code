package leetcode;

import java.util.*;

// Given the root of a tree, you are asked to find the most frequent subtree sum.
// The subtree sum of a node is defined as the sum of all the node values formed by the subtree rooted at that node (including the node itself).
//
// So what is the most frequent subtree sum value?
// If there is a tie, return all the values with the highest frequency in any order.
//
// Examples 1
// Input:
//
//   5
//  /  \
// 2   -3
// return [2, -3, 4], since all the values happen only once, return all of them in any order.
// Examples 2
// Input:
//
//   5
//  /  \
// 2   -5
// return [2], since 2 happens twice, however -5 only occur once.
// Note: You may assume the sum of values in any subtree is in the range of 32-bit signed integer.
public class MostFrequentSubtreeSum_508 {
    public int[] findFrequentTreeSum(TreeNode root) {

        dfs(root);

        ArrayList<Integer> res = new ArrayList<>();
        int maxFrequency = 0;

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {

            if (maxFrequency == entry.getValue()) {
                res.add(entry.getKey());
            } else if (entry.getValue() > maxFrequency) {
                res.clear();
                maxFrequency = entry.getValue();
                res.add(entry.getKey());
            }
        }

        return res.stream().mapToInt(x -> x).toArray();
    }

    private HashMap<Integer, Integer> map = new HashMap<>();


    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftSub = dfs(node.left);
        int rightSub = dfs(node.right);

        int subTreeSum = leftSub + rightSub + node.val;

        map.put(subTreeSum, map.getOrDefault(subTreeSum, 0) + 1);

        return subTreeSum;
    }
}
