package leetcode;

import java.util.*;

// You are given an integer array sorted in ascending order (may contain duplicates),
// you need to split them into several subsequences, where each subsequences consist of at least 3 consecutive integers.
//
// Return whether you can make such a split.
//
// Example 1:
// Input: [1,2,3,3,4,5]
// Output: True
// Explanation:
// You can split them into two consecutive subsequences :
// 1, 2, 3
// 3, 4, 5
// Example 2:
// Input: [1,2,3,3,4,4,5,5]
// Output: True
// Explanation:
// You can split them into two consecutive subsequences :
// 1, 2, 3, 4, 5
// 3, 4, 5
// Example 3:
// Input: [1,2,3,4,4,5]
// Output: False
public class SplitArrayIntoConsecutiveSubsequences_659 {

    // for each number n, there are 3 cases:
    // 1. the heap top element peek.last == n, then we create a new Sequence element [n, 1]
    // 2. n == peek.last + 1, then we can simply update the peek.last = n and increase the length by 1
    // 3. n > peek.last + 1, then we need to keep pop out the heap,  until we found peek.last + 1= n or heap is empty
    public boolean isPossible(int[] nums) {

        // min heap
        PriorityQueue<Sequence> heap = new PriorityQueue<>((x, y) -> {
            if (x.last == y.last) {
                return x.length - y.length;
            }

            return x.last - y.last;
        });

        for (int n : nums) {

            // if (heap.isEmpty() || heap.peek().last == n) {
            //     heap.add(new Sequence(n, 1));
            //     continue;
            // }
            //
            // if (heap.peek().last + 1 == n) {
            //     Sequence topElement = heap.poll();
            //
            //     topElement.last = n;
            //     topElement.length++;
            //     heap.add(topElement);
            //     continue;
            // }
            //
            // while (!heap.isEmpty() && n > heap.peek().last + 1) {
            //     heap.poll();
            // }
            // int length = 0;
            // if (!heap.isEmpty()) {
            //     length = heap.poll().length;
            // }
            //
            // heap.add(new Sequence(n, length + 1));

            if (!heap.isEmpty() && heap.peek().last == n) {
                heap.add(new Sequence(n, 1));
                continue;
            }

            while (!heap.isEmpty() && heap.peek().last + 1 != n) {
                Sequence topElement = heap.poll();
                if (topElement.length < 3) {
                    return false;
                }
            }

            int length = 0;
            if (!heap.isEmpty()) {
                length = heap.poll().length;
            }

            heap.add(new Sequence(n, length + 1));

        }

        while (!heap.isEmpty()) {
            if (heap.poll().length < 3) {
                return false;
            }
        }

        return true;
    }

    private class Sequence {

        int last;
        int length;

        public Sequence(int num, int size) {
            last = num;
            length = size;
        }
    }
}
