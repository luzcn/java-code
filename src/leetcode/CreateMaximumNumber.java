package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given two arrays of length m and n with digits 0-9 representing two numbers. Create the maximum
 * number of length k <= m + n from digits of the two.
 *
 * The relative order of the digits from the same array must be preserved. Return an array of the k
 * digits. You should try to optimize your time and space complexity.
 *
 * Example 1: nums1 = [3, 4, 6, 5] nums2 = [9, 1, 2, 5, 8, 3] k = 5 return [9, 8, 6, 5, 3]
 *
 * Example 2: nums1 = [6, 7] nums2 = [6, 0, 4] k = 5 return [6, 7, 6, 0, 4]
 *
 * Example 3: nums1 = [3, 9] nums2 = [8, 9] k = 3 return [9, 8, 9]
 */
public class CreateMaximumNumber {

  // create a maximum number from a single array
  private List<Integer> createNumber(int[] nums, int k) {
    // 1. use stack to store the digits
    // 2. for each element in the array:
    // - keep pop the top element from the stack, if the stack is not empty, the top is less than nums[i],
    //    and there are enough digits left in the array (stack.size() - 1 + nums.length - i >= k).
    // - push the nums[i] into the stack, if the stack size is less than k
    List<Integer> result = new ArrayList<>();
    if (nums.length < k) {
      return result;
    }

    Stack<Integer> stack = new Stack<>();
    for (int i = 0; i < nums.length; i++) {
      while (!stack.isEmpty() && stack.peek() < nums[i]
          && (stack.size() - 1 + nums.length - i) >= k) {
        stack.pop();
      }

      if (stack.size() < k) {
        stack.push(nums[i]);
      }
    }

    result.addAll(stack);

    return result;
  }

  // True if nums1 >= nums2
  private boolean greater(List<Integer> num1, int i, List<Integer> num2, int j) {
    while (i < num1.size() && j < num2.size() && num1.get(i).equals(num2.get(j))) {
      i++;
      j++;
    }

    return j == num2.size() || (i < num1.size() && num1.get(i) > num2.get(j));

  }

  // merge these two number list, the total length of these two list should be k
  // similar to merge two sorted list,
  // but the num1[i] == num2[j], we need to check the following digits to find the greater number
  // and put the greater number digit into the result array.
  private List<Integer> merge(List<Integer> num1, List<Integer> num2) {
    List<Integer> result = new ArrayList<>();

    int i = 0, j = 0;

    while (i + j < num1.size() + num2.size()) {

      if (greater(num1, i, num2, j)) {
        result.add(num1.get(i++));
      } else {
        result.add(num2.get(j++));
      }
    }

    return result;

  }

  public int[] maxNumber(int[] nums1, int[] nums2, int k) {

    List<Integer> result = new ArrayList<>();
    int m = nums1.length;
    int n = nums2.length;

    for (int i = Math.max(0, k - n); i <= Math.min(k, m); i++) {
      List<Integer> num1 = createNumber(nums1, i);
      List<Integer> num2 = createNumber(nums2, k - i);

      // merge these two numbers
      List<Integer> candidate = merge(num1, num2);

      if (greater(candidate, 0, result, 0)) {
        result = candidate;
      }
    }

    return result.stream().mapToInt(x -> x).toArray();
  }

}
