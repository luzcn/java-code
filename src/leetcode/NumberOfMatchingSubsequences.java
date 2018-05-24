package leetcode;

import java.util.*;

// Given string S and a dictionary of words words, find the number of words[i] that is a subsequence of S.
//
// Example :
// Input:
// S = "abcde"
// words = ["a", "bb", "acd", "ace"]
// Output: 3
// Explanation: There are three words in words that are a subsequence of S: "a", "acd", "ace".
// Note:
//
// All words in words and S will only consists of lowercase letters.
// The length of S will be in the range of [1, 50000].
// The length of words will be in the range of [1, 5000].
// The length of words[i] will be in the range of [1, 50].
public class NumberOfMatchingSubsequences {


    // the string S could be very long
    // brute force solution is TLE
    public int numMatchingSubseq(String S, String[] words) {

        if (S.isEmpty() || words.length == 0) {
            return 0;
        }

        int res = 0;
        // consider only 'a'..'z' 26 characters
        HashMap<Character, List<Data>> buckets = new HashMap<>();
        for (char c = 'a'; c <= 'z'; c++) {
            buckets.put(c, new ArrayList<>());
        }

        // add all words into the bucket
        for (String w : words) {
            buckets.get(w.charAt(0)).add(new Data(w, 0));
        }

        for (char c : S.toCharArray()) {
            List<Data> currentBucket = new ArrayList<>(buckets.get(c));

            // all words in current bucket will be move to other buckets
            // clear the current bucket data
            buckets.get(c).clear();

            for (Data node : currentBucket) {

                // move to next character pointer
                node.index++;

                if (node.index == node.word.length()) {
                    res++;
                } else {

                    // move this node to another bucket which start as node[word[index]] character
                    buckets.get(node.word.charAt(node.index)).add(node);
                }
            }
        }

        return res;
    }


    private class Data {

        int index;
        String word;

        Data(String w, int i) {
            word = w;
            index = i;
        }
    }

    // https://leetcode.com/problems/is-subsequence/description/
    // O(m) time, O(1) space
    private boolean isSubsequence(String s, String t) {
        if (s == null || s.length() == 0) {
            return true;
        }

        int i = 0, j = 0;

        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }

            j++;
        }

        return i == s.length();
    }
}
