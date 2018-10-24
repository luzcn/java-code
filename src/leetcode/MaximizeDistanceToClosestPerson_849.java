package leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

// In a row of seats, 1 represents a person sitting in that seat, and 0 represents that the seat is empty.
//
// There is at least one empty seat, and at least one person sitting.
//
// Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized.
//
// Return that maximum distance to closest person.
//
// Example 1:
// Input: [1,0,0,0,1,0,1]
// Output: 2
// Explanation:
// - If Alex sits in the second open seat (seats[2]), then the closest person has distance 2.
// - If Alex sits in any other open seat, the closest person has distance 1.
// - Thus, the maximum distance to the closest person is 2.
//
// Example 2:
// Input: [1,0,0,0]
// Output: 3
// Explanation:
// - If Alex sits in the last seat, the closest person is 3 seats away.
// - This is the maximum distance possible, so the answer is 3.
public class MaximizeDistanceToClosestPerson_849 {

  // use two helper array
  // O(n) time, O(n) space
  public int maxDistToClosest(int[] seats) {
    int n = seats.length;

    // left[i] means the distance to the person of left i
    int[] left = new int[n];
    Arrays.fill(left, n);

    int[] right = new int[n];
    Arrays.fill(right, n);

    for (int i = 0; i < n; i++) {
      if (seats[i] == 1) {
        left[i] = 1;
      } else if (i > 0) {
        left[i] = left[i - 1] + 1;
      }
    }

    for (int i = n - 1; i >= 0; i--) {
      if (seats[i] == 1) {
        right[i] = 0;
      } else if (i < n - 1) {
        right[i] = right[i + 1] + 1;
      }
    }

    int res = 0;

    for (int i = 0; i < n; i++) {
      res = Math.max(res, Math.min(left[i], right[i]));
    }

    return res;
  }

  // two pointer idea
  // keep left as the position of left person
  // use right to find the closest right person
  public int maxDistToClosest2(int[] seats) {
    int n = seats.length;

    int left = -1;
    int right = 0;

    int res = 0;

    for (int i = 0; i < n; i++) {
      if (seats[i] == 1) {
        left = i;
      } else {
        right = i + 1;
        while (right < n && seats[right] == 0) {
          right++;
        }
      }

      int leftDistance = left == -1 ? n : i - left;
      int rightDistance = right == n ? n : right - i;
      res = Math.max(res, Math.min(leftDistance, rightDistance));

    }

    return res;
  }

  // design this seat assign class
  // call the getSeats multiple times, maximize the distance to the closest person

  private int[] seats;

  private PriorityQueue<int[]> heap = new PriorityQueue<>((x, y) -> (y[1] - y[0]) - (x[1] - x[0]));

  public MaximizeDistanceToClosestPerson_849(int n) {
    seats = new int[n];
  }

  // return the index, that assign the person
  // -1 if no more seats available
  public int assign() {

    int n = seats.length;

    if (seats[0] == 0) {
      seats[0] = 1;
      return 0;
    }

    if (seats[n - 1] == 0) {
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
    seats[pos] = 1;

    if (pos - 1 != start) {
      heap.add(new int[]{start, pos});
    }

    if (pos + 1 != end) {
      heap.add(new int[]{pos, end});
    }

    return pos;
  }
}
