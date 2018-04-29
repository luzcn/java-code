package leetcode;

import java.util.Arrays;

/**
 * Given a list of non negative integers, arrange them such that they form the largest number.
 * <p>
 * For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
 * <p>
 * Note: The result may be very large, so you need to return a string instead of an integer.
 */
public class LargestNumber {

    public String largestNumber(int[] nums) {

        int[] s = Arrays.stream(nums)
                .boxed()
                .sorted((a, b) -> {
                    String r1 = String.valueOf(a) + String.valueOf(b);
                    String r2 = String.valueOf(b) + String.valueOf(a);
                    return r2.compareTo(r1);
                })
                .mapToInt(i -> i)
                .toArray();

        StringBuilder res = new StringBuilder();

        for (int i : s) {
            res.append(i);
        }

        return res.toString().charAt(0) == '0' ? "0" : res.toString();
    }
}
