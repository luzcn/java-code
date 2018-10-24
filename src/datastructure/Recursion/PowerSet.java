package datastructure.Recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// given a list of numbers, get all the subset
// without duplicate
public class PowerSet {

  private List<List<Integer>> res = new ArrayList<>();

  public List<List<Integer>> subSet(int[] nums) {

    Arrays.sort(nums);
    dfs(nums, 0, new ArrayList<>());

    return this.res;
  }

  private void dfs(int[] nums, int index, List<Integer> current) {
    this.res.add(new ArrayList<>(current));

    if (index >= nums.length) {
      return;
    }

    for (int i = index; i < nums.length; i++) {
      current.add(nums[i]);

      dfs(nums, i + 1, current);

      current.remove(current.size() - 1);
    }
  }
}
