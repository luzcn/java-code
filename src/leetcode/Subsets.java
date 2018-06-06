package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Created by zhenlu on 10/10/16.


public class Subsets {

    private List<List<Integer>> result = new ArrayList<>();
    private List<Integer> current = new ArrayList<>();

    public List<List<Integer>> getSubset(int[] nums) {
        // sort the input
        Arrays.sort(nums);
        dfs(nums, 0);

        return this.result;
    }


    public List<List<Integer>> subsetWithDup(int[] nums) {
        Arrays.sort(nums);
        dfsWithDup(nums, 0);

        return result;
    }


    private void dfs(int[] nums, int index) {
        this.result.add(new ArrayList<>(this.current));

        if (index >= nums.length) {
            return;
        }

        for (int i = index; i < nums.length; i++) {
            current.add(nums[i]);

            dfs(nums, i + 1);

            current.remove(current.size() - 1);
        }
    }

    private void dfsWithDup(int[] nums, int index) {

        result.add(new ArrayList<>(current));

        if (index >= nums.length) {
            return;
        }

        for (int i = index; i < nums.length; i++) {

            if (i > index && nums[i - 1] == nums[i]) {
                continue;
            }

            current.add(nums[i]);

            dfsWithDup(nums, i + 1);

            current.remove(current.size() - 1);
        }
    }
}
