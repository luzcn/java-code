package leetcode;

import java.util.LinkedList;
import java.util.Queue;

// There is a ball in a maze with empty spaces and walls.
// The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall.
//
// When the ball stops, it could choose the next direction.
//
// Given the ball's start position, the destination and the maze, determine whether the ball could stop at the destination.
//
// The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space.
// You may assume that the borders of the maze are all walls.
//
// The start and destination coordinates are represented by row and column indexes.
//
public class TheMaze {

    private Queue<int[]> queue = new LinkedList<>();
    private boolean[][] visited;
    private int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};


    // bfs solution, use queue
    private boolean bfs(int[][] maze, int[] start, int[] destination) {
        queue.add(start);
        visited[start[0]][start[1]] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.remove();

            if (current[0] == destination[0] && current[1] == destination[1]) {
                return true;
            }

            for (int[] dir : dirs) {
                int x = current[0] + dir[0];
                int y = current[1] + dir[1];

                while (x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 0) {
                    x += dir[0];
                    y += dir[1];
                }

                if (!visited[x - dir[0]][y - dir[1]]) {
                    visited[x - dir[0]][y - dir[1]] = true;
                    queue.add(new int[]{x - dir[0], y - dir[1]});
                }
            }
        }

        return false;
    }

    private boolean dfs(int[][] maze, int[] current, int[] destination) {
        if (current[0] == destination[0] && current[1] == destination[1]) {
            return true;
        }

        if (visited[current[0]][current[1]]) {
            return false;
        }

        visited[current[0]][current[1]] = true;

        for (int[] dir : dirs) {
            int x = current[0];
            int y = current[1];
            while (x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 0) {
                x += dir[0];
                y += dir[1];
            }

            if (dfs(maze, new int[]{x - dir[0], y - dir[1]}, destination)) {
                return true;
            }
        }

        return false;
    }


    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        if (maze.length == 0) {
            return false;
        }

        visited = new boolean[maze.length][maze[0].length];

        return dfs(maze, start, destination);
    }
}
