package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class TheMaze2 {

    private int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    private int[][] distance;
    private static final int INF = Integer.MAX_VALUE;

    // Maze2, find the shortest distance, bfs
    private void bfs(int[][] maze, int[] start) {

        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            int[] current = queue.remove();

            for (int[] dir : dirs) {
                // get the next position first
                int x = current[0] + dir[0];
                int y = current[1] + dir[1];
                int count = 0;

                // keep moving the ball, until reach a wall
                while (x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 0) {
                    x += dir[0];
                    y += dir[1];
                    count++;
                }

                if (distance[current[0]][current[1]] + count < distance[x - dir[0]][y - dir[1]]) {
                    distance[x - dir[0]][y - dir[1]] = distance[current[0]][current[1]] + count;
                    queue.add(new int[]{x - dir[0], y - dir[1]});
                }
            }
        }
    }


    private void dfs(int[][] maze, int[] current) {
        if (current[0] < 0 || current[0] >= maze.length || current[1] < 0 || current[1] >= maze[0].length
                || maze[current[0]][current[1]] == 1) {
            return;
        }

        for (int[] dir : dirs) {
            int x = current[0] + dir[0];
            int y = current[1] + dir[1];
            int count = 0;

            while (x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 0) {
                x += dir[0];
                y += dir[1];
                count++;
            }

            if (distance[current[0]][current[1]] + count < distance[x - dir[0]][y - dir[1]]) {
                distance[x - dir[0]][y - dir[1]] = distance[current[0]][current[1]] + count;
                dfs(maze, new int[]{x - dir[0], y - dir[1]});
            }
        }
    }


    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        if (maze.length == 0) {
            return 0;
        }

        distance = new int[maze.length][maze[0].length];
        for (int[] row : distance) {
            Arrays.fill(row, INF);
        }
        distance[start[0]][start[1]] = 0;

        dfs(maze, start);

        return this.distance[destination[0]][destination[1]] == INF ? -1
                : this.distance[destination[0]][destination[1]];
    }
}
