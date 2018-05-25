package leetcode;

import java.util.*;

// Given an array consists of non-negative integers, your task is to count the number of triplets
// chosen from the array that can make triangles if we take them as side lengths of a triangle.

// Example 1:
// Input: [2,2,3,4]
// Output: 3
// Explanation:
// Valid combinations are:
// 2,3,4 (using the first 2)
// 2,3,4 (using the second 2)
// 2,2,3
// Note:
// - The length of the given array won't exceed 1000.
// - The integers in the given array are in the range of [0, 1000].
public class ValidTriangleNumber {


    public int triangleNumber(int[] nums) {

        int ans = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] == 0) {
                continue;
            }

            // the trick is here
            // we do not re-initialize the k, for a new j iteration
            int k = i + 2;
            for (int j = i + 1; j < nums.length - 1; j++) {
                while (k < nums.length && nums[i] + nums[j] > nums[k]) {
                    k++;
                }
                ans += k - j - 1;
            }
        }
        return ans;
    }


    // find all triple, check if it can form a triangle
    // O(n^3)
    private int triangleNumberBF(int[] nums) {

        // sort first for easy check

        Arrays.sort(nums);
        int ans = 0;

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] > nums[k]) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }
}
