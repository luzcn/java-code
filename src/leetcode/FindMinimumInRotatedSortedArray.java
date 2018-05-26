package leetcode;

import java.util.*;

//Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
//
// (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
//
// Find the minimum element.
//
// You may assume no duplicate exists in the array.
//
// Example 1:
//
// Input: [3,4,5,1,2]
// Output: 1
// Example 2:
//
// Input: [4,5,6,7,0,1,2]
// Output: 0
public class FindMinimumInRotatedSortedArray {

    // binary search
    // if the array is not rotated, A[m] should < A[r],
    // - so every time if we see A[m] < A[r], move to left i.e. r = m
    // do not use r = m-1, because A[m] could be the min value
    //- if we see A[m] > A[r], now min value should be in range [m...r], search right side

    public int findMin(int[] nums) {

        int l = 0;
        int r = nums.length - 1;

        while (l < r) {
            int mid = l + (r - l) / 2;

            if (nums[mid] > nums[r]) {
                l = mid + 1;
            } else {
                // nums[mid] could be the min value
                // do  not use mid - 1
                r = mid;
            }
        }

        return nums[l];
    }
}