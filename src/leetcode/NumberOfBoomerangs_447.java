package leetcode;

import java.util.*;

// Given n points in the plane that are all pairwise distinct, a "boomerang" is a tuple of points (i, j, k)
// such that the distance between i and j equals the distance between i and k (the order of the tuple matters).
//
// Find the number of boomerangs.
// You may assume that n will be at most 500 and coordinates of points are all in the range [-10000, 10000] (inclusive).
//
// Example:
// Input:
// [[0,0],[1,0],[2,0]]
//
// Output:
// 2
//
// Explanation:
// The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]]
public class NumberOfBoomerangs_447 {

    public int numberOfBoomerangs(int[][] points) {

        int res = 0;
        for (int i = 0; i < points.length; i++) {
            HashMap<Integer, Integer> map = new HashMap<>();

            for (int j = 0; j < points.length; j++) {
                if (i == j) {
                    continue;
                }

                int distance = getDistance(points[i], points[j]);
                map.put(distance, map.getOrDefault(distance, 0) + 1);
            }

            for (int c : map.values()) {
                res += c * (c - 1);
            }
        }

        return res;
    }


    private int getDistance(int[] p1, int[] p2) {
        int v1 = p1[0] - p2[0];
        int v2 = p1[1] - p2[1];

        return v1 * v1 + v2 * v2;
    }
}
