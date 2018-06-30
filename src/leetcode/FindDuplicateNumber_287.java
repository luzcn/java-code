package leetcode;

import java.util.*;

// Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
// prove that at least one duplicate number must exist.
//
// Assume that there is only one duplicate number, find the duplicate one.
//
// Example 1:
//
// Input: [1,3,4,2,2]
// Output: 2
// Example 2:
//
// Input: [3,1,3,4,2]
// Output: 3
// Note:
//
// - You must not modify the array (assume the array is read only).
// - You must use only constant, O(1) extra space.
// - Your runtime complexity should be less than O(n2).
// - There is only one duplicate number in the array, but it could be repeated more than once.
public class FindDuplicateNumber_287 {
    // brute-force solution needs O(n^2) time
    // sorting needs to modify the original array

    // so we can use binary search + counting idea;
    // let l = 1, r = n; so all the numbers [1...n] are sorted.
    // let mid = l + (r-l)/2, count all numbers nums[i] which is less than mid,
    // if we found count > mid, the duplicate number is less than mid, let r = mid
    // else l = mid + 1
    public int findDuplicate(int[] nums) {
        int l = 1;
        int r = nums.length;

        while (l < r) {
            int count = 0;
            int mid = l + (r - l) / 2;

            for (int n : nums) {
                // the [l...r] are sorted,
                if (n <= mid) {
                    count++;
                }
            }

            if (count > mid) {
                // the duplicate number is less than mid
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return l;
    }

    // if we can modify the original array
    // we can mark the visited number as negative
    // next time, we see a number already < 0, return it
    // public int findDuplicateCanModify(int[] nums) {
    //     int res = -1;
    //
    //     for (int i = 0; i < nums.length; i++) {
    //         int t = Math.abs(nums[i]);
    //
    //         if (nums[t - 1] > 0) {
    //             nums[t - 1] = 0 - nums[t - 1];
    //         } else {
    //             res = 0 - nums[t - 1];
    //             break;
    //         }
    //     }
    //
    //     return res;
    // }
}
