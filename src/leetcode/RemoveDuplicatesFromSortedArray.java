package leetcode;

/**
 * Given a sorted array, remove the duplicates in-place such that each element appear only once and
 * return the new length.
 *
 * Do not allocate extra space for another array, you must do this by modifying the input array
 * in-place with O(1) extra memory.
 *
 * Example:
 *
 * Given nums = [1,1,2],
 *
 * Your function should return length = 2, with the first two elements of nums being 1 and 2
 * respectively. It doesn't matter what you leave beyond the new length.
 */
public class RemoveDuplicatesFromSortedArray {

  // the array is sorted
  // use two pointer solution
  public int removeDuplicates(int[] nums) {
    int begin = 0;
    int end = 0;

    while (end < nums.length) {
      if (nums[end] != nums[begin]) {
        nums[++begin] = nums[end];
      }

      end++;
    }

    return begin + 1;
  }

  /**
   * Follow up for "Remove Duplicates": What if duplicates are allowed at most twice?
   *
   * For example, Given sorted array nums = [1,1,1,2,2,3],
   *
   * Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2
   * and 3.
   *
   * It doesn't matter what you leave beyond the new length.
   */
  public int removeDuplicates2(int[] nums) {
    // similar to the first question,
    // make sure when found 1 duplicate, also need to copy nums[++begin] = nums[end]
    int begin = 0;
    int end = 0;
    // there is at least 1 appearance of each number.
    // so when count is 1, there are 2 duplicates now.
    int count = 0;

    while (end < nums.length) {
      if (nums[end] != nums[begin]) {
        nums[++begin] = nums[end];
        count = 0;
      } else if (++count < 2) {
        nums[++begin] = nums[end];
      }
    }
    return begin + 1;
  }
}
