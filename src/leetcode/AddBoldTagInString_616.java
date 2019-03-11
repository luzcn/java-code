package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

// Given a string s and a list of strings dict,
// you need to add a closed pair of bold tag <b> and </b> to wrap the substrings in s that exist in dict.
//
// If two such substrings overlap, you need to wrap them together by only one pair of closed bold tag.
// Also, if two substrings wrapped by bold tags are consecutive, you need to combine them.
//
// Example 1:
// Input:
// s = "abcxyz123"
// dict = ["abc","123"]
// Output:
// "<b>abc</b>xyz<b>123</b>"
// Example 2:
// Input:
// s = "aaabbcc"
// dict = ["aaa","aab","bc"]
// Output:
// "<b>aaabbc</b>c"
public class AddBoldTagInString_616 {

  public String addBoldTag(String s, String[] dict) {

    // brute force

    HashSet<String> set = new HashSet<>();
    Collections.addAll(set, dict);

    List<int[]> bolds = new ArrayList<>();

    for (int i = 0; i < s.length(); i++) {
      for (int j = i + 1; j <= s.length(); j++) {
        String sub = s.substring(i, j);

        if (set.contains(sub)) {
          bolds.add(new int[]{i, j});
        }
      }
    }

    // merge interval
    Collections.sort(bolds, (x, y) -> x[0] - y[0]);
    List<int[]> mergedBolds = mergeInterval(bolds);

    HashSet<Integer> openSet = new HashSet<>();
    HashSet<Integer> closeSet = new HashSet<>();
    for (int[] bold : mergedBolds) {
      openSet.add(bold[0]);
      closeSet.add(bold[1]);
    }

    StringBuilder res = new StringBuilder();

    for (int i = 0; i < s.length(); i++) {
      if (openSet.contains(i)) {
        res.append("<b>").append(s.charAt(i));
      } else if (closeSet.contains(i)) {
        res.append("</b>").append(s.charAt(i));
      } else {
        res.append(s.charAt(i));
      }
    }
    if (closeSet.contains(s.length())) {
      res.append("</b>");
    }

    return res.toString();
  }

  private List<int[]> mergeInterval(List<int[]> intervals) {
    List<int[]> res = new ArrayList<>();

    if (intervals.isEmpty()) {
      return res;
    }

    int[] first = intervals.get(0);
    for (int i = 1; i < intervals.size(); i++) {
      int[] current = intervals.get(i);

      if (isOverlap(first, current)) {

        first = new int[]{Math.min(first[0], current[0]), Math.max(first[1], current[1])};
      } else {
        res.add(first);
        first = current;
      }
    }

    res.add(first);
    return res;
  }

  private boolean isOverlap(int[] l1, int[] l2) {
    return l1[1] >= l2[0] && l2[1] >= l1[0];

  }
}
