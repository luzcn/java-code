package leetcode;

import java.util.*;

// Given a sorted array consisting of only integers
// where every element appears twice except for one element which appears once.
//
// sFind this single element that appears only once.
//
// Example 1:
// Input: [1,1,2,3,3,4,4,8,8]
// Output: 2
// Example 2:
// Input: [3,3,7,7,10,11,11]
// Output: 10
// Note: Your solution should run in O(log n) time and O(1) space.
public class SingleElementInSortedArray_540 {

    public int singleNonDuplicate(int[] nums) {
        int n = nums.length;

        if (n == 1) {
            return nums[0];
        }

        // binary search
        int l = 0;
        int r = n - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;

            if (mid == n - 1 && nums[mid] != nums[mid - 1]) {
                return nums[mid];
            }

            if (mid == 0 && nums[mid] != nums[mid + 1]) {
                return nums[mid];
            }

            if (nums[mid] != nums[mid + 1] && nums[mid] != nums[mid - 1]) {
                return nums[mid];
            }

            if (nums[mid] == nums[mid - 1]) {
                // A[m-1] == A[m]
                // check if m+1 is a even number, if it is then single in the range of [mid + 1, r]
                if ((mid - l + 1) % 2 == 0) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            } else {
                if ((r - mid + 1) % 2 == 0) {
                    r = mid - 1;
                } else {
                    l = mid;
                }
            }
        }

        return nums[l];
    }
}
