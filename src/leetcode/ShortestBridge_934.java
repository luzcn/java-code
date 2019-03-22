package leetcode;

import java.util.ArrayList;
import java.util.List;

// In a given 2D binary array A, there are two islands.
// (An island is a 4-directionally connected group of 1s not connected to any other 1s.)
//
// Now, we may change 0s to 1s so as to connect the two islands together to form 1 island.
//
// Return the smallest number of 0s that must be flipped.  (It is guaranteed that the answer is at least 1.)
//
//
//
// Example 1:
//
// Input: [[0,1],[1,0]]
// Output: 1
// Example 2:
//
// Input: [[0,1,0],[0,0,0],[0,0,1]]
// Output: 2
// Example 3:
//
// Input: [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
// Output: 1
public class ShortestBridge_934 {

  // O(n^2) time
  // O(n) space
  public int shortestBridge(int[][] A) {

    // dfs, get these 2 islands
    int n = A.length;
    int res = Integer.MAX_VALUE;
    boolean[][] visited = new boolean[n][n];

    List<List<int[]>> islands = new ArrayList<>();

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (A[i][j] == 1 && !visited[i][j]) {
          List<int[]> current = new ArrayList<>();
          dfs(A, i, j, visited, current);
          islands.add(new ArrayList<>(current));
        }
      }
    }

    List<int[]> nums1 = islands.get(0);
    List<int[]> nums2 = islands.get(1);

    for (int[] p1 : nums1) {
      for (int[] p2 : nums2) {
        res = Math.min(res, Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]) - 1);
      }
    }

    // compute the shortest distance between these two set of 1s

    return res;
  }


  private void dfs(int[][] nums, int x, int y, boolean[][] visited, List<int[]> current) {
    if (x < 0 || x >= nums.length || y < 0 || y >= nums[0].length || visited[x][y]) {
      return;
    }

    if (nums[x][y] == 0) {
      return;
    }

    visited[x][y] = true;
    current.add(new int[]{x, y});

    for (int[] dir : dirs) {
      dfs(nums, x + dir[0], y + dir[1], visited, current);
    }
  }

  private int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
}
