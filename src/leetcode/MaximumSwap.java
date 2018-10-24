package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a non-negative integer, you could swap two digits at most once to get the maximum valued
 * number. Return the maximum valued number you could get.
 *
 * Example 1: Input: 2736 Output: 7236 Explanation: Swap the number 2 and the number 7. Example 2:
 * Input: 9973 Output: 9973 Explanation: No swap.
 */
public class MaximumSwap {

  private int[] convert(int n) {
    List<Integer> num = new ArrayList<>();
    while (n > 0) {
      num.add(0, n % 10);
      n /= 10;
    }

    return num.stream().mapToInt(i -> i).toArray();
  }

  private void swap(int[] nums, int i, int j) {
    if (i == j) {
      return;
    }

    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  private int getNumber(int[] nums) {
    int result = 0;

    for (int i = 0; i < nums.length; i++) {
      result = result * 10 + nums[i];
    }

    return result;
  }

  public int maximumSwap(int num) {

    int[] digits = convert(num);

    int result = num;

    for (int i = 1; i < digits.length; i++) {
      for (int j = 0; j < i; j++) {
        if (digits[i] > digits[j]) {
          // swap the digit
          swap(digits, i, j);

          result = Math.max(result, getNumber(digits));

          // swap back for next iteration check
          swap(digits, i, j);
        }
      }
    }

    return result;
  }
}
