package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of
 * shortest transformation sequence from beginWord to endWord, such that:
 *
 * Only one letter can be changed at a time. Each transformed word must exist in the word list. Note
 * that beginWord is not a transformed word. Note:
 *
 * Return 0 if there is no such transformation sequence. - All words have the same length. - All
 * words contain only lowercase alphabetic characters. - You may assume no duplicates in the word
 * list. - You may assume beginWord and endWord are non-empty and are not the same.
 *
 * Example 1:
 *
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 *
 * Output: 5
 *
 * Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog", return
 * its length 5.
 */
public class WordLadder {

  // when we see shortest/minimum, usually we can use bfs
  public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    Set<String> wordSet = new HashSet<>(wordList);

    // use a queue to save the candidate words
    Queue<String> candidates = new LinkedList<>();
    candidates.add(beginWord);

    // a hash map use to save the distance and check if the word has been visited
    Map<String, Integer> map = new HashMap<>();

    // put the start word into the map and set distance as 1
    map.put(beginWord, 1);

    while (!candidates.isEmpty()) {

      String current = candidates.poll();

      for (int i = 0; i < current.length(); i++) {
        for (char c = 'a'; c <= 'z'; c++) {
          String newWord = current.substring(0, i) + c + current.substring(i + 1);

          if (newWord.equals(endWord)) {
            return map.get(current) + 1;
          }

          if (wordSet.contains(newWord) && !map.containsKey(newWord)) {
            candidates.add(newWord);
            map.put(newWord, map.get(current) + 1);
          }
        }
      }
    }

    return 0;
  }
}
