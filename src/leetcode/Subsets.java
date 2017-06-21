package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Created by zhenlu on 10/10/16.


public class Subsets {
    public List<List<Integer>> getSubset(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();

        // sort the input
        Arrays.sort(nums);
        dfs(nums, result, new ArrayList<>(), 0);
        return result;
    }


    public List<List<Integer>> subsetWithDup(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        ArrayList<Boolean> visited = new ArrayList<>(nums.length);
        Arrays.sort(nums);


        dfsWithDup(nums, result, new ArrayList<>(), visited, 0);
        return result;

    }


    private void dfs(int[] nums, List<List<Integer>> result, ArrayList<Integer> current, int index) {
        result.add(new ArrayList<>(current));

        if (index >= nums.length) {
            return;
        }

        for (int i = index; i < nums.length; i++) {
            current.add(nums[i]);

            dfs(nums, result, current, i + 1);

            current.remove(current.size() - 1);
        }
    }

    private void dfsWithDup(int[] nums,
                            List<List<Integer>> result,
                            ArrayList<Integer> current,
                            ArrayList<Boolean> visited,
                            int index) {

        result.add(new ArrayList<>(current));

        if (index >= nums.length) {
            return;
        }


        for (int i = index; i < nums.length; i++) {

            if (!visited.get(i)) {
                if (i != index && nums[i - 1] == nums[i] && !visited.get(i - 1))
                    continue;

                visited.set(i, true);
                current.add(nums[i]);

                dfsWithDup(nums, result,current, visited, i + 1);

                current.remove(current.size()-1);
                visited.set(i, false);
            }
        }

    }
}
