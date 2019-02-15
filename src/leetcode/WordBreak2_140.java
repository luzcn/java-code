package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

// Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
// add spaces in s to construct a sentence where each word is a valid dictionary word.
//
// You may assume the dictionary does not contain duplicate words.
//
// Return all such possible sentences.
//
// For example, given
// s = "catsanddog",
// dict = ["cat", "cats", "and", "sand", "dog"].
//
// A solution is ["cats and dog", "cat sand dog"].
public class WordBreak2_140 {

  // dfs + memoization
  private List<String> dfs(String s, List<String> dict, Map<String, List<String>> map) {
    if (map.containsKey(s)) {
      return map.get(s);
    }

    List<String> res = new ArrayList<>();
    if (s.isEmpty()) {
      res.add("");
      return res;
    }

    // here i <= s.length(), don't forget
    for (int i = 0; i < s.length(); i++) {
      String prefix = s.substring(0, i + 1);

      if (dict.contains(prefix)) {
        List<String> subList = dfs(s.substring(i + 1), dict, map);

        for (String sub : subList) {
          res.add(prefix + (sub.isEmpty() ? "" : " ") + sub);
        }
      }
    }

    map.put(s, res);
    return res;
  }


  private List<String> res = new ArrayList<>();
  private HashSet<String> dict;

  // dfs and use dp to prune unnecessary computation
  // it must use "start" index, because access the dp array is using the index
  private void dfsAndDP(String s, int start, List<String> current, boolean[] canBreak) {
    if (start >= s.length()) {
      res.add(String.join(" ", current));
      return;
    }

    for (int i = start; i < s.length(); i++) {
      String prefix = s.substring(start, i + 1);

      if (dict.contains(prefix) && canBreak[i]) {
        current.add(prefix);
        int sizeBeforeChange = res.size();

        dfsAndDP(s, i + 1, current, canBreak);
        current.remove(current.size() - 1);

        if (res.size() == sizeBeforeChange) {
          // no solution found
          canBreak[i] = false;
        }
      }
    }
  }

  public List<String> wordBreak(String s, List<String> wordDict) {
    dict = new HashSet<>(wordDict);
    boolean[] canBreak = new boolean[s.length()];
    Arrays.fill(canBreak, true);

    dfsAndDP(s, 0, new ArrayList<>(), canBreak);

    return res;
  }
}
