package leetcode;

/**
 * Given an array with n objects colored red, white or blue,
 * sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.
 *
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 */
public class SortColors {
    public void sortColors(int[] nums) {
        if (nums.length == 0) {
            return;
        }

        int begin = 0;
        int end = nums.length - 1;
        int p = begin;

        while (p <= end) {
            if (nums[p] == 0) {
                nums[p] = nums[begin];
                nums[begin++] = 0;

                // ensure index p is always >= begin
                p++;
            } else if (nums[p] == 2) {
                nums[p] = nums[end];
                nums[end--] = 2;
            } else {
                p++;
            }
        }
    }
}
