package leetcode;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Given a non-empty array of digits representing a non-negative integer, plus one to the integer.
 *
 * The digits are stored such that the most significant digit is at the head of the list,
 * and each element in the array contain a single digit.
 *
 * You may assume the integer does not contain any leading zero, except the number 0 itself.
 *
 * Example 1:
 *
 * Input: [1,2,3]
 * Output: [1,2,4]
 * Explanation: The array represents the integer 123.
 * Example 2:
 *
 * Input: [4,3,2,1]
 * Output: [4,3,2,2]
 * Explanation: The array represents the integer 4321.
 */
public class PlusOne {

    public int[] plusOne(int[] digits) {
        if (digits.length == 0) {
            return new int[0];
        }

        ArrayList<Integer> result = new ArrayList<>();
        int i = digits.length - 1;

        while (i >= 0 && digits[i] == 9) {
            result.add(0);
            i--;
        }

        if (i < 0) {
            result.add(1);
        } else {
            result.add(digits[i] + 1);
            i--;

            while (i >= 0) {
                result.add(digits[i]);
                i--;
            }
        }

        // need to reverse the result
        Collections.reverse(result);

        return result.stream().mapToInt(x -> x).toArray();
    }
}
