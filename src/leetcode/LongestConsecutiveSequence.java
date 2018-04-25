package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 *
 * Your algorithm should run in O(n) complexity.
 *
 * Example:
 *
 * Input: [100, 4, 200, 1, 3, 2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 */
public class LongestConsecutiveSequence {

    // - We cannot sort, since it requires O(n) complexity.
    // - A traditional space trade time solution, use a hashmap/hashset
    // 1. for each number "n" in the given vector:
    //  - check if the num -1 is in the hashmap, continue as loop if it is in the hashmap
    //  - check if the num + 1 is in the hashmap, continue as loop if yes.
    // 2. Trace the longest sequence.
    public int longestConsecutive(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }

        Set<Integer> set = new HashSet<>();
        int longestLength = 0;

        for (int n : nums) {
            set.add(n);
        }

        for (int n : nums) {
            int temp = n;
            int currentLength = 1;

            while (set.contains(temp - 1)) {
                temp--;
                currentLength++;
                set.remove(temp);
            }

            temp = n;
            while (set.contains(temp + 1)) {
                temp++;
                currentLength++;
                set.remove(temp);
            }

            longestLength = Math.max(longestLength, currentLength);
        }

        return longestLength;
    }
}
