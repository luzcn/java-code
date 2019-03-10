package leetcode;

import java.util.Arrays;

// Given a non-empty integer array of size n,
// find the minimum number of moves required to make all array elements equal,
// where a move is incrementing n - 1 elements by 1.
//
// Example:
//
// Input:
// [1,2,3]
//
// Output:
// 3
//
// Explanation:
// Only three moves are needed (remember each move increments two elements):
//
// [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
public class MinimumMovesToEqualArrayElements_453 {


  // use sort and counting
  // assume nums [1,2,3,3,3], the min is 1, max is 3, diff = max-min = 2
  // increment all n-1 elements by dff, we have [3,4,5,5,3]
  // we can observe that the previous second-largest is the largest now.
  // we again have diff = max-min = 2;
  // repeat the previous steps
  // we can observe that total steps are sum(diff) = sum of nums[i]-nums[0] where 1 <= i < n;
  public int minMoves(int[] nums) {

    Arrays.sort(nums);
    int steps = 0;

    for (int i = 1; i < nums.length; i++) {
      steps += nums[i] - nums[0];
    }

    return steps;
  }


  // brute force TLE
  public int minMovesBF(int[] nums) {

    int step = 0;

    while (!isSame(nums)) {
      Arrays.sort(nums);
      increment(nums);
      step++;
    }

    return step;
  }

  private void increment(int[] nums) {
    for (int i = 0; i < nums.length - 1; i++) {
      nums[i]++;
    }
  }

  private boolean isSame(int[] nums) {

    for (int i = 1; i < nums.length; i++) {
      if (nums[i] != nums[i - 1]) {
        return false;
      }
    }

    return true;
  }
}
