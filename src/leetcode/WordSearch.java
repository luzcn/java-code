package leetcode;

// Given a 2D board and a word, find if the word exists in the grid.
//
// The word can be constructed from letters of sequentially adjacent cell,
// where "adjacent" cells are those horizontally or vertically neighboring.
//
// The same letter cell may not be used more than once.
//
// For example,
// Given board =
//
// [
// ['A','B','C','E'],
// ['S','F','C','S'],
// ['A','D','E','E']
// ]
// word = "ABCCED", -> returns true,
// word = "SEE", -> returns true,
// word = "ABCB", -> returns false.
//
public class WordSearch {

    private int[][] dirs = {{1, 0}};

    private boolean dfs(char[][] board, String word, int index, int i, int j) {
        if (index >= word.length()) {
            return true;
        }

        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return false;
        }

        if (board[i][j] != word.charAt(index)) {
            return false;
        }

        // visited[i][j] = true;

        board[i][j] ^= 256;
        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (dfs(board, word, index + 1, x, y)) {
                return true;
            }
        }
        board[i][j] ^= 256;
        // visited[i][j] = false;

        return false;
    }


    // use board[x][y] xor 256 to avoid extra space
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;

        // boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (word.length() == 1) {
                        return true;
                    }

                    if (dfs(board, word, 0, i, j)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
