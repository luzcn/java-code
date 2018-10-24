package datastructure.Recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Given a list of numbers, (may have duplicate)
// return all the permutations without duplicates
public class Permutations {

  private List<List<Integer>> res = new ArrayList<>();

  public List<List<Integer>> getPermutation(int[] nums) {
    if (nums.length == 0) {
      return res;
    }

    // sort the array first
    Arrays.sort(nums);
    boolean[] visited = new boolean[nums.length];

    dfs(nums, visited, new ArrayList<>());

    return this.res;
  }


  private void dfs(int[] nums, boolean[] visited, List<Integer> current) {
    if (current.size() == nums.length) {
      res.add(new ArrayList<>(current));
      return;
    }

    for (int i = 0; i < nums.length; i++) {

      if (visited[i]) {
        continue;
      }

      // remove duplicate
      if (i > 0 && nums[i - 1] == nums[i] && !visited[i - 1]) {
        continue;
      }

      current.add(nums[i]);
      visited[i] = true;

      dfs(nums, visited, current);

      current.remove(current.size() - 1);
      visited[i] = false;
    }
  }
}
