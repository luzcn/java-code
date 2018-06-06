package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/////
// Given a set of candidate numbers (C) (without duplicates) and a target number (T),
// find all unique combinations in C where the candidate numbers sums to T.
//
// The same repeated number may be chosen from C unlimited number of times.
//
// Note:
// - All numbers (including target) will be positive integers.
// - The solution set must not contain duplicate combinations.
//
// For example, given candidate set [2, 3, 6, 7] and target 7,
// A solution set is:
// [
// [7],
// [2, 2, 3]
// ]
///
public class CombinationSum {

    private List<List<Integer>> result = new ArrayList<>();
    private List<Integer> current = new ArrayList<>();


    // recursive + backtracking
    private void dfs(int[] candidates, int target, int index, int sum) {

        if (index >= candidates.length) {
            return;
        }

        if (sum > target) {
            return;
        }

        if (sum == target) {
            this.result.add(new ArrayList<>(this.current));
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            this.current.add(candidates[i]);

            dfs(candidates, target, i, sum + candidates[i]);

            this.current.remove(this.current.size() - 1);
        }
    }


    // now the input candidates array may contain duplicates.
    // and each number can only be used once.
    private void dfs2(int[] candidates, int target, int index, int sum) {
        if (sum == target) {
            this.result.add(new ArrayList<>(this.current));
            return;
        }

        if (sum > target) {
            return;
        }

        for (int i = index; i < candidates.length; i++) {

            if (i > index && (candidates[i] == candidates[i - 1])) {
                continue;
            }

            this.current.add(candidates[i]);
            dfs2(candidates, target, i + 1, sum + candidates[i]);
            this.current.remove(this.current.size() - 1);
        }

    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        dfs(candidates, target, 0, 0);
        return this.result;

    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        dfs2(candidates, target, 0, 0);

        return this.result;
    }
}
