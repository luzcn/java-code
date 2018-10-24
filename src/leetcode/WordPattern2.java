package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordPattern2 {


  private Map<Character, String> map = new HashMap<>();
  private Set<String> set = new HashSet<>();


  private boolean dfs(String pattern, String str, int start, int count) {

    if (count == pattern.length() && start == str.length()) {
      return true;
    }

    if (count >= pattern.length() || start >= str.length()) {
      return false;
    }

    char c = pattern.charAt(count);

    // if we already have set the pattern character with some string s
    // check if we can find any substring which can match this s
    if (map.containsKey(c)) {
      String s = map.get(c);

      if (start + s.length() < str.length() && !s
          .equals(str.substring(start, start + s.length()))) {
        return false;
      }

      return dfs(pattern, str, start + s.length(), count + 1);
    }

    for (int i = start; i < str.length(); i++) {
      String s = str.substring(start, i + 1);

      // here, the pattern "c" must have no string assigned yet,
      // if string s has been assigned to some pattern,
      //  this s is not a valid candicate, keep searching.
      if (set.contains(s)) {
        continue;
      }

      set.add(s);
      map.put(c, s);

      if (dfs(pattern, str, i + 1, count + 1)) {
        return true;
      }

      set.remove(s);
      map.remove(c);
    }

    return false;
  }

  public boolean wordPatternMatch(String pattern, String str) {
    if (pattern.isEmpty()) {
      return str.isEmpty();
    }

    return dfs(pattern, str, 0, 0);
  }
}
