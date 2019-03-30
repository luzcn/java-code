package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all
// unique triplets in the array which gives the sum of zero.
//
// Note: The solution set must not contain duplicate triplets.
//
// For example, given array S = [-1, 0, 1, 2, -1, -4],
//
// A solution set is: [ [-1, 0, 1], [-1, -1, 2] ]
///
public class ThreeSum_15 {

  // sort + two pointer
  // don't forget skip repeated element
  public List<List<Integer>> threeSum(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();

    Arrays.sort(nums);
    int k = 0;

    for (int i = 0; i < nums.length - 2; i++) {
      // remove duplicate
      if (i > 0 && nums[i - 1] == nums[i]) {
        continue;
      }

      int t = k - nums[i];
      int l = i + 1;
      int r = nums.length - 1;

      while (l < r) {
        if (nums[l] + nums[r] == t) {
          result.add(Arrays.asList(nums[i], nums[l], nums[r]));
          l++;
          r--;

          // remove duplicates
          while (l < r && nums[l] == nums[l - 1]) {
            l++;
          }

          while (l < r && nums[r] == nums[r + 1]) {
            r--;
          }

        } else if (nums[l] + nums[r] > t) {
          r--;
        } else {
          l++;
        }
      }
    }

    return result;
  }

  // Given an array of n integers nums and a target,
  // find the number of index triplets i, j, k with 0 <= i < j < k < n
  // that satisfy the condition nums[i] + nums[j] + nums[k] < target.
  //
  // Example:
  //
  // Input: nums = [-2,0,1,3], and target = 2
  // Output: 2
  // Explanation: Because there are two triplets which sums are less than 2:
  //              [-2,0,1]
  //              [-2,0,3]
  public int ThreeSumSmaller(int[] nums, int target) {

    Arrays.sort(nums);

    int n = nums.length;
    int count = 0;

    for (int i = 0; i < n - 2; i++) {
      int left = i + 1;
      int right = n - 1;
      // int a = nums[i];

      while (left < right) {
        if (nums[i] + nums[left] + nums[right] < target) {

          // all the numbers from [left, right] can be included in the result triples
          count += right - left;
          left++;
        } else {
          right--;
        }
      }
    }

    return count;
  }
}
