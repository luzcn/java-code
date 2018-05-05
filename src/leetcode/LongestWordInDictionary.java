package leetcode;

/**
 * Given a list of strings "words" representing an English Dictionary,
 * find the longest word in words that can be built one character at a time by other words in "words".
 *
 * If there is more than one possible answer, return the longest word with the smallest lexicographical order.
 *
 * If there is no answer, return the empty string.
 * Example 1:
 * Input:
 * words = ["w","wo","wor","worl", "world"]
 * Output: "world"
 * Explanation:
 * The word "world" can be built one character at a time by "w", "wo", "wor", and "worl".
 *
 * Example 2:
 * Input:
 * words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
 * Output: "apple"
 * Explanation:
 * Both "apply" and "apple" can be built from other words in the dictionary.
 * However, "apple" is lexicographically smaller than "apply".
 *
 * All the strings in the input will only contain lowercase letters.
 * The length of words will be in the range [1, 1000].
 * The length of words[i] will be in the range [1, 30].
 */

public class LongestWordInDictionary {

    private TrieNode root = new TrieNode();
    private String longestWord = "";

    private void dfs(TrieNode node, String s) {
        if (node == null) {
            return;
        }

        if (node.wordCount > 0 && s.length() > longestWord.length()) {
            longestWord = s;
        }

        for (char c = 'a'; c <= 'z'; c++) {
            int index = c - 'a';
            if (node.children[index] == null || node.children[index].wordCount == 0) {
                continue;
            }

            dfs(node.children[index], s + c);
        }
    }

    private void buildTrie(String[] words) {
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

    public String longestWord(String[] words) {
        if (words.length == 0) {
            return "";
        }

        // add all word into the trie
        this.buildTrie(words);

        // dfs get the longest word
        this.dfs(root, "");

        return longestWord;
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
