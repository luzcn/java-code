package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
//
// Example:
// Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
//
// Input: word1 = “coding”, word2 = “practice”
// Output: 3
// Input: word1 = "makes", word2 = "coding"
// Output: 1
// Note:
// - You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
public class ShortestWordDistance {

  // brute force solution
  public int shortestDistanceBF(String[] words, String word1, String word2) {

    HashMap<String, List<Integer>> map = new HashMap<>();

    for (int i = 0; i < words.length; i++) {
      String w = words[i];

      map.computeIfAbsent(w, k -> new ArrayList<>()).add(i);
    }
    int dis = word1.length();
    for (int i : map.get(word1)) {
      for (int j : map.get(word2)) {
        dis = Math.min(dis, Math.abs(i - j));
      }
    }

    return dis;
  }

  // one pass solution
  // each time if we found index of two input strings, compute the distance
  public int shortestDistance(String[] words, String word1, String word2) {

    int index1 = -1;
    int index2 = -1;
    int dis = words.length;

    for (int i = 0; i < words.length; i++) {

      if (words[i].equals(word1)) {
        index1 = i;
      }

      if (words[i].equals(word2)) {
        index2 = i;
      }

      if (index1 != -1 && index2 != -1) {
        dis = Math.min(dis, Math.abs(index1 - index2));
      }
    }

    return dis;
  }

  // 245. Shortest Word Distance III
  // Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
  //
  // word1 and word2 may be the same and they represent two individual words in the list.
  //
  // Example:
  // Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
  //
  // Input: word1 = “makes”, word2 = “coding”
  // Output: 1
  // Input: word1 = "makes", word2 = "makes"
  // Output: 3
  // Note:
  // You may assume word1 and word2 are both in the list.
  public int shortestWordDistance3(String[] words, String word1, String word2) {
    int index1 = -1;
    int index2 = -1;
    int dis = words.length;
    boolean flip = true;

    for (int i = 0; i < words.length; i++) {

      if (word1.equals(word2)) {

        if (words[i].equals(word1)) {
          if (flip) {
            index1 = i;
          } else {
            index2 = i;
          }
          flip = !flip;
        }

      } else {
        if (words[i].equals(word1)) {
          index1 = i;
        }

        if (words[i].equals(word2)) {
          index2 = i;
        }
      }

      if (index1 != -1 && index2 != -1) {
        dis = Math.min(dis, Math.abs(index1 - index2));
      }
    }

    return dis;
  }
}
