package leetcode;

import java.util.PriorityQueue;

// Given n non-negative integers representing an elevation map where the width of each bar is 1,
// compute how much water it is able to trap after raining.
//
//
// The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
// In this case, 6 units of rain water (blue section) are being trapped.
//
// Example:
//
// Input: [0,1,0,2,1,0,1,3,2,1,2,1]
// Output: 6
public class TrappingRainWater_42 {

  // the general idea is for each position i, we need to know the max height on it's left side [0..i-1]
  // and the max height on it's right side [i+1...n-1].
  // the brute force solution takes O(n^2)
  //
  // use dp idea, scan twice from left to right and right to left
  // save the highest bar found so far
  // then we reduce to O(n) time and O(n) space
  public int trap(int[] height) {
    if (height.length == 0) {
      return 0;
    }

    int n = height.length;

    int[] left = new int[n];
    left[0] = height[0];
    for (int i = 1; i < n; i++) {
      left[i] = Math.max(left[i - 1], height[i]);
    }

    int[] right = new int[n];
    right[n - 1] = height[n - 1];
    for (int i = n - 2; i >= 0; i--) {
      right[i] = Math.max(right[i + 1], height[i]);
    }

    int sum = 0;
    for (int i = 0; i < n; i++) {
      sum += Math.min(left[i], right[i]) - height[i];
    }

    return sum;
  }

  // 407. Trapping Rain Water II
  // Given an m x n matrix of positive integers representing the height of each unit cell in a 2D elevation map,
  // compute the volume of water it is able to trap after raining.
  //
  // Note:
  // Both m and n are less than 110. The height of each unit cell is greater than 0 and is less than 20,000.
  //
  // Example:
  //
  // Given the following 3x6 height map:
  // [
  //   [1,4,3,1,3,2],
  //   [3,2,1,3,2,4],
  //   [2,3,3,2,3,1]
  // ]
  //
  // Return 4.
  public int trapRainWater(int[][] heightMap) {

    // use bfs, each cell of edges cannot trap any water,
    // so starting searching from these cells;

    // use a priority queue to save the unvisited cells and each time start from the cell with minimum height

    int res = 0;
    int m = heightMap.length;
    if (m == 0) {
      return 0;
    }

    int n = heightMap[0].length;
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    boolean[][] visited = new boolean[m][n];

    // x[0] is the row index, x[1] is column index and x[2] is height
    PriorityQueue<int[]> queue = new PriorityQueue<>((x, y) -> x[2] - y[2]);

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        // all edge cells cannot hold water,
        // starting bfs search from here.
        if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
          queue.add(new int[]{i, j, heightMap[i][j]});
          visited[i][j] = true;
        }
      }
    }

    int maxHeight = 0;

    while (!queue.isEmpty()) {
      int[] cell = queue.poll();

      int i = cell[0];
      int j = cell[1];
      int h = cell[2];
      maxHeight = Math.max(maxHeight, h);

      for (int[] dir : dirs) {
        int x = i + dir[0];
        int y = j + dir[1];

        if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y]) {
          continue;
        }

        queue.add(new int[]{x, y, heightMap[x][y]});
        visited[x][y] = true;

        if (heightMap[x][y] < maxHeight) {
          res += maxHeight - heightMap[x][y];
        }
      }
    }

    return res;
  }

}
