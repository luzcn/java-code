package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.
 *
 * Example 1:
 * Input: [0,1]
 * Output: 2
 * Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
 * Example 2:
 * Input: [0,1,0]
 * Output: 2
 * Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
 */
public class ContiguousArray {

    private int findBruteForce(int[] nums) {
        int maxLength = 0;

        for (int i = 0; i < nums.length; i++) {
            int zeros = 0;
            int ones = 0;
            for (int j = i; j < nums.length; j++) {
                if (nums[j] == 0) {
                    zeros++;
                } else {
                    ones++;
                }

                if (zeros == ones) {
                    maxLength = Math.max(maxLength, j - i + 1);
                }
            }
        }

        return maxLength;
    }

    public int findMaxLength(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }

        int n = nums.length;

        // auxiliary array to save the count of 1 from [0...i]
        int maxLength = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int ones = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                ones++;
                map.put(ones, i);
            }

            int zeros = i + 1 - ones;
            if (map.containsKey(Math.abs(ones - zeros))) {
                maxLength = Math.max(maxLength, i - map.get(Math.abs(ones - zeros)));
            }
        }
        return maxLength;
    }
}
