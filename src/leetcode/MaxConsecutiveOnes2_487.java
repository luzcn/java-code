package leetcode;

public class MaxConsecutiveOnes2_487 {

  public int findMaxConsecutiveOnes(int[] nums) {
    // dp idea
    int n = nums.length;

    int[] left = new int[n];
    int[] right = new int[n];

    left[0] = nums[0];
    for (int i = 1; i < n; i++) {
      if (nums[i] == 1) {
        left[i] = left[i - 1] + 1;
      }
    }

    right[n - 1] = nums[n - 1];
    for (int i = n - 2; i >= 0; i--) {
      if (nums[i] == 1) {
        right[i] = right[i + 1] + 1;
      }
    }

    int res = 0;
    for (int i = 0; i < n; i++) {
      if (nums[i] == 0) {
        int value1 = i > 0 ? left[i - 1] : 0;
        int value2 = i < n - 1 ? right[i + 1] : 0;

        res = Math.max(res, value1 + value2 + 1);
      } else {
        res = Math.max(res, Math.max(left[i], right[i]));
      }
    }

    return res;
  }
}
