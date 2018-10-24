package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given a string, sort it in decreasing order based on the frequency of characters.
 *
 * Example 1:
 *
 * Input: "tree"
 *
 * Output: "eert"
 *
 * Explanation: 'e' appears twice while 'r' and 't' both appear once. So 'e' must appear before both
 * 'r' and 't'. Therefore "eetr" is also a valid answer. Example 2:
 *
 * Input: "cccaaa"
 *
 * Output: "cccaaa"
 *
 * Explanation: Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer. Note that
 * "cacaca" is incorrect, as the same characters must be together. Example 3:
 *
 * Input: "Aabb"
 *
 * Output: "bbAa"
 *
 * Explanation: "bbaA" is also a valid answer, but "Aabb" is incorrect. Note that 'A' and 'a' are
 * treated as two different characters.
 */
public class SortCharactersByFrequency {

  public String frequencySort(String s) {
    if (s == null || s.isEmpty()) {
      return "";
    }

    Map<Character, Integer> mapFrequency = new HashMap<>();

    // Character[] chars = new Character[s.length()];
    // for (int i = 0; i < chars.length; i++)
    //     chars[i] = s.charAt(i);
    Character[] chars = s.chars().mapToObj(c -> (char) c).toArray(Character[]::new);

    for (Character c : chars) {
      mapFrequency.put(c, mapFrequency.getOrDefault(c, 0) + 1);
    }

    Arrays.sort(chars, (n1, n2) -> {
      // comparing object, must use equals function
      if (mapFrequency.get(n2).equals(mapFrequency.get(n1))) {
        return n1.compareTo(n2);
      } else {
        return mapFrequency.get(n2) - mapFrequency.get(n1);
      }
    });

    StringBuilder sb = new StringBuilder();

    for (Character c : chars) {
      sb.append(c);
    }

    return sb.toString();
  }
}
