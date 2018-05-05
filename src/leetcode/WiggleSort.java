package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
 *
 * For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].
 */
public class WiggleSort {

    // O(n) solution
    // the idea is:
    // - if it is increasing and nums[i-1] > nums[i], or state is decreasing and nums[i-1] < nums[i].
    // we need to swap these (i - 1, i), and repeat until to the end of the array
    // because current state is increasing, it means the previous state is decreasing,
    // so we have order nums[i] < nums[i-1] < nums[i-2], the swap doesn't break the previous order.
    public void wiggleSort(int[] nums) {

        if (nums.length == 0) {
            return;
        }

        int n = nums.length;
        boolean isIncreasing = true;

        for (int i = 1; i < n; i++) {
            // if (isIncreasing) {
            //     if (nums[i - 1] > nums[i]) {
            //         swap(nums, i - 1, i);
            //     }
            // } else {
            //     if (nums[i - 1] < nums[i]) {
            //         swap(nums, i - 1, i);
            //     }
            // }

            if ((isIncreasing && nums[i - 1] > nums[i]) || (!isIncreasing && nums[i - 1] < nums[i])) {
                swap(nums, i - 1, i);
            }
            isIncreasing = !isIncreasing;
        }
    }

    private void swap(int[] nums, int i, int j) {
        if (i == j) {
            return;
        }

        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    /**
     * Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
     *
     * Example:
     * (1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6].
     * (2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].
     *
     * Note:
     * You may assume all input has valid answer.
     *
     * Follow Up:
     * Can you do it in O(n) time and/or in-place with O(1) extra space?
     */
    public void wiggleSort2(int[] nums) {

        // sort the input array in increasing order
        // let s pointing to the middle of the sorted array, and k pointing the last element
        // iterate through the input array, if we have
        List<Integer> sorted = Arrays.stream(nums).boxed().sorted(Comparator.comparingInt(x -> x))
                .collect(Collectors.toList());

        int s = nums.length % 2 == 0 ? nums.length / 2 - 1 : nums.length / 2;
        int k = nums.length - 1;

        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                // even number position
                // we use the first half smaller numbers
                nums[i] = sorted.get(s--);
            } else {
                // use the second half larger numbers.
                nums[i] = sorted.get(k--);
            }
        }
    }

}
