package leetcode;

import java.util.List;

/**
 * Given a string and a string dictionary,
 * find the longest string in the dictionary that can be formed by deleting some characters of the given string.
 *
 * If there are more than one possible results, return the longest word with the smallest lexicographical order.
 *
 * If there is no possible result, return the empty string.
 *
 * Example 1:
 * Input:
 * s = "abpcplea", d = ["ale","apple","monkey","plea"]
 *
 * Output:
 * "apple"
 *
 * Example 2:
 * Input:
 * s = "abpcplea", d = ["a","b","c"]
 *
 * Output:
 * "a"
 */

// Thoughts:
// - Using Trie + dfs is TLE
// build ing trie takes O(n * m) where n is the words list size and m is the longest string length in the words list
// trie is tree structure, assume there are totally k nodes in the trie
// dfs take O( s.size() * 2^k) time
//
// Actually, we don't need trie here.
// the problem doesn't say after deleting a character the rest of the input sting s needs to be int he dictionary
// so we can sort the input words list based on the string length and alphabetic order
// then check if any string in the words list is a sub-sequence of the given string s
// we can use two pointer idea to check if the string t is a sub-sequence of string s, it takes O(s + t) where s, t indicates string length
// the sor takes O(nlongn)
public class LongestWordInDictionaryThroughDeleting {

    // check if string s is a sub-sequence of string t
    // it means all characters s also appears in t and keeps the order in s
    private boolean isSubSequence(String s, String t) {
        if (s.isEmpty()) {
            return t.isEmpty();
        }

        int i = 0;
        int j = 0;

        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }

        return i == s.length();
    }

    public String findLongestWord(String s, List<String> words) {

        // sort the input words list based on the string length and alphabetic order
        words.sort((x, y) -> {
            if (x.length() == y.length()) {
                return x.compareTo(y);
            } else {
                return y.length() - x.length();
            }
        });

        // words.forEach(System.out::println);


        for (String w : words) {
            if (isSubSequence(w, s)) {
                return w;
            }
        }
        return "";
    }
}

// public class LongestWordInDictionaryThroughDeleting {
//
//     private TrieNode root = new TrieNode();
//     private String longestWord = "";
//
//     private void buildTrie(List<String> words) {
//
//         for (String word : words) {
//             TrieNode current = this.root;
//
//             for (char c : word.toCharArray()) {
//                 int index = c - 'a';
//
//                 if (current.children[index] == null) {
//                     current.children[index] = new TrieNode();
//                 }
//
//                 current = current.children[index];
//             }
//             current.wordCount++;
//         }
//     }
//
//     private void dfs(TrieNode node, String s, int index, String current) {
//
//         if (node == null) {
//             return;
//         }
//
//         if (node.wordCount > 0) {
//             if (current.length() > longestWord.length() || (current.length() == longestWord.length()
//                     && current.compareTo(longestWord) < 0)) {
//                 longestWord = current;
//             }
//         }
//
//         if (index >= s.length()) {
//             return;
//         }
//
//         char c = s.charAt(index);
//
//         if (node.children[c - 'a'] != null) {
//             dfs(node.children[c - 'a'], s, index + 1, current + c);
//         } else {
//             dfs(node, s, index + 1, current);
//         }
//     }
//
//     public String findLongestWord(String s, List<String> words) {
//         if (s == null || s.length() == 0 || words == null || words.size() == 0) {
//             return "";
//         }
//
//         // build the trie
//         this.buildTrie(words);
//
//         for (int i = 0; i < s.length(); i++) {
//             if (s.length() - i < longestWord.length()) {
//                 break;
//             }
//
//             this.dfs(root, s, i, "");
//         }
//
//         return this.longestWord;
//     }
//
//
//     private class TrieNode {
//
//         int wordCount;
//         TrieNode[] children;
//
//         TrieNode() {
//             wordCount = 0;
//             children = new TrieNode[26];
//         }
//     }
// }
