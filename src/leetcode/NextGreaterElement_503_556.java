package leetcode;

import java.util.*;

// Given a circular array (the next element of the last element is the first element of the array),
// print the Next Greater Number for every element.
//
// The Next Greater Number of a number x is the first greater number to its traversing-order next in the array,
// which means you could search circularly to find its next greater number.
//
// If it doesn't exist, output -1 for this number.
//
// Example 1:
// Input: [1,2,1]
// Output: [2,-1,2]
// Explanation: The first 1's next greater number is 2;
// The number 2 can't find next greater number;
// The second 1's next greater number needs to search circularly, which is also 2.
// Note: The length of given array won't exceed 10000.
public class NextGreaterElement_503_556 {

    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;

        int[] right = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = n - 1; i >= 0; i--) {
            int num = nums[i];

            while (!stack.isEmpty() && stack.getLast() <= num) {
                stack.removeLast();
            }

            if (stack.isEmpty()) {
                right[i] = -1;
            } else {
                right[i] = stack.getLast();
            }

            stack.addLast(num);
        }


        int[] res = new int[n];
        Arrays.fill(res, -1);
        for (int i = 0; i < n; i++) {
            if (right[i] == -1) {
                for (int j = 0; j < i; j++) {
                    if (nums[j] > nums[i]) {
                        res[i] = nums[j];
                    }
                }
            } else {
                res[i] = right[i];
            }
        }

        return res;
    }
}
