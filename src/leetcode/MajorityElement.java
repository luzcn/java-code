package leetcode;

import java.util.*;

// Given an array of size n, find the majority element.
// The majority element is the element that appears more than ⌊ n/2 ⌋ times.
//
// You may assume that the array is non-empty and the majority element always exist in the array.
//
// Example 1:
// Input: [3,2,3]
// Output: 3
public class MajorityElement {

    // Moor's voting
    public int majorityElement(int[] nums) {
        int candidate = nums[0];
        int count = 1;

        for (int n : nums) {
            if (candidate == n) {
                count++;
            } else {
                count--;
            }

            if (count == 0) {
                candidate = n;
                count = 1;
            }
        }

        // need to check if the majority number exists
        count = 0;
        for (int n : nums) {
            if (candidate == n) {
                count++;
            }

            if (count > nums.length / 2) {
                return candidate;
            }
        }

        return -1;
    }

    // 229. Majority Element II
    // Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
    // Note: The algorithm should run in linear time and in O(1) space.
    public List<Integer> majorityElement2(int[] nums) {

        // again Moor's voting with two counter
        int candidate1 = 0;
        int candidate2 = 0;
        int count1 = 1;
        int count2 = 1;
        List<Integer> res = new ArrayList<>();

        for (int n : nums) {
            if (candidate1 == n) {
                count1++;
            } else if (candidate2 == n) {
                count2++;
            } else if (count1 == 0) {
                candidate1 = n;
                count1 = 1;
            } else if (count2 == 0) {
                candidate2 = n;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }

        if (count1 == 0 && count2 == 0) {
            return res;
        }

        count1 = 0;
        count2 = 0;

        for (int n : nums) {
            if (candidate1 == n) {
                count1++;
            } else if (candidate2 == n) {
                count2++;
            }

            if (count1 > nums.length / 3) {
                res.add(candidate1);
            }
            if (count2 > nums.length / 3) {
                res.add(candidate2);
            }
        }

        return res;
    }
}
