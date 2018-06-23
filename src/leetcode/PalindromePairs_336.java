package leetcode;

import java.util.*;

// Given a list of unique words, find all pairs of distinct indices (i, j) in the given list,
// so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.
//
// Example 1:
// Given words = ["bat", "tab", "cat"]
// Return [[0, 1], [1, 0]]
// The palindromes are ["battab", "tabbat"]
// Example 2:
// Given words = ["abcd", "dcba", "lls", "s", "sssll"]
// Return [[0, 1], [1, 0], [3, 2], [2, 4]]
// The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]
public class PalindromePairs_336 {

    public List<List<Integer>> palindromePairs(String[] words) {
        HashMap<String, Integer> map = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }

        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {

            for (int j = 0; j <= words[i].length(); j++) {

                // check all the substrings, include the entire word itself
                String first = words[i].substring(0, j);
                String second = words[i].substring(j);

                // if the prefix is a palindrome, check if the reverse of the suffix substring exist
                if (isPalindrome(first)) {

                    String rev = (new StringBuilder(second)).reverse().toString();
                    if (map.get(rev) != null) {
                        // prefix is palindrome, need to make the string as rev + word, so add the rev index first
                        res.add(Arrays.asList(map.get(rev), i));
                    }
                }

                // similarly, if the suffix is palindrome, check if the reverse of the prefix substring exist
                // the prefix could be the entire word string
                if (second.length() > 0 && isPalindrome(second)) {
                    String rev = (new StringBuilder(first)).reverse().toString();
                    if (map.get(rev) != null) {
                        res.add(Arrays.asList(i, map.get(rev)));
                    }
                }
            }
        }

        return res;
    }


    private boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;

        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }

        return true;
    }

    // O(n^2) TLE
    public List<List<Integer>> palindromePairsBruteForce(String[] words) {

        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) {
                if (i == j) {
                    continue;
                }

                if (isPalindrome(words[i] + words[j])) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }

        return res;
    }
}
