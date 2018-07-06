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

        // List<Integer> res = new ArrayList<>();
        int[] res = new int[n - k + 1];

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
        res[0] = nums[queue.getFirst()];

        for (int i = k; i < n; i++) {
            while (!queue.isEmpty() && nums[queue.getLast()] <= nums[i]) {
                queue.removeLast();
            }
            queue.addLast(i);

            // the max number may be out the sliding window size
            // don't need while here, because each time move 1 step ahead
            // only the first element may be expired.
            if (queue.getFirst() <= i - k) {
                queue.removeFirst();
            }

            // add the max number into result
            res[i - k + 1] = nums[queue.getFirst()];
        }

        return res;
    }
}
