package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Remove the minimum number of invalid parentheses in order to make the input string valid.
// Return all possible results.
//
// Note: The input string may contain letters other than the parentheses ( and ).
//
// Examples:
// "()())()" -> ["()()()", "(())()"]
// "(a)())()" -> ["(a)()()", "(a())()"]
///
public class RemoveInvalidParentheses {

  private List<String> result = new ArrayList<>();

  // dfs solution
  // the problem needs to remove minimum parenthesis, so the valid result string should be longest
  // - for each '(' or ')', we can choose use it or not use it
  // - use a variable "open" to keep track if the result string is valid,
  //   if open < 0, we inserted more ) than (, directly return, only open == 0 will be a valid result
  private void dfs(String s, int index, String current, int open) {
    if (open < 0) {
      // inserted more ) than (, invalid
      return;
    }

    // Always save the longest current string
    if (!this.result.isEmpty() && (current.length() + s.length() - index < this.result
        .get(this.result.size() - 1)
        .length())) {
      return;
    }

    if (index == s.length()) {
      if (open == 0 && (this.result.isEmpty() || current.length() == this.result
          .get(this.result.size() - 1)
          .length())) {
        this.result.add(current);
      }

      return;
    }

    char c = s.charAt(index);
    if (c != '(' && c != ')') {
      dfs(s, index + 1, current + c, open);
    } else {
      if (c == '(') {
        // use this (, so open + 1
        dfs(s, index + 1, current + c, open + 1);
      } else {
        // use this ), and open - 1
        dfs(s, index + 1, current + c, open - 1);
      }

      // skip the duplicate parenthesis, i.e. )))) only need to check ) once
      // probably only used to avoid TLE in leetcode
      while (index < s.length() - 1 && s.charAt(index) == s.charAt(index + 1)) {
        index++;
      }

      // or, we can choose do not use this parenthesis
      dfs(s, index + 1, current, open);
    }
  }

  // check if the input string has valid parenthesis
  private boolean isValid(String s) {
    int count = 0;
    for (char c : s.toCharArray()) {
      if (c == '(') {
        count++;
      } else if (c == ')') {
        count--;
      }

      if (count < 0) {
        return false;
      }
    }
    return count == 0;
  }

  // BFS solution
  // when we see minimum/shortest, usually BFS is easier than DFS
  // bfs needs to use a queue to save the candidate data.
  private void bfs(String s) {
    Deque<String> queue = new ArrayDeque<>();
    Set<String> visited = new HashSet<>();
    queue.addLast(s);

    while (!queue.isEmpty()) {
      String current = queue.removeFirst();

      if (visited.contains(current)) {
        continue;
      }

      visited.add(current);

      if (isValid(current)) {
        // always save the longest string
        if (this.result.isEmpty() || current.length() == this.result.get(this.result.size() - 1)
            .length()) {
          result.add(current);
        }
        continue;
      }

      // not valid, we can remove the parenthesis
      for (int i = 0; i < current.length(); i++) {
        char c = current.charAt(i);

        if (c == '(' || c == ')') {
          String candidate = current.substring(0, i) + current.substring(i + 1);

          queue.addLast(candidate);
        }
      }
    }
  }

  public List<String> removeInvalidParentheses(String s) {

    dfs(s, 0, "", 0);
    return this.result;
  }
}
