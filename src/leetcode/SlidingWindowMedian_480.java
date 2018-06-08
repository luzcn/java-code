package leetcode;

import java.util.*;

// Median is the middle value in an ordered integer list.
// If the size of the list is even, there is no middle value.
//
// So the median is the mean of the two middle value.
//
// Examples:
// [2,3,4] , the median is 3
//
// [2,3], the median is (2 + 3) / 2 = 2.5
//
// Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right.
// You can only see the k numbers in the window.
//
// Each time the sliding window moves right by one position.
// Your job is to output the median array for each window in the original array.
//
// For example,
// Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
//
// Window position                Median
// ---------------               -----
// [1  3  -1] -3  5  3  6  7       1
//  1 [3  -1  -3] 5  3  6  7       -1
//  1  3 [-1  -3  5] 3  6  7       -1
//  1  3  -1 [-3  5  3] 6  7       3
//  1  3  -1  -3 [5  3  6] 7       5
//  1  3  -1  -3  5 [3  6  7]      6
// Therefore, return the median sliding window as [1,-1,-1,3,5,6].
//
// Note:
// You may assume k is always valid, ie: k is always smaller than input array's size for non-empty array.
public class SlidingWindowMedian_480 {


    private PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());
    private PriorityQueue<Integer> right = new PriorityQueue<>();

    // similar to problem find median from data stream
    // - use two heaps to simulate the median data
    // - use a hashset to save the invalid number, when the number reaches the heap top, remove them from heap
    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || k <= 0) {
            return new double[0];
        }

        // List<Double> res = new ArrayList<>();

        double[] res = new double[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            addNumber(nums[i]);

            if (i >= k) {
                // invalid i-k th number
                remove(nums[i - k]);
            }

            if (i >= k - 1) {
                res[i - k + 1] = getMedian();
            }
        }

        return res;
    }

    private void balance() {
        if (left.size() - right.size() > 1) {
            right.add(left.poll());
        } else if (right.size() - left.size() > 1) {
            left.add(right.poll());
        }
    }

    private void remove(Integer n) {
        if (!left.remove(n)) {
            right.remove(n);
        }

        balance();
    }

    private void addNumber(int n) {

        if (left.isEmpty()) {
            left.add(n);
            return;
        }

        if (n <= left.peek()) {
            left.add(n);
        } else {
            right.add(n);
        }

        balance();
    }

    private double getMedian() {
        if (left.size() == 0 && right.size() == 0) {
            return 0;
        }

        if (left.size() == right.size()) {
            return left.peek() * 0.5 + right.peek() * 0.5;
        } else if (left.size() > right.size()) {
            return left.peek();
        } else {
            return right.peek();
        }
    }
}
