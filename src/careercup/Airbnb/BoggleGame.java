package careercup.Airbnb;

import java.util.*;

import datastructure.Trie;

// similar to word search II
// each cell can move in 8 directions
// Given a dictionary, a method to do lookup in dictionary and a M x N board where every cell has one character.
// Find all possible words that can be formed by a sequence of adjacent characters.
//
// - Note that we can move to any of 8 adjacent characters, but a word should not have multiple instances of same cell.
//
// Example:
//
// Input: dictionary[] = {"GEEKS", "FOR", "QUIZ", "GO"};
//        boggle[][]   = {{'G','I','Z'},
//                        {'U','E','K'},
//                        {'Q','S','E'}};
//       isWord(str): returns true if str is present in dictionary
//                    else false.
//
// Output:  Following words of dictionary are present
//          GEEKS
//          QUIZ
// https://www.geeksforgeeks.org/boggle-find-possible-words-board-characters/
public class BoggleGame {


    private List<String> res = new ArrayList<>();
    private int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {0, 1}, {-1, -1}, {1, -1}, {-1, 1}, {1, 1}};

    // dfs solution O(m*n) time
    private void dfs(char[][] boggle, int i, int j, boolean[][] visited, String word, int pos) {
        if (pos >= word.length()) {
            res.add(word);
            return;
        }

        if (i < 0 || i >= boggle.length || j < 0 || j >= boggle[0].length || visited[i][j]) {
            return;
        }

        if (boggle[i][j] != word.charAt(pos)) {
            return;
        }

        visited[i][j] = true;

        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = i + dir[1];

            dfs(boggle, x, y, visited, word, pos + 1);
        }
        visited[i][j] = false;
    }

    private List<String> wordSearchDFS(char[][] boggle, HashSet<String> dict) {

        if (boggle.length == 0) {
            return res;
        }

        int m = boggle.length;
        int n = boggle[0].length;
        boolean[][] visited = new boolean[m][n];

        for (String word : dict) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (word.charAt(0) == boggle[i][j]) {
                        dfs(boggle, i, j, visited, word, 0);
                    }
                }
            }
        }

        return res;
    }

    //**********************************************************************/

    // we can build a trie on top of the given dictionary words
    // search each path in the boggle board, if the path string in the trie, save it
    private class TrieNode {

        TrieNode[] children;
        int wordCount;

        TrieNode() {
            children = new TrieNode[26];
            wordCount = 0;
        }

    }

    private TrieNode root;

    private void buildTrie(List<String> words) {
        for (String word : words) {
            TrieNode current = root;

            for (char c : word.toCharArray()) {
                int index = c - 'a';

                if (current.children[index] == null) {
                    current.children[index] = new TrieNode();
                }
                current = current.children[index];
            }

            current.wordCount = 1;
        }
    }

    private void dfs(char[][] board, int i, int j, String current, TrieNode node, boolean[][] visited) {
        if (node == null) {
            return;
        }

        if (node.wordCount == 1) {
            res.add(current);

            // no duplicate
            node.wordCount--;
        }

        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || visited[i][j]) {
            return;
        }

        visited[i][j] = true;
        char c = board[i][j];
        int index = c - 'a';

        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];

            dfs(board, x, y, current + c, node.children[index], visited);
        }

        visited[i][j] = false;
    }

    private List<String> findWords(char[][] board, List<String> dict) {
        // build trie for each word
        root = new TrieNode();

        buildTrie(dict);

        return res;

    }


    public List<String> wordSearch(char[][] board, HashSet<String> dict) {
        return this.wordSearchDFS(board, dict);
    }

}
