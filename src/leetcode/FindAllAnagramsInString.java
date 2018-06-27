package leetcode;

import java.util.*;

// Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
//
// Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
//
// The order of output does not matter.
//
// Example 1:
//
// Input:
// s: "cbaebabacd" p: "abc"
//
// Output:
// [0, 6]
//
// Explanation:
// The substring with start index = 0 is "cba", which is an anagram of "abc".
// The substring with start index = 6 is "bac", which is an anagram of "abc".
//
// Example 2:
// Input:
// s: "abab" p: "ab"
//
// Output:
// [0, 1, 2]
//
// Explanation:
// The substring with start index = 0 is "ab", which is an anagram of "ab".
// The substring with start index = 1 is "ba", which is an anagram of "ab".
// The substring with start index = 2 is "ab", which is an anagram of "ab".
public class FindAllAnagramsInString {

    private boolean isAnagram(String s, String t) {
        int[] chars = new int[26];

        for (char c : s.toCharArray()) {
            chars[c - 'a']++;
        }

        for (char c : t.toCharArray()) {
            chars[c - 'a']--;
        }

        for (int n : chars) {
            if (n != 0) {
                return false;
            }
        }

        return true;
    }

    // brute force solution O(len(s) * len(p)) time
    public List<Integer> findAnagrams(String s, String p) {

        List<Integer> result = new ArrayList<>();
        if (s.isEmpty()) {
            return result;
        }

        for (int i = 0; i <= s.length() - p.length(); i++) {
            if (isAnagram(s.substring(i, i + p.length()), p)) {
                result.add(i);
            }
        }

        return result;
    }
}
