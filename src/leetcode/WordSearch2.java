package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 *
 * Each word must be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring.
 *
 * The same letter cell may not be used more than once in a word.
 *
 * For example,
 * Given words = ["oath","pea","eat","rain"] and board =
 *
 * [
 * ['o','a','a','n'],
 * ['e','t','a','e'],
 * ['i','h','k','r'],
 * ['i','f','l','v']
 * ]
 * Return ["eat","oath"].
 * Note:
 * You may assume that all inputs are consist of lowercase letters a-z.
 *
 * Thoughts:
 * 1. Build a Trie of the given words
 * 2. Traverse the given 2d graph, for each searched string, check if it is in the trie
 */
public class WordSearch2 {

    private TrieNode root = new TrieNode();
    private List<String> result = new ArrayList<>();
    private int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    // build the trie
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

            // the problem doesn't need include duplicate
            // set the the word count to 1
            current.words = 1;
        }
    }

    private void dfs(char[][] board, int x, int y, String currentWord, boolean[][] visited, TrieNode currentNode) {

        if (currentNode == null) {
            return;
        }

        if (currentNode.words == 1) {
            result.add(currentWord);

            // doesn't need duplicates in result
            currentNode.words--;
        }

        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || visited[x][y]) {
            return;
        }

        visited[x][y] = true;
        char c = board[x][y];
        int index = c - 'a';

        for (int[] dir : this.dirs) {
            dfs(board, x + dir[0], y + dir[1], currentWord + c, visited, currentNode.children[index]);
        }

        visited[x][y] = false;
    }

    public List<String> findWords(char[][] board, String[] words) {

        if (board.length == 0 || words.length == 0) {
            return result;
        }

        // Build the trie
        buildTrie(words);

        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, i, j, "", visited, root);
            }
        }

        return this.result;
    }


    private class TrieNode {

        TrieNode[] children;
        int words;

        public TrieNode() {
            children = new TrieNode[26];
            words = 0;
        }
    }
}

// char[][] board = new char[][]{
//         {'o', 'a', 'a', 'n'},
//         {'e', 'e', 'a', 'e'},
//         {'i', 'h', 'k', 'r'},
//         {'i', 'f', 'l', 'v'}};
//
// String[] words = new String[]{"oath", "pea", "eat", "rain"};