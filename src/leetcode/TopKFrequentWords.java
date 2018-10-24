package leetcode;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

// Given a non-empty list of words, return the k most frequent elements.
//
// Your answer should be sorted by frequency from highest to lowest.
// If two words have the same frequency, then the word with the lower alphabetical order comes first.
//
// Example 1:
// Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
// Output: ["i", "love"]
// Explanation: "i" and "love" are the two most frequent words.
// Note that "i" comes before "love" due to a lower alphabetical order.
//
// Example 2:
// Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
// Output: ["the", "is", "sunny", "day"]
// Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
// with the number of occurrence being 4, 3, 2 and 1 respectively.
//
// Note:
// You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
// Input words contain only lowercase letters.
// Follow up:
// Try to solve it in O(n log k) time and O(n) extra space.
///
public class TopKFrequentWords {

  public List<String> topKFrequent(String[] words, int k) {
    List<String> result = new LinkedList<>();
    Map<String, Integer> map = new HashMap<>();
    PriorityQueue<String> minHeap = new PriorityQueue<>((w1, w2) -> {
      if (map.get(w1).equals(map.get(w2))) {
        // return 0 - w1.compareTo(w2);
        return w2.compareTo(w1);
      }

      return map.get(w1) - map.get(w2);
    });

    // count the frequency
    for (String w : words) {
      map.put(w, map.getOrDefault(w, 0) + 1);
    }

    for (String word : map.keySet()) {
      minHeap.offer(word);

      if (minHeap.size() > k) {
        minHeap.poll();
      }
    }

    while (!minHeap.isEmpty()) {
      result.add(minHeap.poll());
    }

    Collections.reverse(result);
    return result;
  }
}
