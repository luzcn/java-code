package leetcode;

/**
 * Given a non-empty array of integers, return the third maximum number in this array.
 * If it does not exist, return the maximum number. The time complexity must be in O(n).
 *
 * Input: [2, 2, 3, 1]
 *
 * Output: 1
 *
 * Explanation: Note that the third maximum here means the third maximum distinct number.
 * Both numbers with value 2 are both considered as second maximum.
 */
public class ThirdMaximumNumber {
    public int getThirdMaximum(int[] nums) {

        // use Integer type to avoid the INT_MIN
        Integer first = null;
        Integer second = null;
        Integer third = null;


        for (Integer n : nums) {
            // the question requires the distinct number.
            if (n.equals(first) || n.equals(second) || n.equals(third))
                continue;

            if (first == null || n > first) {
                third = second;
                second = first;
                first = n;
            } else if (second == null || n > second) {
                third = second;
                second = first;
            } else if (third == null || n > third) {
                third = n;
            }
        }

        return third == null ? first : third;
    }
}
