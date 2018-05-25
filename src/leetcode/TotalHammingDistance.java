package leetcode;

/**
 * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
 *
 * Now your job is to find the total Hamming distance between all pairs of the given numbers.
 *
 * Example:
 * Input: 4, 14, 2
 *
 * Output: 6
 *
 * Explanation: In binary representation, the 4 is 0100, 14 is 1110, and 2 is 0010 (just
 * showing the four bits relevant in this case). So the answer will be:
 * HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6.
 */
public class TotalHammingDistance {

    // the brute force solution is check each pair of numbers and sum all the xor results
    // it takes O(n^2*32)
    // we can create an array bit[] with size 32,
    // for each position i, we count the bit of 1 at ith bit position of all numbers
    // and sum up all the bit 1 count for all the numbers
    // now, bit[0] is the total number of bit 1, then n - bit[0] is the total number of 0.
    // so the hamming distance at bit position 0, is bit[0]*(n-bit[0]).
    public int totalHammingDistance(int[] nums) {

        if (nums.length == 0) {
            return 0;
        }

        int[] bitOfOne = new int[32];

        for (int i = 0; i < 32; i++) {
            for (int n : nums) {
                if (((n >> i) & 1) == 1){
                    bitOfOne[i]++;
                }
            }
        }

        int result = 0;
        for (int i = 0; i < 32; i++) {
            result += bitOfOne[i] * (nums.length - bitOfOne[i]);
        }

        return result;
    }

}
