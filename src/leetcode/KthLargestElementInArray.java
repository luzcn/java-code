package leetcode;

/**
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the
 * sorted order, not the kth distinct element.
 *
 * For example, Given [3,2,1,5,6,4] and k = 2, return 5.
 *
 * Note: You may assume k is always valid, 1 ≤ k ≤ array's length.
 */
public class KthLargestElementInArray {

  // selection algorithm
  // similar to quick sort
  public int findKthLargest(int[] nums, int k) {
    return this.selection(nums, k, 0, nums.length - 1);
  }


  private void swap(int[] nums, int i, int j) {
    if (i == j) {
      return;
    }

    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  private int selection(int[] nums, int k, int l, int r) {
    if (l == r) {
      return nums[l];
    }

    int m = l - 1;
    int pivot = nums[r];

    for (int i = l; i < r; i++) {
      if (nums[i] > pivot) {      // use nums[i] < pivot for kth smallest
        m++;

        // swap m and i
        swap(nums, m, i);
      }
    }

    m++;
    swap(nums, m, r);

    if (m + 1 == k) {
      return nums[m];
    } else if (m + 1 > k) {
      return selection(nums, k, l, m - 1);
    } else {
      return selection(nums, k, m + 1, r);
    }
  }
}
