package leetcode;

import java.util.*;

// Given a non-empty 2D array grid of 0's and 1's,
// an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.)
//
// You may assume all four edges of the grid are surrounded by water.
//
// Count the number of distinct islands.
// An island is considered to be the same as another if and only if one island can be translated
// (and not rotated or reflected) to equal the other.
//
// Example 1:
// 11000
// 11000
// 00011
// 00011
// Given the above grid map, return 1.
//
// Example 2:
// 11011
// 10000
// 00001
// 11011
// Given the above grid map, return 3.
public class NumberOfDistinctIslands {

    private boolean[][] visited;
    private String shape = "";

    private void dfs(int[][] grid, int x, int y, String id) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) {
            return;
        }

        if (visited[x][y] || grid[x][y] == 0) {
            return;
        }

        visited[x][y] = true;
        shape += id;

        dfs(grid, x - 1, y, "L");
        dfs(grid, x + 1, y, "R");
        dfs(grid, x, y - 1, "U");
        dfs(grid, x, y + 1, "D");

        shape += "#";
    }

    public int numDistinctIslands(int[][] grid) {
        int m = grid.length;
        if (m == 0) {
            return 0;
        }

        int n = grid[0].length;
        visited = new boolean[m][n];
        HashSet<String> shapes = new HashSet<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {

                    shape = "";
                    dfs(grid, i, j, "0");

                    if (!shape.isEmpty()) {
                        shapes.add(shape);
                    }
                }
            }
        }

        return shapes.size();
    }
}
