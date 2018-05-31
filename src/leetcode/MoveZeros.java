package leetcode;

/**
 * Given an array nums, write a function to move all 0's to the end of it
 * while maintaining the relative order of the non-zero elements.
 *
 * For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
 *
 * Note:
 * You must do this in-place without making a copy of the array.
 * Minimize the total number of operations.
 */
public class MoveZeros {

    // two pointer solution
    public void moveZeroes(int[] nums) {
        if (nums.length == 0) {
            return;
        }

        int begin = 0;

        // skip the first non-zero numbers
        while (begin < nums.length && nums[begin] != 0) {
            begin++;
        }

        int end = begin + 1;
        while (end < nums.length) {
            if (nums[end] != 0) {
                nums[begin++] = nums[end];
                nums[end] = 0;
            }
            end++;
        }
    }
}
