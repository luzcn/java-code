package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed
 * parentheses.
 *
 * For example, given n = 3, a solution set is:
 *
 * [ "((()))", "(()())", "(())()", "()(())", "()()()" ]
 */
public class GenerateParentheses {

  private void dfs(int l, int r, List<String> result, String current) {
    if (l > r) {
      return;
    }

    if (l == 0 && r == 0) {
      result.add(current);
      return;
    }

    if (l > 0) {
      dfs(l - 1, r, result, current + "(");
    }

    if (r > 0) {
      dfs(l, r - 1, result, current + ")");
    }
  }

  public List<String> generateParenthesis(int n) {
    List<String> result = new ArrayList<>();

    dfs(n, n, result, "");
    return result;
  }
}
