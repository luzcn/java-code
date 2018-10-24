package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a collection of distinct numbers, return all possible permutations.
 * <p>
 * For example, [1,2,3] have the following permutations: [ [1,2,3], [1,3,2], [2,1,3], [2,3,1],
 * [3,1,2], [3,2,1] ]
 */
public class Permutations {

  private List<List<Integer>> result = new ArrayList<>();
  private List<Integer> current = new ArrayList<>();


  private void dfs(int[] nums, boolean[] visited) {

    if (current.size() == nums.length) {
      result.add(new ArrayList<>(current));
      return;
    }

    for (int i = 0; i < nums.length; i++) {

      if (!visited[i]) {

        if (i > 0 && nums[i - 1] == nums[i] && !visited[i - 1]) {
          continue;
        }

        current.add(nums[i]);
        visited[i] = true;

        dfs(nums, visited);

        current.remove(current.size() - 1);
        visited[i] = false;
      }
    }
  }

  public List<List<Integer>> permute(int[] nums) {

    boolean[] visited = new boolean[nums.length];

    dfs(nums, visited);
    return this.result;
  }
}
