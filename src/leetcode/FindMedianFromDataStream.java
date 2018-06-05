package leetcode;

import java.util.*;
import java.util.concurrent.ConcurrentMap;


// Median is the middle value in an ordered integer list.
// If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
//
// Examples:
// [2,3,4] , the median is 3
//
// [2,3], the median is (2 + 3) / 2 = 2.5
//
// Design a data structure that supports the following two operations:
//
// - void addNum(int num) - Add a integer number from the data stream to the data structure.
// - double findMedian() - Return the median of all elements so far.

// For example:
// addNum(1)
// addNum(2)
// findMedian() -> 1.5
// addNum(3)
// findMedian() -> 2

public class FindMedianFromDataStream {

    // The idea is using two heaps to simulate a balanced binary search tree, because a balanced bst root is the median.
    // the left is maxheap and right is minheap
    // when add number, comparing the number with two heap top element, if larger than right top then insert to right
    // then if |right.size - left.size| > 1, we need to balance them, pop out the top from larger size heap and move to less size heap
    // when find median, if left and right has equivalent size, then median is (left.peek() + right.peek()) /2
    // otherwise, return the top element of larger size heap

    // the left is max heap
    private PriorityQueue<Integer> left = new PriorityQueue<>((x, y) -> y - x);

    // the right is min heap
    private PriorityQueue<Integer> right = new PriorityQueue<>();

    public FindMedianFromDataStream() {

    }

    public void addNum(int num) {

        if (left.isEmpty()) {
            left.add(num);
            return;
        }


        if (num <= left.peek()) {
            left.add(num);
        } else {
            left.add(num);
        }

        if (right.size() - left.size() > 1) {
            int toMove = right.poll();
            left.add(toMove);
        } else if (left.size() - right.size() > 1) {
            int toMove = left.poll();
            right.add(toMove);
        }
    }

    public double findMedian() {

        if (left.size() == 0 && right.size() == 0) {
            return 0;
        }

        if (left.size() == right.size()) {
            return (double) (left.peek() + right.peek()) / 2;
        } else if (left.size() > right.size()) {
            return left.peek();
        } else {
            return right.peek();
        }
    }
}
