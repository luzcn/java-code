package leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

// A move consists of taking a point (x, y) and transforming it to either (x, x+y) or (x+y, y).
//
// Given a starting point (sx, sy) and a target point (tx, ty),
// return True if and only if a sequence of moves exists to transform the point (sx, sy) to (tx, ty).
// Otherwise, return False.
//
// Examples:
// Input: sx = 1, sy = 1, tx = 3, ty = 5
// Output: True
// Explanation:
// One series of moves that transforms the starting point to the target is:
// (1, 1) -> (1, 2)
// (1, 2) -> (3, 2)
// (3, 2) -> (3, 5)
//
// Input: sx = 1, sy = 1, tx = 2, ty = 2
// Output: False
//
// Input: sx = 1, sy = 1, tx = 1, ty = 1
// Output: True
public class ReachingPoints_780 {

  public boolean reachingPoints(int sx, int sy, int tx, int ty) {

    return bfs(new int[]{sx, sy}, new int[]{tx, ty});
  }

  // time: O(tx*ty)
  private boolean bfs(int[] start, int[] end) {
    HashSet<int[]> set = new HashSet<>();

    Queue<int[]> queue = new LinkedList<>();
    queue.add(start);

    while (!queue.isEmpty()) {
      int[] current = queue.poll();

      if (set.contains(current)) {
        continue;
      }

      if (current[0] > end[0] || current[1] > end[1]) {
        return false;
      }

      set.add(current);
      if (current[0] == end[0] && current[1] == end[1]) {
        return true;
      }

      int[] next1 = new int[]{current[0] + current[1], current[1]};
      int[] next2 = new int[]{current[0], current[0] + current[1]};

      queue.add(next1);
      queue.add(next2);
    }

    return false;
  }

  // time: O(tx*ty)
  private boolean dfs(int[] node, int[] end, HashSet<int[]> set) {
    if (set.contains(node)) {
      return false;
    }

    if (node[0] > end[0] || node[1] > end[1]) {
      return false;
    }

    if (node[0] == end[0] && node[1] == end[1]) {
      return true;
    }

    set.add(node);

    return dfs(new int[]{node[0] + node[1], node[1]}, end, set) || dfs(
        new int[]{node[0], node[0] + node[1]}, end, set);
  }


  // For a point (x, y), it can only be reached from (x -y, y) or (x, y - x)
  // so using backwards search
  // - if x > y, then move backward to (x - y, y)
  // - otherwise, move to (x, y - x)
  // O(max(tx,ty))
  private boolean backwardsMove(int sx, int sy, int tx, int ty) {
    while (tx >= sx && ty >= sy) {

      if (tx == sx && ty == sy) {
        return true;
      }

      if (tx > ty) {
        tx -= ty;
      } else {
        ty -= tx;
      }
    }

    return false;
  }


  // O(log(max(tx, ty))
  private boolean backwardsMove2(int sx, int sy, int tx, int ty) {

    while (tx >= sx && ty >= sy) {
      if (tx == ty) {
        break;
      }

      //  When both tx > ty and ty > sy,
      //  we can perform all these parent operations in one step,
      //  replacing "while tx > ty: tx -= ty" with tx %= ty.
      if (tx > ty) {
        if (ty > sy) {
          tx %= ty;
        } else {
          // otherwise, ty == sy
          // now, we need to check if the distance tx-sx is the multiple times of ty.
          return (tx - sx) % ty == 0;
        }
      } else {

        if (tx > sx) {
          ty %= tx;
        } else {
          return (ty - sy) % tx == 0;
        }
      }
    }
    return (tx == sx && ty == sy);
  }
}
