package leetcode;

import java.util.HashMap;

/**
 * Given a binary array, find the maximum number of consecutive 1s in this array if you can flip at most one 0.
 *
 * Example 1:
 * Input: [1,0,1,1,0]
 * Output: 4
 * Explanation: Flip the first zero will get the the maximum number of consecutive 1s.
 * After flipping, the maximum number of consecutive 1s is 4.
 * Note:
 *
 * The input array will only contain 0 and 1.
 * The length of input array is a positive integer and will not exceed 10,000
 */
public class MaxConsecutiveOnes {


    // counting, use index "last" to indicate the previous position of "0"
    // for each i, i
    public int findMaxConsecutiveOnes(int[] nums) {
        int lastZero = -1;
        int current = 0;
        int maxLength = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                // the current length of consecutive "1" including one "0"
                current = i - lastZero;
                lastZero = i;
            } else {
                current++;
            }

            maxLength = Math.max(maxLength, current);
        }

        return maxLength;
    }

    // Two pointer + hash map
    public int findMaxConsecutiveOnes2(int[] nums) {
        // two pointer
        int n = nums.length;

        if (n == 0) {
            return 0;
        }

        int begin = 0;
        // find the first 1
        while (begin < n && nums[begin] == 0) {
            begin++;
        }

        int end = begin;
        int maxLength = 0;
        int currentLength = 0;

        // use a hash map to save the end position and the length of the consecutive ones
        HashMap<Integer, Integer> map = new HashMap<>();

        while (end <= n) {
            if (end == n) {
                // reach the end of the array,
                // we still need to check if begin - 1 is 0 and begin - 2 is 1
                if (begin - 2 >= 0 && nums[begin - 1] == 0 && nums[begin - 2] == 1) {
                    maxLength = Math.max(maxLength, currentLength + map.get(begin - 2) + 1);
                } else if (begin != 0) {
                    // if begin is 0, the input array is all 1
                    maxLength = Math.max(maxLength, currentLength + 1);
                }
                break;
            }

            if (nums[end] == 1) {
                currentLength++;
                map.put(end, currentLength);
                end++;
                maxLength = Math.max(maxLength, currentLength);
            } else {

                // check if begin - 1 is 0 and begin - 2 is 1
                if (begin - 2 >= 0 && nums[begin - 1] == 0 && nums[begin - 2] == 1) {
                    maxLength = Math.max(maxLength, currentLength + map.get(begin - 2) + 1);
                } else {
                    maxLength = Math.max(maxLength, currentLength + 1);
                }
                begin = end;
                while (begin < n && nums[begin] == 0) {
                    begin++;
                }
                currentLength = 0;
                end = begin;
            }
        }

        return maxLength;
    }
}
