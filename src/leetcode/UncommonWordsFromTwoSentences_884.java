package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// We are given two sentences A and B.
// (A sentence is a string of space separated words.  Each word consists only of lowercase letters.)
//
// A word is uncommon if it appears exactly once in one of the sentences, and does not appear in the other sentence.
//
// Return a list of all uncommon words.
//
// You may return the list in any order.
//
//
//
// Example 1:
//
// Input: A = "this apple is sweet", B = "this apple is sour"
// Output: ["sweet","sour"]
// Example 2:
//
// Input: A = "apple apple", B = "banana"
// Output: ["banana"]
public class UncommonWordsFromTwoSentences_884 {

  // use hashmap
  public String[] uncommonFromSentences(String A, String B) {
    HashMap<String, Integer> map = new HashMap<>();

    for (String word : A.split(" ")) {
      map.put(word, map.getOrDefault(word, 0) + 1);
    }

    for (String word : B.split(" ")) {
      map.put(word, map.getOrDefault(word, 0) + 1);
    }

    List<String> res = new ArrayList<>();
    for (Map.Entry<String, Integer> entry : map.entrySet()) {
      if (entry.getValue() == 1) {
        res.add(entry.getKey());
      }
    }

    return res.toArray(new String[res.size()]);

  }
}
