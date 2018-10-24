package leetcode;

import java.util.Collections;
import java.util.HashSet;

// Given a set of keywords words and a string S, make all appearances of all keywords in S bold.
// Any letters between <b> and </b> tags become bold.
//
// The returned string should use the least number of tags possible, and of course the tags should form a valid combination.
//
// For example, given that words = ["ab", "bc"] and S = "aabcd", we should return "a<b>abc</b>d".
// Note that returning "a<b>a<b>b</b>c</b>d" would use more tags, so it is incorrect.
//
// Note:
//
// - words has length in range [0, 50].
// - words[i] has length in range [1, 10].
// - S has length in range [0, 500].
// - All characters in words[i] and S are lowercase letters.
public class BoldWordsInString_758 {

  public String boldWords(String[] words, String s) {
    int n = s.length();

    // use a helper array to indicate which chars need to be bold
    boolean[] bold = new boolean[n];

    HashSet<String> keyWords = new HashSet<>();
    Collections.addAll(keyWords, words);

    // all the substring
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j <= n; j++) {
        String sub = s.substring(i, j);
        if (keyWords.contains(sub)) {
          for (int k = i; k < j; k++) {
            bold[k] = true;
          }
        }
      }
    }

    StringBuilder res = new StringBuilder();
    int i = 0;

    while (i < n) {

      // wrap the bold chars in <b>...</b>
      if (bold[i]) {
        res.append("<b>");

        while (i < n && bold[i]) {
          res.append(s.charAt(i));
          i++;
        }
        res.append("</b>");
      } else {
        res.append(s.charAt(i));
        i++;
      }
    }

    return res.toString();
  }
}
