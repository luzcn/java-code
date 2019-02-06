package leetcode;

// Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
//
// Note: You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold
// additional elements from nums2. The number of elements initialized in nums1 and nums2 are m and n
// respectively.
public class MergeTwoSortedArray {

  public void merge(int[] nums1, int m, int[] nums2, int n) {
    int end = m + n - 1;    // write position in nums1
    int p = m - 1;  // read position of nums1
    int q = n - 1;  // read position of nums2

    while (p >= 0 && q >= 0) {
      if (nums1[p] >= nums2[q]) {
        nums1[end--] = nums1[p--];
      } else {
        nums1[end--] = nums2[q--];
      }
    }

    while (q >= 0) {
      nums1[end--] = nums2[q--];
    }
  }
}
