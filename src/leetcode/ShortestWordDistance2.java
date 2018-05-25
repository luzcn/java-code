package leetcode;

import java.util.*;

// Design a class which receives a list of words in the constructor,
// and implements a method that takes two words word1 and word2 and return the shortest distance between these two words in the list.
//
// Your method will be called repeatedly many times with different parameters.
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
public class ShortestWordDistance2 {

    private HashMap<String, List<Integer>> map = new HashMap<>();

    public ShortestWordDistance2(String[] words) {

        for (int i = 0; i < words.length; i++) {
            map.computeIfAbsent(words[i], k -> new ArrayList<>()).add(i);
        }
    }

    public int shortest(String word1, String word2) {
        int dis = Integer.MAX_VALUE;

        for (int i : map.get(word1)) {
            for (int j : map.get(word2)) {
                dis = Math.min(dis, Math.abs(i - j));
            }
        }

        return dis;
    }



}
