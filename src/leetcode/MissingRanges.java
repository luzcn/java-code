package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a sorted integer array nums, where the range of elements are in the inclusive range [lower, upper],
 * return its missing ranges.
 *
 * Example:
 *
 * Input: nums = [0, 1, 3, 50, 75], lower = 0 and upper = 99,
 * Output: ["2", "4->49", "51->74", "76->99"]
 */
public class MissingRanges {


    public List<String> findMissingRanges(int[] nums, int lower, int upper) {

        List<String> result = new ArrayList<>();

        if (nums.length == 0) {
            String range = lower == upper ? String.valueOf(lower) : lower + "->" + upper;
            result.add(range);
            return result;
        }

        int n = nums.length;

        // process the lower to first element
        int first = nums[0];
        if (lower < first) {
            String range = lower == first - 1 ? String.valueOf(lower) : lower + "->" + String.valueOf(first - 1);
            result.add(range);
        }

        for (int i = 0; i < n - 1; i++) {
            int value1 = nums[i];
            int value2 = nums[i + 1];
            // the input is sorted array, if these adjacent numbers are equivalent or contiguous, skip
            if (value1 == value2 || value1 + 1 == value2) {
                continue;
            }

            String range = value1 + 1 == value2 - 1 ? String.valueOf(value1 + 1)
                    : String.valueOf(value1 + 1) + "->" + String.valueOf(value2 - 1);

            result.add(range);
        }

        // process the last element to the uppder range
        int last = nums[n - 1];
        if (last < upper) {
            String range = last + 1 == upper ? String.valueOf(last + 1)
                    : String.valueOf(last + 1) + "->" + String.valueOf(upper);
            result.add(range);
        }

        return result;
    }
}
