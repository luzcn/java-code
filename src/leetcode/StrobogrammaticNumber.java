package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
//
// Write a function to determine if a number is strobogrammatic. The number is represented as a string.
//
// Example 1:
//
// Input:  "69"
// Output: true
// Example 2:
//
// Input:  "88"
// Output: true
// Example 3:
//
// Input:  "962"
// Output: false
public class StrobogrammaticNumber {

  private HashMap<Character, Character> map = new HashMap<>();

  public boolean isStrobogrammatic(String num) {

    map.put('0', '0');
    map.put('1', '1');
    map.put('6', '9');
    map.put('9', '6');
    map.put('8', '8');

    int begin = 0;
    int end = num.length() - 1;

    while (begin <= end) {
      char c1 = num.charAt(begin);
      char c2 = num.charAt(end);

      if (map.get(c1) == null || map.get(c2) == null || map.get(c1) != c2) {
        return false;
      }
      begin++;
      end--;
    }

    return true;
  }

  // 247. Strobogrammatic Number II
  // find all Strobogrammatic Number with length n
  public List<String> findStrobogrammatic(int n) {

    // dfs
    List<String> res = new ArrayList<>();

    dfs(n, res, "");
    dfs(n, res, "1");
    dfs(n, res, "0");
    dfs(n, res, "8");

    return res;
  }

  private void dfs(int n, List<String> res, String current) {
    if (current.length() > n) {
      return;
    }

    if (current.length() == n) {

      // 010 is not valid, but 0 or 101 is ok
      if (current.length() > 1 && current.charAt(0) == '0') {
        return;
      }

      res.add(current);
      return;
    }

    dfs(n, res, "0" + current + "0");
    dfs(n, res, "1" + current + "1");
    dfs(n, res, "6" + current + "9");
    dfs(n, res, "9" + current + "6");
    dfs(n, res, "8" + current + "8");
  }

  // 248. Strobogrammatic Number III
  // Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.
  private int res = 0;

  public int strobogrammaticInRange(String low, String high) {

    for (int i = low.length(); i <= high.length(); i++) {
      find(low, high, "", i);
      find(low, high, "0", i);
      find(low, high, "1", i);
      find(low, high, "8", i);
    }

    return res;
  }

  private void find(String low, String high, String path, int len) {
    if (path.length() > len) {
      return;
    }

    if (path.length() == len) {

      // 010, 00 not valid
      if (path.length() > 1 && path.charAt(0) == '0') {
        return;
      }

      // path < low or path > high, out of range
      if ((len == low.length() && path.compareTo(low) < 0) || (len == high.length()
          && path.compareTo(high) > 0)) {
        return;
      }

      res++;
    }

    find(low, high, "0" + path + "0", len);
    find(low, high, "1" + path + "1", len);
    find(low, high, "6" + path + "9", len);
    find(low, high, "9" + path + "6", len);
    find(low, high, "8" + path + "8", len);
  }
}
