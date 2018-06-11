package leetcode;

import java.util.*;

// Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
//
// For example,
// MovingAverage m = new MovingAverage(3);
// m.next(1) = 1
// m.next(10) = (1 + 10) / 2
// m.next(3) = (1 + 10 + 3) / 3
// m.next(5) = (10 + 3 + 5) / 3
public class MovingAverageFromDataStream_346 {
    private LinkedList<Integer> data = new LinkedList<>();
    private int cap;
    private int sum;

    /** Initialize your data structure here. */
    public MovingAverageFromDataStream_346(int size) {
        cap = size;
        sum = 0;
    }

    public double next(int val) {
        if (data.size() == cap) {
            sum -= data.getFirst();
            data.removeFirst();
        }

        data.addLast(val);
        sum += val;

        if (data.size() < cap) {
            return (double) sum/data.size();
        }

        return (double) sum/cap;

    }
}
