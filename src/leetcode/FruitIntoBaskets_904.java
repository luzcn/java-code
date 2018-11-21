package leetcode;

import java.util.HashMap;
import java.util.Map;

// In a row of trees, the i-th tree produces fruit with type tree[i].
//
// You start at any tree of your choice, then repeatedly perform the following steps:
//
// Add one piece of fruit from this tree to your baskets.
//  - If you cannot, stop.
// Move to the next tree to the right of the current tree.
//  - If there is no tree to the right, stop.
// Note that you do not have any choice after the initial choice of starting tree:
// you must perform step 1, then step 2, then back to step 1, then step 2, and so on until you stop.
//
// You have two baskets, and each basket can carry any quantity of fruit, but you want each basket to only carry one type of fruit each.
//
// What is the total amount of fruit you can collect with this procedure?
//
//
//
// Example 1:
//
// Input: [1,2,1]
// Output: 3
// Explanation: We can collect [1,2,1].
// Example 2:
//
// Input: [0,1,2,2]
// Output: 3
// Explanation: We can collect [1,2,2].
// If we started at the first tree, we would only collect [0, 1].
// Example 3:
//
// Input: [1,2,3,2,2]
// Output: 4
// Explanation: We can collect [2,3,2,2].
// If we started at the first tree, we would only collect [1, 2].
// Example 4:
//
// Input: [3,3,3,1,2,1,1,2,3,3,4]
// Output: 5
// Explanation: We can collect [1,2,1,1,2].
// If we started at the first tree or the eighth tree, we would only collect 4 fruits.
public class FruitIntoBaskets_904 {

  public int totalFruit(int[] tree) {

    // find the max length of subarray which has at most 2 distinct numbers

    return findMaxLengthSubarray(tree);
  }

  private int findMaxLengthSubarray(int[] nums) {
    Map<Integer, Integer> map = new HashMap<>();

    int maxLength = 0;
    int begin = 0;
    int end = 0;
    int count = 0;

    while (end < nums.length) {

      int c = nums[end];

      if (map.get(c) == null || map.get(c) == 0) {
        count++;
        map.put(c, 1);
      } else {
        map.put(c, map.get(c) + 1);
      }

      while (count > 2) {

        int first = nums[begin];
        map.put(first, map.get(first) - 1);
        if (map.get(first) == 0) {
          count--;
        }
        begin++;
      }

      maxLength = Math.max(maxLength, end - begin + 1);

      end++;
    }

    return maxLength;

  }
}
