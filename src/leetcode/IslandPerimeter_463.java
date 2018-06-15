package leetcode;

import java.util.*;

// You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water.
//
// Grid cells are connected horizontally/vertically (not diagonally).
// The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells).
//
// The island doesn't have "lakes" (water inside that isn't connected to the water around the island).
// One cell is a square with side length 1.
// The grid is rectangular, width and height don't exceed 100.
// Determine the perimeter of the island.

public class IslandPerimeter_463 {

    public int islandPerimeter(int[][] grid) {
        if (grid.length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    dfs(grid, i, j, visited);
                }
            }
        }
        return currentSum;
    }

    private int currentSum = 0;

    private void dfs(int[][] grid, int x, int y, boolean[][] visited) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) {
            return;
        }

        if (visited[x][y] || grid[x][y] == 0) {
            return;
        }

        visited[x][y] = true;
        currentSum += getEdge(grid, x, y);

        for (int[] dir : dirs) {
            int i = x + dir[0];
            int j = y + dir[1];
            dfs(grid, i, j, visited);
        }
    }

    private int getEdge(int[][] grid, int i, int j) {
        int sum = 0;

        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];

            // if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 0) {
            //     sum++;
            // }
            if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == 0) {
                sum++;
            }
        }

        return sum;
    }

    private int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
}
