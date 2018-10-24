package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
//
// The distance between two adjacent cells is 1.
// Example 1:
// Input:
//
// 0 0 0
// 0 1 0
// 0 0 0
// Output:
// 0 0 0
// 0 1 0
// 0 0 0
// Example 2:
// Input:
//
// 0 0 0
// 0 1 0
// 1 1 1
// Output:
// 0 0 0
// 0 1 0
// 1 2 1
// Note:
// The number of elements of the given matrix will not exceed 10,000.
// There are at least one 0 in the given matrix.
// The cells are adjacent in only four directions: up, down, left and right.

public class Matrix01_542 {

  // Thought:
  // we should do BFS from each 0, update the distance to each 1

  public int[][] updateMatrix(int[][] matrix) {

    int m = matrix.length;
    int n = matrix[0].length;

    int[][] distance = new int[m][n];
    for (int[] row : distance) {
      Arrays.fill(row, Integer.MAX_VALUE);
    }

    Queue<int[]> queue = new LinkedList<>();

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (matrix[i][j] == 0) {
          distance[i][j] = 0;
          queue.add(new int[]{i, j});
        }
      }
    }

    // bfs
    // no need to use boolean[][] visited, because comparing the distance can get rid of the visited cell
    while (!queue.isEmpty()) {
      int x = queue.peek()[0];
      int y = queue.peek()[1];

      queue.poll();

      for (int[] dir : dirs) {
        int newX = x + dir[0];
        int newY = y + dir[1];

        if (newX < 0 || newX >= m || newY < 0 || newY >= m || matrix[newX][newY] == 0) {
          continue;
        }

        if (distance[x][y] != Integer.MAX_VALUE && distance[x][y] + 1 < distance[newX][newY]) {
          distance[newX][newY] = distance[x][y] + 1;
          queue.add(new int[]{newX, newY});
        }
      }
    }

    return distance;
  }


  private int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
}
