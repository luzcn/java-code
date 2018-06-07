package leetcode;

import java.util.*;

// Let's play the minesweeper game (Wikipedia, online game)!
//
// You are given a 2D char matrix representing the game board.
// - 'M' represents an unrevealed mine,
// - 'E' represents an unrevealed empty square,
// - 'B' represents a revealed blank square that has no adjacent (above, below, left, right, and all 4 diagonals) mines,
// digit ('1' to '8') represents how many mines are adjacent to this revealed square,
// - and finally 'X' represents a revealed mine.
//
// Now given the next click position (row and column indices) among all the unrevealed squares ('M' or 'E'),
// return the board after revealing this position according to the following rules:
//
// - If a mine ('M') is revealed, then the game is over - change it to 'X'.
// - If an empty square ('E') with no adjacent mines is revealed,
// then change it to revealed blank ('B') and all of its adjacent unrevealed squares should be revealed recursively.
// - If an empty square ('E') with at least one adjacent mine is revealed,
// then change it to a digit ('1' to '8') representing the number of adjacent mines.
//
// Return the board when no more squares will be revealed.
// Input:
//
// [['E', 'E', 'E', 'E', 'E'],
//  ['E', 'E', 'M', 'E', 'E'],
//  ['E', 'E', 'E', 'E', 'E'],
//  ['E', 'E', 'E', 'E', 'E']]
//
// Click : [3,0]
//
// Output:
//
// [['B', '1', 'E', '1', 'B'],
//  ['B', '1', 'M', '1', 'B'],
//  ['B', '1', '1', '1', 'B'],
//  ['B', 'B', 'B', 'B', 'B']]
public class Minesweeper {

    public char[][] updateBoard(char[][] board, int[] click) {

        // bfs
        int i = click[0];
        int j = click[1];

        // already revealed, do nothing
        if (board[i][j] == 'B') {
            return board;
        }

        // if click the mine, mark as 'X' and return
        if (board[i][j] == 'M') {
            board[i][j] = 'X';
            return board;
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(click);

        while (!queue.isEmpty()) {
            i = queue.peek()[0];
            j = queue.peek()[1];
            queue.poll();

            if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != 'E') {
                continue;
            }

            int mines = getMines(board, i, j);
            if (mines > 0) {
                board[i][j] = (char) (mines + '0');
            } else {
                board[i][j] = 'B';

                for (int[] dir : fourDirs) {
                    int x = i + dir[0];
                    int y = j + dir[1];

                    queue.offer(new int[]{x, y});
                }
            }
        }

        return board;
    }

    private int getMines(char[][] board, int i, int j) {
        int mines = 0;

        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];

            if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
                continue;
            }

            if (board[x][y] == 'M') {
                mines++;
            }
        }
        return mines;
    }

    private int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
    private int[][] fourDirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
}
