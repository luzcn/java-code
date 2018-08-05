package leetcode;

import java.util.*;

// You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2.
// Find all the next greater numbers for nums1's elements in the corresponding places of nums2.
//
// The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2.
// If it does not exist, output -1 for this number.
//
// Example 1:
// Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
// Output: [-1,3,-1]
// Explanation:
//     For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.
//     For number 1 in the first array, the next greater number for it in the second array is 3.
//     For number 2 in the first array, there is no next greater number for it in the second array, so output -1.
public class NextGreaterElement_496 {

    // for all the numbers in nums2, find the next greater number, saved in a hashmap
    // - use stack
    // - start from last element,
    // - if we found stack.peek() < current number remove from the stack,
    // keep doing this step, until we found a larger one or stack is empty

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {

        HashMap<Integer, Integer> map = new HashMap<>();

        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = nums2.length - 1; i >= 0; i++) {

            int num = nums2[i];
            while (!stack.isEmpty() && stack.getLast() <= num) {
                stack.removeLast();
            }

            if (stack.isEmpty()) {
                map.put(num, -1);
            } else {
                map.put(num, stack.getLast());
            }

            stack.addLast(num);
        }

        int[] res = new int[nums1.length];

        for (int i = 0; i < nums1.length; i++) {
            res[i] = map.get(nums1[i]);
        }

        return res;
    }
}