package leetcode;


/**
 * Implement a trie with insert, search, and startsWith methods.
 *
 * Note:
 * You may assume that all inputs are consist of lowercase letters a-z.
 */
public class ImplementTrie {

    private TrieNode root = new TrieNode();

    /**
     * Initialize your data structure here.
     */
    public ImplementTrie() {

    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        if (word == null || word.length() == 0) {
            return;
        }

        TrieNode current = this.root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';

            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            current = current.children[index];
        }
        // used to indicate if it is a complete word
        current.wordCount++;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {

        TrieNode current = this.root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';

            if (current.children[index] == null) {
                return false;
            }
            current = current.children[index];
        }

        return current.wordCount > 0;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode current = this.root;
        for (char c : prefix.toCharArray()) {
            int index = c - 'a';

            if (current.children[index] == null) {
                return false;
            }
            current = current.children[index];
        }

        return true;
    }

    private class TrieNode {

        TrieNode[] children;
        int wordCount;

        TrieNode() {
            wordCount = 0;
            children = new TrieNode[26];
        }
    }
}
