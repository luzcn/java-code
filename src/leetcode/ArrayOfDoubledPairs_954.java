package leetcode;

import java.util.Arrays;
import java.util.HashMap;

// Given an array of integers A with even length,
// return true if and only if it is possible to reorder it such that A[2 * i + 1] = 2 * A[2 * i] for every 0 <= i < len(A) / 2.
//
//
//
//Example 1:
//
//Input: [3,1,3,6]
//Output: false
//Example 2:
//
//Input: [2,1,2,6]
//Output: false
//Example 3:
//
//Input: [4,-2,2,-4]
//Output: true
//Explanation: We can take two groups, [-2,-4] and [2,4] to form [-2,-4,2,4] or [2,4,-2,-4].
//Example 4:
//
//Input: [1,2,4,16,8,4]
//Output: false
public class ArrayOfDoubledPairs_954 {

  public boolean canReorderDoubled(int[] A) {

    // must be even
    int n = A.length;

    HashMap<Integer, Integer> map = new HashMap<>();

    for (int num : A) {
      map.put(num, map.getOrDefault(num, 0) + 1);
    }

    // sort is required here
    // because we are always comparing x and 2*x
    Arrays.sort(A);

    for (int num : A) {

      if (map.get(num) == null) {
        continue;
      }

      int target = 2 * num;
      if (map.containsKey(target)) {
        map.put(num, map.get(num) - 1);
        if (map.get(num) == 0) {
          map.remove(num);
        }

        map.put(target, map.getOrDefault(target, 0) - 1);
        if (map.get(target) <= 0) {
          map.remove(target);
        }
      }
    }

    return map.isEmpty();
  }
}
