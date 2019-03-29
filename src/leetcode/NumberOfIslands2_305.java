package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// A 2d grid map of m rows and n columns is initially filled with water.
// We may perform an "addLand" operation which turns the water at position (row, col) into a land.
//
// Given a list of positions to operate, count the number of islands after each addLand operation.
//
// An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
// You may assume all four edges of the grid are all surrounded by water.
//
// Example:
//
// Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
// Output: [1,1,2,3]
public class NumberOfIslands2_305 {

  public List<Integer> numIslands2(int m, int n, int[][] positions) {
    List<Integer> res = new ArrayList<>();

    int[][] board = new int[m][n];
    int count = 0;

    for (int[] pos : positions) {
      int x = pos[0];
      int y = pos[1];
      board[x][y] = 1;
      count++;

      int id = x * n + y;

      map.putIfAbsent(id, id);

      for (int[] dir : dirs) {
        int i = x + dir[0];
        int j = y + dir[1];

        if (i < 0 || i >= m || j < 0 || j >= n) {
          continue;
        }

        if (board[i][j] == 1) {
          int neighborRoot = getRootId(i * n + j);

          if (neighborRoot != getRootId(id)) {
            count--;
            map.put(getRootId(id), neighborRoot);
          }
        }
      }


      res.add(count);
    }

    return res;
  }

  private HashMap<Integer, Integer> map = new HashMap<>();
  private int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};


  private int getRootId(int id) {

    int i = id;
    while (map.get(i) != i) {
      i = map.get(i);
    }

    return i;
  }
}
