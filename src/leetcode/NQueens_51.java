package leetcode;

import java.util.ArrayList;
import java.util.List;

public class NQueens_51 {

  private List<List<String>> res = new ArrayList<>();
  private int n;

  private int[] rows;

  public List<List<String>> solveNQueens(int n) {

    rows = new int[n];
    this.n = n;

    dfs(0);
    return res;
  }


  private void dfs(int index) {

    if (index >= n) {

      List<String> currentSolution = new ArrayList<>();
      for (int i = 0; i < n; i++) {
        char[] current = new char[n];
        for (int j = 0; j < n; j++) {
          if (j == rows[i]) {
            current[j] = 'Q';
          } else {
            current[j] = '.';
          }
        }
        currentSolution.add(new String(current));
      }

      this.res.add(currentSolution);
      return;
    }

    for (int value = 0; value < n; value++) {
      rows[index] = value;
      if (isValid(index)) {
        dfs(index + 1);
      }
    }
  }


  private boolean isValid(int index) {

    for (int i = 0; i < index; i++) {
      int diff = Math.abs(rows[i] - rows[index]);

      if (diff == 0 || diff == index - i) {
        return false;
      }
    }
    return true;
  }
}
