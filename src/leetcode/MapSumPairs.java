package leetcode;

import java.util.*;

// Implement a MapSum class with insert, and sum methods.
//
// For the method insert, you'll be given a pair of (string, integer).
// The string represents the key and the integer represents the value.
//
// If the key already existed, then the original key-value pair will be overridden to the new one.
//
// For the method sum, you'll be given a string representing the prefix,
// and you need to return the sum of all the pairs' value whose key starts with the prefix.
//
// Example 1:
// Input: insert("apple", 3), Output: Null
// Input: sum("ap"), Output: 3
// Input: insert("app", 2), Output: Null
// Input: sum("ap"), Output: 5
public class MapSumPairs {

    // when we see prefix, usually we should use trie
    private TrieNode root;


    /**
     * Initialize your data structure here.
     */
    public MapSumPairs() {
        root = new TrieNode();
    }

    public void insert(String key, int val) {
        TrieNode current = root;
        for (char c : key.toCharArray()) {
            int index = c - 'a';

            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            current = current.children[index];
        }
        current.value = val;
    }

    public int sum(String prefix) {
        // bfs
        int totalSum = 0;
        TrieNode current = root;

        for (char c : prefix.toCharArray()) {
            int index = c - 'a';

            if (current.children[index] == null) {
                return 0;
            }
            current = current.children[index];
        }

        // bfs from this current node
        Queue<TrieNode> queue = new LinkedList<>();
        queue.add(current);

        while (!queue.isEmpty()) {
            TrieNode node = queue.poll();

            if (node == null) {
                continue;
            }
            totalSum += node.value;

            for (TrieNode child : node.children) {
                if (child != null) {
                    queue.offer(child);
                }
            }
        }

        return totalSum;
    }

    private class TrieNode {

        int value;
        TrieNode[] children;

        TrieNode() {
            value = 0;
            children = new TrieNode[26];
        }
    }
}
