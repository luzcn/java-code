package leetcode;

import java.util.*;

// Given an integer array, you need to find one continuous subarray
// that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order, too.
//
// You need to find the shortest such subarray and output its length.
//
// Example 1:
// Input: [2, 6, 4, 8, 10, 9, 15]
// Output: 5
// Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
// Note:
// - The length of the input array is in range [1, 10,000].
// - The input array may contain duplicates, so ascending order here means <=.
public class ShortestUnsortedContinuousSubarray {

    // use sort
    public int findUnsortedSubarraySort(int[] nums) {

        int n = nums.length;
        int[] sorted = nums.clone();

        Arrays.sort(sorted);

        int end = 0;
        int begin = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != sorted[i]) {
                begin = i;
                break;
            }
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] != sorted[i]) {
                end = i;
                break;
            }
        }

        return end > begin ? end - begin + 1 : 0;
    }

    // use stack
    public int findUnsortedSubarrayStack(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }

        Stack<Integer> stack = new Stack<>();
        int l = nums.length - 1;
        int r = 0;

        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                l = Math.min(l, stack.pop());
            }

            stack.push(i);
        }

        stack.clear();
        for (int j = nums.length - 1; j >= 0; j--) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[j]) {
                r = Math.max(r, stack.pop());
            }

            stack.push(j);
        }

        if (r > l) {
            return r - l + 1;
        } else {
            return 0;
        }
    }

    // no extra space and no sorting
    // the idea is to find the left/right boundary to swap by comparing element value
    // - each time if we see a pair not in ascending order, we save the min and max value
    // - scan from begin, when we find the first element that is > minValue,
    // this position should be the minValue correct position after sorted
    // - similarly, we can find the correct position of maxValue
    public int findUnsortedSubarray(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }

        int minValue = Integer.MAX_VALUE;
        int maxValue = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                minValue = Math.min(minValue, nums[i + 1]);
                maxValue = Math.max(maxValue, nums[i]);
            }
        }
        int start = 0;
        int end = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > minValue) {
                start = i;
                break;
            }
        }

        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] < maxValue) {
                end = i;
                break;
            }
        }

        if (end > start) {
            return end - start + 1;
        } else {
            return 0;
        }
    }

}
