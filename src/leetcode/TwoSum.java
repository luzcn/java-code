package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 *
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] result = new int[2];

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                // do not need to check if map.get == i,
                // because i is not in the map yet.
                result[0] = map.get(target - nums[i]);
                result[1] = i;
            } else {
                map.put(nums[i], i);
            }
        }

        return result;
    }

    /**
     * Given an array of integers that is already sorted in ascending order,
     * find two numbers such that they add up to a specific target number.
     *
     * The function twoSum should return indices of the two numbers such that they add up to the target,
     * where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.
     *
     * You may assume that each input would have exactly one solution and you may not use the same element twice.
     *
     * Input: numbers={2, 7, 11, 15}, target=9
     * Output: index1=1, index2=2
     */
    public int[] twoSumInSortedArray(int[] nums, int target) {
        int[] result = new int[2];

        if (nums.length == 0)
            return result;

        int begin = 0;
        int end = nums.length - 1;

        while (begin < end) {
            if (target == nums[begin] + nums[end]) {
                result[0] = begin + 1;
                result[1] = end + 1;
                break;
            } else if (target > nums[begin] + nums[end]) {
                begin++;
            } else {
                end--;
            }
        }

        return result;
    }
}
