package leetcode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

/////
// Given an array of non-negative integers, you are initially positioned at the first index of the array.
//
// Each element in the array represents your maximum jump length at that position.
//
// Determine if you are able to reach the last index.
//
// Example 1:
//
// Input: [2,3,1,1,4]
// Output: true
// Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
// Example 2:
//
// Input: [3,2,1,0,4]
// Output: false
// Explanation: You will always arrive at index 3 no matter what. Its maximum
// jump length is 0, which makes it impossible to reach the last index.
///
public class JumpGame {


    // greedy solution
    // start from the position 0, we set range = A[0]
    // for each i in [0...range] if we found A[i] + i > range, extend the range to this larger value
    // if the range + 1 >= nums.length, return true
    public boolean canJump(int[] nums) {
        if (nums.length == 0) {
            return false;
        }

        int range = nums[0];

        for (int i = 0; i <= range; i++) {
            if (range + 1 >= nums.length) {
                return true;
            }

            range = Math.max(range, nums[i] + i);
        }

        return false;
    }


    /////
    // Given an array of non-negative integers, you are initially positioned at the first index of the array.
    //
    // Each element in the array represents your maximum jump length at that position.
    //
    // Your goal is to reach the last index in the minimum number of jumps.
    //
    // Example:
    //
    // Input: [2,3,1,1,4]
    // Output: 2
    // Explanation: The minimum number of jumps to reach the last index is 2.
    // Jump 1 step from index 0 to 1, then 3 steps to the last index.
    ///
    public int jump(int[] nums) {
        // when we see minimum, we can use bfs
        // similar to level order

        if (nums.length < 2) {
            // empty list or only one element,
            // no need to jump
            return 0;
        }

        // the elements are index of given array
        Queue<Integer> candidates = new LinkedList<>();
        Queue<Integer> temp = new LinkedList<>();
        int steps = 1;

        candidates.add(0);

        while (!candidates.isEmpty()) {
            int index = candidates.poll();

            int range = index + nums[index];
            if (range + 1 >= nums.length) {
                break;
            }

            int nextIndex = 0;
            // do not use i <= range here as previous question
            // because we need to find the next available index
            for (int i = index + 1; i <= index + nums[index]; i++) {
                if (i + nums[i] > range) {
                    // get the next index that can extend the range as far as possible
                    range = i + nums[i];
                    nextIndex = i;
                }
            }
            if (nextIndex != 0) {
                temp.add(nextIndex);
            }

            if (candidates.isEmpty()) {
                steps++;
                candidates = temp;
                temp = new LinkedList<>();
            }
        }

        return steps;
    }
}
