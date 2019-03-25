package careercup.Minimax;

import java.util.*;

// a simple example of minimax algorithm
// find maximum score that maximizing player can get.
public class MiniMaxExample {

  // given a list of integer numbers,
  // two players, player1 try to get the highest score possible
  // player2 choose lowest score as possible
  // return the max score the player1 can get
  public int getMax(int[] nums) {

    return dfs(nums, true, 0, nums.length - 1);
  }


  // minimax is a backtracking problem
  private int dfs(int[] nums, boolean isMax, int l, int r) {
    if (l > r) {
      return 0;
    }

    if (l == r) {
      return nums[l];
    }

    int mid = l + (r - l) / 2;
    if (isMax) {

      return Math.max(dfs(nums, false, l, mid - 1), dfs(nums, false, mid + 1, r));
    }

    return Math.min(dfs(nums, true, l, mid - 1), dfs(nums, true, mid + 1, r));
  }
}
