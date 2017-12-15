package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Determine if a Sudoku is valid
 * <p>
 * A valid Sudoku board (partially filled) is not necessarily solvable.
 * Only the filled cells need to be validated.
 */
public class ValidSudoku {
    public boolean isValidSudoku(char[][] board) {

        if (board.length == 0) {
            return false;
        }

        int m = board.length;
        int n = board[0].length;

        // check if each row has duplicate
        Set<Character> isDuplicate = new HashSet<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '.')
                    continue;

                if (isDuplicate.contains(board[i][j])) {
                    return false;
                } else {
                    isDuplicate.add(board[i][j]);
                }
            }

            isDuplicate.clear();
        }

        isDuplicate.clear();
        // check each column
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                if (board[i][j] == '.')
                    continue;

                if (isDuplicate.contains(board[i][j])) {
                    return false;
                } else {
                    isDuplicate.add(board[i][j]);
                }
            }
            isDuplicate.clear();
        }

        isDuplicate.clear();
        // check each 3X3 square
        for (int i = 0; i < m; i = i + 3) {
            for (int j = 0; j < n; j = j + 3) {

                for (int k = i; k < i + 3; k++){
                    for (int l = j ; l < j + 3; l++){
                        if (board[k][l] == '.')
                            continue;

                        if (isDuplicate.contains(board[k][l])){
                            return false;
                        } else {
                            isDuplicate.add(board[k][l]);
                        }
                    }
                }
                isDuplicate.clear();
            }
            isDuplicate.clear();
        }
        return true;
    }
}
