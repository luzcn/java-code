package datastructure.Recursion;

import java.util.*;

// Given two integers n and k, return all possible combinations of k numbers out of 1 ... n
public class Combinations {

    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        List<Integer> numsList = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            numsList.add(i);
        }

        dfs(numsList, 0, new ArrayList<>(), k);

        return res;
    }

    private void dfs(List<Integer> nums, int index, List<Integer> current, int k) {
        if (current.size() == k) {
            this.res.add(new ArrayList<>(current));
        }

        for (int i = index; i < nums.size(); i++) {
            current.add(nums.get(i));

            dfs(nums, i + 1, current, k);

            current.remove(current.size() - 1);
        }
    }
}
