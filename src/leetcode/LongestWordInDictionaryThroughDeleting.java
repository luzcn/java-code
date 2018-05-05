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
public class LongestWordInDictionaryThroughDeleting {

    private TrieNode root = new TrieNode();
    private String longestWord = "";

    private void buildTrie(List<String> words) {

        for (String word : words) {
            TrieNode current = this.root;

            for (char c : word.toCharArray()) {
                int index = c - 'a';

                if (current.children[index] == null) {
                    current.children[index] = new TrieNode();
                }

                current = current.children[index];
            }
            current.wordCount++;
        }
    }

    private void dfs(TrieNode node, String s, int index, String current) {

        if (node == null) {
            return;
        }

        if (node.wordCount > 0 && current.length() >= this.longestWord.length()) {

            if (current.length() > this.longestWord.length()) {
                this.longestWord = current;
            } else if (current.compareTo(this.longestWord) < 0){
                this.longestWord = current;
            }
        }

        if (index >= s.length()) {
            return;
        }

        char c = s.charAt(index);

        if (node.children[c - 'a'] != null) {
            dfs(node.children[c - 'a'], s, index + 1, current + c);
        } else {
            dfs(node, s, index + 1, current);
        }
    }

    public String findLongestWord(String s, List<String> words) {
        if (s == null || s.length() == 0 || words == null || words.size() == 0) {
            return "";
        }

        // build the trie
        this.buildTrie(words);

        for (int i = 0; i < s.length(); i++) {
            this.dfs(root, s, i, "");
        }

        return this.longestWord;
    }


    private class TrieNode {

        int wordCount;
        TrieNode[] children;

        TrieNode() {
            wordCount = 0;
            children = new TrieNode[26];
        }
    }
}
