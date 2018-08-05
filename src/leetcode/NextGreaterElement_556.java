package leetcode;

import java.util.*;

// Given a positive 32-bit integer n,
// you need to find the smallest 32-bit integer which has exactly the same digits existing in the integer n
// and is greater in value than n.
//
// If no such positive 32-bit integer exists, you need to return -1.
//
// Example 1:
//
// Input: 12
// Output: 21
//
//
// Example 2:
//
// Input: 21
// Output: -1
public class NextGreaterElement_556 {

    // need to consider overflow problem

    public int nextGreaterElement(int n) {

        char[] nums = String.valueOf(n).toCharArray();

        int len = nums.length;

        // 112 => 121
        if (nums[len - 2] < nums[len - 1]) {
            swap(nums, len - 2, len - 1);
            return Integer.parseInt(new String(nums));
        }

        int i = len - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        if (i >= 0) {

            int index = len - 1;
            while (index > 0 && nums[index] <= nums[i]) {
                index--;
            }

            swap(nums, i, index);
        }

        Arrays.sort(nums, i + 1, len);

        return Integer.parseInt(new String(nums));
    }

    private void swap(char[] nums, int i, int j) {
        if (i == j) {
            return;
        }

        char temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
