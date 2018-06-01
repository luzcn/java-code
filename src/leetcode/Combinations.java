package leetcode;

import java.util.ArrayList;
import java.util.List;

// Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
//
// For example,
// If n = 4 and k = 2, a solution is:
// [
// [2,4],
// [3,4],
// [2,3],
// [1,2],
// [1,3],
// [1,4],
// ]

public class Combinations {


    private void dfs(List<Integer> nums, int k, List<List<Integer>> result, List<Integer> current, int index) {
        if (current.size() == k) {
            result.add(new ArrayList<>(current));
        }

        for (int i = index; i < nums.size(); i++) {
            current.add(nums.get(i));

            dfs(nums, k, result, current, i + 1);

            current.remove(current.size() - 1);
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        List<Integer> nums = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            nums.add(i);
        }

        dfs(nums, k, result, current, 0);
        return result;

    }
}
