package leetcode;

import java.util.*;


// Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right.
// You can only see the k numbers in the window.
//
// Each time the sliding window moves right by one position.
// Return the max value of each window.
//
// Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
// Output: [3,3,5,5,6,7]
// Explanation:
//
// Window position                Max
// ---------------               -----
// [1  3  -1] -3  5  3  6  7       3
//  1 [3  -1  -3] 5  3  6  7       3
//  1  3 [-1  -3  5] 3  6  7       5
//  1  3  -1 [-3  5  3] 6  7       5
//  1  3  -1  -3 [5  3  6] 7       6
//  1  3  -1  -3  5 [3  6  7]      7
public class SlidingWindowMaximum {

    // the basic idea is using deque, modify first and last both O(1), data structure
    // for each number, keep removing last element from the deque, if it is  greater than last element,
    // until deque is empty or last element is larger. Now, we can guarantee the first is the maximum number in the windows
    //
    // since the window is sliding and the max number may expire, so we need to save the index in the deque
    public int[] maxSlidingWindow(int[] nums, int k) {

        int n = nums.length;
        if (n == 0 || k <= 1) {
            return nums;
        }

        List<Integer> res = new ArrayList<>();

        // double queue
        // the element is index from the input array
        Deque<Integer> queue = new ArrayDeque<>();

        // processing the first k elements
        for (int i = 0; i < k; i++) {
            while (!queue.isEmpty() && nums[queue.getLast()] <= nums[i]) {
                queue.removeLast();
            }
            queue.addLast(i);
        }
        // add the max number of first window into result
        res.add(nums[queue.getFirst()]);

        for (int i = k; i < n; i++) {
            while (!queue.isEmpty() && nums[queue.getLast()] <= nums[i]) {
                queue.removeLast();
            }

            // the max number may be out the sliding window size
            while (!queue.isEmpty() && queue.getFirst() <= i - k) {
                queue.removeFirst();
            }

            queue.addLast(i);

            // add the max number into result
            res.add(nums[queue.getFirst()]);
        }

        return res.stream().mapToInt(x -> x).toArray();
    }
}
