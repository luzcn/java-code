package leetcode;

import java.util.*;

// A Tic-Tac-Toe board is given as a string array board.
// Return True if and only if it is possible to reach this board position during the course of a valid tic-tac-toe game.
//
// The board is a 3 x 3 array, and consists of characters " ", "X", and "O".  The " " character represents an empty square.
//
// Here are the rules of Tic-Tac-Toe:
//
// - Players take turns placing characters into empty squares (" ").
// - The first player always places "X" characters, while the second player always places "O" characters.
// - "X" and "O" characters are always placed into empty squares, never filled ones.
// - The game ends when there are 3 of the same (non-empty) character filling any row, column, or diagonal.
// - The game also ends if all squares are non-empty.
//
// No more moves can be played if the game is over.
// Example 1:
// Input: board = ["O  ", "   ", "   "]
// Output: false
// Explanation: The first player always plays "X".
//
// Example 2:
// Input: board = ["XOX", " X ", "   "]
// Output: false
// Explanation: Players take turns making moves.
//
// Example 3:
// Input: board = ["XXX", "   ", "OOO"]
// Output: false
//
// Example 4:
// Input: board = ["XOX", "O O", "XOX"]
// Output: true
// Note:
//
// - board is a length-3 array of strings, where each string board[i] has length 3.
// - Each board[i][j] is a character in the set {" ", "X", "O"}.
public class ValidTicTacToeState_794 {

    public boolean validTicTacToe(String[] board) {

        // check the count of 'X' and 'O'
        // determine what would be the next char
        int countX = 0;
        int countO = 0;

        for (String row : board) {
            for (char c : row.toCharArray()) {
                if (c == 'X') {
                    countX++;
                }

                if (c == 'O') {
                    countO++;
                }
            }
        }

        // the first is always 'X', so countX >= countO
        if (countO != countX && countO != countX - 1) {
            return false;
        }

        // if countO == countX, next char is 'X'
        // if X can win, then current state is false
        if (countO == countX) {
            return !win(board, 'X');
        }

        // the next char is 'O'
        // if O can win, current state is false
        if (countO == countX - 1) {
            return !win(board, 'O');
        }

        return true;
    }


    private boolean win(String[] board, char p) {
        for (int i = 0; i < 3; i++) {

            // vertical
            if (board[i].charAt(0) == p && board[i].charAt(1) == p && board[i].charAt(2) == p) {
                return true;
            }

            // horizontal
            if (board[0].charAt(i) == p && board[1].charAt(i) == p && board[2].charAt(i) == p) {
                return true;
            }
        }

        // diagonal, left-up to right-bottom
        if (board[0].charAt(0) == p && board[1].charAt(1) == p && board[2].charAt(2) == p) {
            return true;
        }

        // diagonal, left-up to right-bottom
        if (board[2].charAt(0) == p && board[1].charAt(1) == p && board[0].charAt(2) == p) {
            return true;
        }

        return false;
    }
}
