package leetcode;

/**
 * Given a 2D board and a word, find if the word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring.
 *
 * The same letter cell may not be used more than once.
 *
 * For example,
 * Given board =
 *
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * word = "ABCCED", -> returns true,
 * word = "SEE", -> returns true,
 * word = "ABCB", -> returns false.
 */
public class WordSearch {
    private boolean dfs(char[][] board, String word, int index, int x, int y, boolean[][] visited) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
            return false;
        }

        if (index >= word.length())
            return true;

        if (!visited[x][y] && board[x][y] == word.charAt(index)) {
            visited[x][y] = true;

            if (dfs(board, word, index + 1, x - 1, y, visited) ||
                    dfs(board, word, index + 1, x + 1, y, visited) ||
                    dfs(board, word, index + 1, x, y - 1, visited) ||
                    dfs(board, word, index + 1, x, y + 1, visited)) {
                return true;
            }

            visited[x][y] = false;
        }
        return false;
    }

    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;

        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (word.length() == 1) {
                        return true;
                    }

                    if (dfs(board, word, 0, i, j, visited)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
