package leetcode;

import java.util.*;

// You want to build a house on an empty land which reaches all buildings in the shortest amount of distance.
//
// You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:
//
// Each 0 marks an empty land which you can pass by freely.
// Each 1 marks a building which you cannot pass through.
// Each 2 marks an obstacle which you cannot pass through.
// Example:
//
// Input: [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
//
// 1 - 0 - 2 - 0 - 1
// |   |   |   |   |
// 0 - 0 - 0 - 0 - 0
// |   |   |   |   |
// 0 - 0 - 1 - 0 - 0
//
// Output: 7
//
// Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2),
//              the point (1,2) is an ideal empty land to build a house, as the total
//              travel distance of 3+3+1=7 is minimal. So return 7.
// Note:
// There will be at least one building. If it is not possible to build such house according to the above rules, return -1.
public class ShortestDistanceFromAllBuildings {

    private int m;
    private int n;
    private int[][] distance;
    private int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private static final int INF = Integer.MAX_VALUE;

    public int shortestDistance(int[][] grid) {
        m = grid.length;
        if (m == 0) {
            return 0;
        }
        n = grid[0].length;

        // init the distance to all Integer.MAX_VALUE
        distance = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    bfs(grid, i, j);
                }
            }
        }

        int minDistance = Integer.MAX_VALUE;

        // get the shortest distance
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    minDistance = Math.min(minDistance, distance[i][j]);
                }
            }
        }

        return minDistance == INF ? -1 : minDistance;
    }

    private void bfs(int[][] grid, int i, int j) {

        boolean[][] visited = new boolean[m][n];
        Queue<Cell> queue = new LinkedList<>();

        for (int[] dir : dirs) {
            queue.offer(new Cell(i + dir[0], j + dir[1], 1));
        }

        while (!queue.isEmpty()) {
            Cell p = queue.poll();
            int x = p.x;
            int y = p.y;
            int dist = p.distance;

            if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y] || grid[x][y] != 0) {
                continue;
            }

            visited[x][y] = true;

            if (distance[x][y] != INF) {
                distance[x][y] += dist;
            }

            for (int[] dir : dirs) {
                queue.offer(new Cell(x + dir[0], y + dir[1], dist + 1));
            }
        }

        // if some land is not reachable, set to INF
        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                if (grid[x][y] == 0 && !visited[x][y]) {
                    distance[x][y] = INF;
                }
            }
        }

    }

    private class Cell {

        int x;
        int y;
        int distance;

        Cell(int i, int j, int dist) {
            x = i;
            y = j;
            distance = dist;
        }
    }
}
