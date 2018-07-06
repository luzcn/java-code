package leetcode;

import java.util.*;

// In an exam room, there are N seats in a single row, numbered 0, 1, 2, ..., N-1.
//
// When a student enters the room, they must sit in the seat that maximizes the distance to the closest person.
// If there are multiple such seats, they sit in the seat with the lowest number.
// (Also, if no one is in the room, then the student sits at seat number 0.)
//
// Return a class ExamRoom(int N) that exposes two functions:
// - ExamRoom.seat() returning an int representing what seat the student sat in,
// - and ExamRoom.leave(int p) representing that the student in seat number p now leaves the room.
//
// - It is guaranteed that any calls to ExamRoom.leave(p) have a student sitting in seat p.
//
//
// Example 1:
//
// Input: ["ExamRoom","seat","seat","seat","seat","leave","seat"], [[10],[],[],[],[],[4],[]]
// Output: [null,0,9,4,2,null,5]
//
// Explanation:
// ExamRoom(10) -> null
// seat() -> 0, no one is in the room, then the student sits at seat number 0.
// seat() -> 9, the student sits at the last seat number 9.
// seat() -> 4, the student sits at the last seat number 4.
// seat() -> 2, the student sits at the last seat number 2.
// leave(4) -> null
// seat() -> 5, the student​​​​​​​ sits at the last seat number 5.
public class ExamRoom_855 {

    private int[] seats;
    private int n;
    private PriorityQueue<int[]> heap = new PriorityQueue<>((x, y) -> {
        if ((y[1] - y[0]) / 2 == (x[1] - x[0]) / 2) {
            return x[0] - y[0];
        }

        return (y[1] - y[0]) - (x[1] - x[0]);
    });


    public ExamRoom_855(int N) {
        seats = new int[N];
        n = N;
    }

    public int seat() {
        if (seats[0] == 0) {
            seats[0] = 1;
            return 0;
        }

        if (heap.isEmpty() && seats[n - 1] == 0) {
            seats[n - 1] = 1;

            heap.add(new int[]{0, n - 1});
            return n - 1;
        }

        if (heap.isEmpty()) {
            return -1;
        }

        int start = heap.peek()[0];
        int end = heap.peek()[1];
        heap.poll();

        int pos = start + (end - start) / 2;

        // if current longest interval starts at 0,
        // then sit to 0
        // also, if end is n, then sit end
        if (start == 0 && seats[start] == 0) {
            pos = start;
        } else if (end == n && seats[n - 1] == 0) {
            pos = n - 1;
        }

        seats[pos] = 1;

        if (pos - 1 != start) {
            heap.add(new int[]{start, pos});
        }

        if (pos + 1 != end) {
            heap.add(new int[]{pos, end});
        }

        return pos;
    }

    public void leave(int p) {

        int left = p - 1;
        int right = p + 1;

        while (left >= 0 && seats[left] == 0) {
            left--;
        }

        while (right < n && seats[right] == 0) {
            right++;
        }

        seats[p] = 0;
        heap.add(new int[]{left, right});
    }
}
