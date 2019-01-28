package leetcode;

import java.util.*;


// In an alien language, surprisingly they also use english lowercase letters,
// but possibly in a different order.
//
// The order of the alphabet is some permutation of lowercase letters.
//
// Given a sequence of words written in the alien language, and the order of the alphabet,
//
// return true if and only if the given words are sorted lexicographicaly in this alien language.
//
// Example 1:
//
// Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
// Output: true
// Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
//
// Example 2:
//
// Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
// Output: false
// Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
public class VerifyingAlienDictionary_953 {

  public boolean isAlienSorted(String[] words, String order) {

    HashMap<Character, Integer> map = new HashMap<>();

    for (int i = 0; i < order.length(); i++) {
      map.put(order.charAt(i), i);
    }

    for (int index = 0; index < words.length - 1; index++) {
      String word1 = words[index];
      String word2 = words[index + 1];

      int i = 0, j = 0;
      while (i < word1.length() || j < word2.length()) {
        if (i == word1.length()) {
          break;
        }

        if (j == word2.length()) {
          return false;
        }

        char c1 = word1.charAt(i);
        char c2 = word2.charAt(j);

        if (c1 == c2) {
          i++;
          j++;
          continue;
        }

        if (map.get(c1) < map.get(c2)) {
          break;
        } else {
          return false;
        }
      }
    }

    return true;
  }
}
