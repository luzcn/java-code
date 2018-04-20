package leetcode;

/**
 * Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.
 *
 * Formally the function should:
 * Return true if there exists i, j, k
 * such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
 * Your algorithm should run in O(n) time complexity and O(1) space complexity.
 *
 * Examples:
 * Given [1, 2, 3, 4, 5],
 * return true.
 *
 * Given [5, 4, 3, 2, 1],
 * return false.
 */
public class IncreasingTripletSubsequence {

    // O(n) space
    public boolean increasingTripletExtraSpace(int[] nums) {
        if (nums.length == 0) {
            return false;
        }

        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];

        // scan from left to right, for each i, save the smallest number from [0...i-1]
        left[0] = nums[0];
        for (int i = 1; i < n; i++) {
            left[i] = left[i - 1] < nums[i] ? left[i - 1] : nums[i];
        }

        right[n - 1] = nums[n - 1];
        for (int j = n - 2; j >= 0; j--) {
            right[j] = right[j + 1] > nums[j] ? right[j + 1] : nums[j];
        }

        for (int i = 1; i < n - 1; i++) {
            if (nums[i] > left[i] && nums[i] < right[i]) {
                return true;
            }
        }

        return false;
    }


    public boolean increasingTriplet(int[] nums) {
        if (nums.length < 3) {
            return false;
        }

        int small = Integer.MAX_VALUE, large = Integer.MAX_VALUE;

        for (int n : nums) {
            if (n < small) {
                small = n;
            } else if (n < large) {
                large = n;
            } else {
                // find the triplet n > large > small
                return true;
            }
        }
        return false;
    }
}
