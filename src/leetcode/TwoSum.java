package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific
 * target.
 *
 * You may assume that each input would have exactly one solution, and you may not use the same
 * element twice.
 */
public class TwoSum {

  public int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    int[] result = new int[2];

    for (int i = 0; i < nums.length; i++) {
      if (map.containsKey(target - nums[i])) {
        // do not need to check if map.get == i,
        // because i is not in the map yet.
        result[0] = map.get(target - nums[i]);
        result[1] = i;
      } else {
        map.put(nums[i], i);
      }
    }

    return result;
  }

  /**
   * Given an array of integers that is already sorted in ascending order, find two numbers such
   * that they add up to a specific target number.
   *
   * The function twoSum should return indices of the two numbers such that they add up to the
   * target, where index1 must be less than index2. Please note that your returned answers (both
   * index1 and index2) are not zero-based.
   *
   * You may assume that each input would have exactly one solution and you may not use the same
   * element twice.
   *
   * Input: numbers={2, 7, 11, 15}, target=9 Output: index1=1, index2=2
   */
  public int[] twoSumInSortedArray(int[] nums, int target) {
    int[] result = new int[2];

    if (nums.length == 0) {
      return result;
    }

    int begin = 0;
    int end = nums.length - 1;

    while (begin < end) {
      if (target == nums[begin] + nums[end]) {
        result[0] = begin + 1;
        result[1] = end + 1;
        break;
      } else if (target > nums[begin] + nums[end]) {
        begin++;
      } else {
        end--;
      }
    }

    return result;
  }

  // 4sum problem
  // Given an array nums of n integers and an integer target,
  // are there elements a, b, c, and d in nums such that a + b + c + d = target?
  // Find all unique quadruplets in the array which gives the sum of target.
  // O(n^3) time complexity
  public List<List<Integer>> fourSum(int[] nums, int target) {
    // sort
    Arrays.sort(nums);
    List<List<Integer>> res = new ArrayList<>();

    for (int i = 0; i < nums.length - 3; i++) {
      if (i > 0) {
        while (i < nums.length - 3 && nums[i - 1] == nums[i]) {
          i++;
        }
      }

      for (int j = i + 1; j < nums.length - 2; j++) {

        if (j > i + 1) {
          while (j < nums.length - 2 && nums[j - 1] == nums[j]) {
            j++;
          }
        }
        // now two sum problem
        int begin = j + 1, end = nums.length - 1;
        while (begin < end) {
          int sum = nums[i] + nums[j] + nums[begin] + nums[end];
          if (sum == target) {
            res.add(Arrays.asList(nums[i], nums[j], nums[begin], nums[end]));

            // skip the duplicates
            begin++;
            while (begin < end && nums[begin] == nums[begin - 1]) {
              begin++;
            }

            end--;
            while (begin < end && nums[end] == nums[end + 1]) {
              end--;
            }
          } else if (sum < target) {
            begin++;
          } else {
            end--;
          }
        }
      }
    }

    return res;
  }

  // 4sum II
  // Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l)
  // there are such that A[i] + B[j] + C[k] + D[l] is zero.
  //
  // To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤ N ≤ 500.
  // All integers are in the range of -228 to 228 - 1 and the result is guaranteed to be at most 231 - 1.
  //
  // Example:
  //
  // Input:
  // A = [ 1, 2]
  // B = [-2,-1]
  // C = [-1, 2]
  // D = [ 0, 2]
  //
  // Output:
  // 2
  //
  // Explanation:
  // The two tuples are:
  // 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
  // 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
  public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
    int count = 0;

    // use a hashmap save the sum of each element of C and D
    HashMap<Integer, Integer> map = new HashMap<>();
    for (int c : C) {
      for (int d : D) {
        int sum = c + d;

        map.put(sum, map.getOrDefault(sum, 0) + 1);
      }
    }

    // sort A, B, to skip duplicate
    for (int i = 0; i < A.length; i++) {
      // skip duplicate numbers in A
      while (i > 0 && i < A.length && A[i - 1] == A[i]) {
        i++;
      }

      for (int j = 0; j < B.length; j++) {
        // skip duplicates in B
        while (j > 0 && j < B.length && B[j - 1] == B[j]) {
          j++;
        }

        if (map.containsKey(0 - A[i] - B[j])) {
          count += map.get(0 - A[i] - B[j]);
        }
      }
    }

    // for (int i = 0; i < A.length; i++) {
    //     for (int j = 0; j < B.length; j++) {
    //         for (int k = 0; k < C.length; k++) {
    //             for (int l = 0; l < D.length; l++) {
    //                 if (A[i] + B[j] + C[k] + D[l] == 0) {
    //                     count++;
    //
    //                     System.out.println(A[i] + " " + B[j] + " " + C[k] + " " + D[l]);
    //                 }
    //             }
    //         }
    //     }
    // }

    return count;
  }
}
