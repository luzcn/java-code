package leetcode;

/////
// You are a professional robber planning to rob houses along a street.
// Each house has a certain amount of money stashed,
// the only constraint stopping you from robbing each of them is that adjacent houses have security system connected
// and it will automatically contact the police if two adjacent houses were broken into on the same night.
//
// Given a list of non-negative integers representing the amount of money of each house,
// determine the maximum amount of money you can rob tonight without alerting the police.
///
public class HouseRobber {

    // recursive with memoization
    private int dfs(int[] nums, int index, int[] memo) {
        if (index >= nums.length) {
            return 0;
        }

        if (memo[index] != -1) {
            return memo[index];
        }

        int value = Math.max(dfs(nums, index + 1, memo), dfs(nums, index + 2, memo) + nums[index]);
        memo[index] = value;

        return value;
    }

    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        // DP solution
        int n = nums.length;
        int[] values = new int[n + 1];
        values[0] = 0;
        values[1] = nums[1];

        for (int i = 2; i <= n; i++) {
            values[i] = Math.max(values[i - 2] + nums[i], values[i - 1]);
        }

        return values[n];
    }

    /////
    // Similar to house rob 1, now the input array is considered as a circle.
    //
    // Thoughts:
    // The only difference is that if rob the first house, then cannot rob the last house, and the opposite as well.
    // we can consider the input array as two arrays: array [0...n-2] and [1...n-1].
    // scan these two separately and get the max value
    ///
    public int rob2(int[] nums) {
        int n = nums.length;

        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return nums[0];
        }

        int[] values = new int[n + 1];

        // scan [0...n-2], include the first house
        values[1] = nums[0];
        for (int i = 2; i <= n; i++) {
            values[i] = Math.max(values[i - 2] + nums[i - 1], values[i - 1]);
        }
        // exclude the last house
        int maxValue1 = values[n - 1];

        // scan [1...n-1], exclude the first house
        values[1] = 0;
        for (int i = 2; i <= n; i++) {
            values[i] = Math.max(values[i - 2] + nums[i - 1], values[i - 1]);
        }
        int maxValue2 = values[n];

        return Math.max(maxValue1, maxValue2);
    }

    // 337. House Robber III
    // he smart thief realized that "all houses in this place forms a binary tree".
    // It will automatically contact the police if two directly-linked houses were broken into on the same night.
    public int rob3(TreeNode root) {
        // similarly, we use bottom-up recursive of the tree
        // the return value is an array r[],
        // - r[0] is the value that we do not rob the current node,
        // - r[1] is the value that rob the current node

        int[] res = dfs(root);

        return Math.max(res[0], res[1]);
    }

    private int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }

        if (node.left == null && node.right == null) {

            // the first is not rob,
            // the second is rob
            return new int[]{0, node.val};
        }

        int[] leftSub = dfs(node.left);
        int[] rightSub = dfs(node.right);

        int[] res = new int[2];
        // do not rob current node
        // can rob the children
        res[0] = Math.max(leftSub[0], leftSub[1]) + Math.min(rightSub[0], rightSub[1]);

        // rob the current node
        // can not rob the children
        res[1] = node.val + leftSub[0] + rightSub[0];

        return res;
    }
}
