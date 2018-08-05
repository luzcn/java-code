package leetcode;

import java.util.*;


// There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
//
// You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1).
// You begin the journey with an empty tank at one of the gas stations.
//
// Return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1.
//
// Note:
//
// - If there exists a solution, it is guaranteed to be unique.
// - Both input arrays are non-empty and have the same length.
// - Each element in the input arrays is a non-negative integer.
//
// Example 1:
//
// Input:
// gas  = [1,2,3,4,5]
// cost = [3,4,5,1,2]
//
// Output: 3
//
// Explanation:
// Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
// Travel to station 4. Your tank = 4 - 1 + 5 = 8
// Travel to station 0. Your tank = 8 - 2 + 1 = 7
// Travel to station 1. Your tank = 7 - 3 + 2 = 6
// Travel to station 2. Your tank = 6 - 4 + 3 = 5
// Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
// Therefore, return 3 as the starting index.
public class GasStation_134 {
    // greedy idea

    // The idea is if we start at any ith station, where ith station has positive gas-cost,and we fail at kth station(total gas < 0),
    // starting from any station between ith and kth will not be possible.
    //
    // Thus, when we fail at k, next time we try to start at a station after k,
    // where the station has positive gas-cost, and see if we can finish the circuit from there.
    //
    // If at the end of the gas/cost array the gas - cost value is still negative or
    // starting at the end of the array is still not a valid solution, we return -1;

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int start = 0;
        int end = 0;

        int n = gas.length;

        while (start < n && gas[start] - cost[start] < 0) {
            start++;
        }
        if (start >= n) {
            return -1;
        }

        end = start + 1;

        // the amount of gas left when moving from i to i+1
        // if this value < 0, means failed to reach from i to i + 1
        int tank = gas[start] - cost[start];

        while (end % n != start) {

            tank += gas[end] - cost[end];

            if (tank < 0) {
                // failed,
                // try start from end + 1 position
                start = end + 1;
                while (start < n && gas[start] - cost[start] < 0) {
                    start++;
                }

                if (start >= n) {
                    return -1;
                }

                tank = gas[start] - cost[start];
                end = start + 1;
            } else {
                end++;
            }
        }

        return start;
    }

}
