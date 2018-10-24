package leetcode;

// You are given a m x n 2D grid initialized with these three possible values.
//
// -1 - A wall or an obstacle.
// 0 - A gate.
// INF - Infinity means an empty room. We use the value 2^31 - 1 = 2147483647 to represent INF
// as you may assume that the distance to a gate is less than 2147483647.
//
// Fill each empty room with the distance to its nearest gate.
// If it is impossible to reach a gate, it should be filled with INF.
//
// For example, given the 2D grid:
// INF  -1  0  INF
// INF INF INF  -1
// INF  -1 INF  -1
// 0  -1 INF INF
// After running your function, the 2D grid should be:
// 3  -1   0   1
// 2   2   1  -1
// 1  -1   2  -1
// 0  -1   3   4
//
public class WallsAndGates {

  private static final int INF = Integer.MAX_VALUE;

  private static final int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

  private void dfs(int[][] rooms, int x, int y, boolean[][] visited, int distance) {
    if (x < 0 || x >= rooms.length || y < 0 || y >= rooms[0].length || visited[x][y]
        || rooms[x][y] == -1
        || rooms[x][y] == 0) {
      return;
    }

    visited[x][y] = true;
    rooms[x][y] = Math.min(rooms[x][y], distance);

    for (int[] dir : dirs) {
      dfs(rooms, x + dir[0], y + dir[1], visited, distance + 1);
    }

    visited[x][y] = false;
  }

  public void wallsAndGates(int[][] rooms) {
    if (rooms.length == 0) {
      return;
    }

    int m = rooms.length;
    int n = rooms[0].length;
    boolean[][] visited = new boolean[m][n];

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (rooms[i][j] == 0) {
          for (int[] dir : dirs) {
            dfs(rooms, i + dir[0], j + dir[1], visited, 1);
          }
        }
      }
    }
  }
}
