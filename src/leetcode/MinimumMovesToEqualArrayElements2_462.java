package leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;

// Given a non-empty integer array,
// find the minimum number of moves required to make all array elements equal,
//
// where a move is incrementing a selected element by 1 or decrementing a selected element by 1.
//
// You may assume the array's length is at most 10,000.
//
// Example:
//
// Input:
// [1,2,3]
//
// Output:
// 2
//
// Explanation:
// Only two moves are needed (remember each move increments or decrements one element):
//
// [1,2,3]  =>  [2,2,3]  =>  [2,2,2]
public class MinimumMovesToEqualArrayElements2_462 {


  public int minMoves(int[] nums) {

    // sort , then use median

    Arrays.sort(nums);

    int median = nums[nums.length / 2];

    int steps = 0;

    for (int n : nums) {
      steps += Math.abs(median - n);
    }

    return steps;
  }

  // bfs ?
  // TLE
  public int minMoves2(int[] nums) {

    HashMap<int[], Integer> map = new HashMap<>();

    Deque<int[]> queue = new ArrayDeque<>();

    queue.add(nums);
    map.put(nums, 0);

    while (!queue.isEmpty()) {
      int[] current = queue.getFirst();
      queue.removeFirst();

      Arrays.stream(current).forEach(x -> System.out.print(x + " "));
      System.out.println();

      if (isSame(current)) {
        return map.get(current);
      }

      for (int i = 0; i < nums.length; i++) {
        int[] next = current.clone();
        next[i]++;
        if (map.get(next) == null) {
          queue.addLast(next);
          map.put(next, map.get(current) + 1);
        }

        int[] next2 = current.clone();
        next2[i]--;

        if (map.get(next2) == null) {
          queue.add(next2);
          map.put(next2, map.get(current) + 1);
        }
      }
    }
    return 0;
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
