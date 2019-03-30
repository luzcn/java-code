package leetcode;

import java.util.PriorityQueue;

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
public class TrappingRainWater2_407 {

  // 模拟水平面上升
  // 每次选取高度最低的开始bfs
  // 4个边都不能hold water，所以可以从4个边开始bfs
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
