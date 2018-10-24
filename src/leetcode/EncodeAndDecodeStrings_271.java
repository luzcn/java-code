package leetcode;

import java.util.ArrayList;
import java.util.List;

// Design an algorithm to encode a list of strings to a string.
// The encoded string is then sent over the network and is decoded back to the original list of strings.
public class EncodeAndDecodeStrings_271 {

  // Encodes a list of strings to a single string.
  public String encode(List<String> strs) {
    StringBuilder res = new StringBuilder();

    for (String s : strs) {
      res.append("#").append(s.length()).append("#").append(s);
    }

    return res.toString();
  }

  // Decodes a single string to a list of strings.
  public List<String> decode(String s) {

    List<String> res = new ArrayList<>();
    if (s.isEmpty() || s.charAt(0) != '#') {
      return res;
    }

    int i = 0;
    while (i < s.length()) {
      int num = 0;

      if (s.charAt(i) == '#') {
        i++;
      }

      while (i < s.length() && Character.isDigit(s.charAt(i))) {
        num = num * 10 + Character.getNumericValue(s.charAt(i));
        i++;
      }

      if (i < s.length() && s.charAt(i) == '#') {
        i++;
      }

      res.add(s.substring(i, i + num));

      i += num;
    }

    return res;
  }
}
