package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// Find all possible combinations of k numbers that add up to a number n,
// given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
//
//Note:
//
// - All numbers will be positive integers.
// - The solution set must not contain duplicate combinations.
//
// Example 1:
//
//Input: k = 3, n = 7
//Output: [[1,2,4]]
//
//Example 2:
//
//Input: k = 3, n = 9
//Output: [[1,2,6], [1,3,5], [2,3,4]]
public class CombinationSum3_216 {

  public List<List<Integer>> combinationSum3(int k, int n) {

    int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};

    dfs(nums, 0, n, k, new LinkedList<>());

    return res;
  }

  private List<List<Integer>> res = new ArrayList<>();

  private void dfs(int[] nums, int index, int n, int k, LinkedList<Integer> current) {

    if (current.size() == k) {
      if (current.stream().mapToInt(x -> x).sum() == n) {
        res.add(new ArrayList<>(current));
      }
    }

    for (int i = index; i < nums.length; i++) {
      current.addLast(nums[i]);

      dfs(nums, i + 1, n, k, current);

      current.removeLast();
    }
  }
}
