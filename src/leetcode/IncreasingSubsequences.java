package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

// Given an integer array, your task is to find all the different possible increasing subsequences of the given array,
// and the length of an increasing subsequence should be at least 2 .
//
// Example:
// Input: [4, 6, 7, 7]
// Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
// Note:
// The length of the given array will not exceed 15.
// The range of integer in the given array is [-100,100].
// The given array may contain duplicates, and two equal integers should also be considered as a special case of increasing sequence.
public class IncreasingSubsequences {

  // usually "find all" is using dfs
  // O(2^n) time
  private List<List<Integer>> res = new ArrayList<>();

  private void dfs(int[] nums, int index, List<Integer> current) {
    if (current.size() >= 2) {
      res.add(new ArrayList<>(current));
    }

    // use hashset to remove unnecessary duplicate sequence
    HashSet<Integer> used = new HashSet<>();
    for (int i = index; i < nums.length; i++) {

      // not increasing
      // or this number has duplicate and has been used.
      if (!current.isEmpty() && nums[i] < current.get(current.size() - 1) || used
          .contains(nums[i])) {
        continue;
      }

      current.add(nums[i]);
      used.add(nums[i]);

      dfs(nums, i + 1, current);

      current.remove(current.size() - 1);
    }
  }

  public List<List<Integer>> findSubsequences(int[] nums) {

    int n = nums.length;
    if (n == 0) {
      return new ArrayList<>();
    }

    // HashSet<Integer> used = new HashSet<>();

    dfs(nums, 0, new ArrayList<>());
    return res;
  }
}
