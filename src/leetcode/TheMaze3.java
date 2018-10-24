package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// There is a ball in a maze with empty spaces and walls.
// The ball can go through empty spaces by rolling up (u), down (d), left (l) or right (r),
// but it won't stop rolling until hitting a wall.
//
// When the ball stops, it could choose the next direction.
// There is also a hole in this maze.
// The ball will drop into the hole if it rolls on to the hole.
//
// Given the ball position, the hole position and the maze,
// find out how the ball could drop into the hole by moving the shortest distance.
//
// The distance is defined by the number of empty spaces traveled by the ball from the start position
// (excluded) to the hole (included). Output the moving directions by using 'u', 'd', 'l' and 'r'.
//
// Since there could be several different shortest ways, you should output the lexicographically smallest way.
// If the ball cannot reach the hole, output "impossible".
//
// The maze is represented by a binary 2D array.
// 1 means the wall and 0 means the empty space.
//
// You may assume that the borders of the maze are all walls.
// The ball and the hole coordinates are represented by row and column indexes.
public class TheMaze3 {

  // left, right, up, down
  private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
  private int[][] distance;
  private int shortestDistance;
  private String res;
  private int[] hole;

  // 1. bfs find the shortest distance from ball to hole
  // 2. dfs to get the path
  public String findShortestWay(int[][] maze, int[] ball, int[] hole) {

    this.res = "";
    this.hole = hole;

    dfs(maze, ball, "");

    return this.res;
  }

  private void dfs(int[][] maze, int[] current, String path) {
    if (current[0] < 0 || current[0] >= maze.length || current[1] < 0
        || current[1] >= maze[0].length
        || maze[current[0]][current[1]] == 1) {
      return;
    }

    for (int i = 0; i < dirs.length; i++) {

      String direction;
      if (i == 0) {
        direction = "L";
      } else if (i == 1) {
        direction = "R";
      } else if (i == 2) {
        direction = "U";
      } else {
        direction = "D";
      }

      int x = current[0] + dirs[i][0];
      int y = current[1] + dirs[i][1];
      int count = 0;

      while (x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 0) {
        x += dirs[i][0];
        y += dirs[i][1];
        count++;
      }

      if (distance[current[0]][current[1]] + count < distance[x - dirs[i][0]][y - dirs[i][1]]) {
        distance[x - dirs[i][0]][y - dirs[i][1]] = distance[current[0]][current[1]] + count;

        if (x - dirs[i][0] == this.hole[0] && y - dirs[i][1] == this.hole[1]) {
          this.res = path + direction;
        }

        dfs(maze, new int[]{x - dirs[i][0], y - dirs[i][1]}, path + direction);
      }
    }
  }

  private void bfs(int[][] maze, int[] ball) {
    // get shortest distance
    int m = maze.length;
    int n = maze[0].length;

    distance = new int[m][n];

    for (int[] di : distance) {
      Arrays.fill(di, Integer.MAX_VALUE);
    }

    Queue<int[]> candidates = new LinkedList<>();
    distance[ball[0]][ball[1]] = 0;

    candidates.add(ball);

    while (!candidates.isEmpty()) {
      int[] current = candidates.poll();

      for (int[] dir : dirs) {
        int newX = current[0] + dir[0];
        int newY = current[1] + dir[1];
        int count = 0;

        while (newX >= 0 && newX < m && newY >= 0 && newY < n && maze[newX][newY] == 0) {
          newX += dir[0];
          newY += dir[1];
          count++;
        }

        if (distance[current[0]][current[1]] + count < distance[newX - dir[0]][newY - dir[1]]) {
          distance[newX - dir[0]][newY - dir[1]] = distance[current[0]][current[1]] + count;

          candidates.add(new int[]{newX - dir[0], newY - dir[1]});
        }
      }
    }
  }
}
