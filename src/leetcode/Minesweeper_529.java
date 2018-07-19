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
public class Minesweeper_529 {

    private int[][] eightDirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
    private int m;
    private int n;

    private int getMines(char[][] board, int i, int j) {
        int mines = 0;
        for (int[] dir : eightDirs) {
            int x = i + dir[0];
            int y = j + dir[1];

            if (x < 0 || x >= m || y < 0 || y >= n) {
                continue;
            }

            if (board[x][y] == 'M') {
                mines++;
            }
        }

        return mines;
    }

    public char[][] updateBoard(char[][] board, int[] click) {
        m = board.length;
        if (m == 0) {
            return board;
        }
        n = board[0].length;

        int i = click[0];
        int j = click[1];

        if (board[i][j] == 'B') {
            return board;
        }

        if (board[i][j] == 'M') {
            board[i][j] = 'X';
            return board;
        }

        // bfs
        Queue<int[]> queue = new LinkedList<>();
        queue.add(click);

        while (!queue.isEmpty()) {
            int x = queue.peek()[0];
            int y = queue.peek()[1];
            queue.poll();

            int mines = getMines(board, x, y);
            if (mines > 0) {
                board[x][y] = (char) (mines + '0');

            } else {
                board[x][y] = 'B';
                for (int[] dir : eightDirs) {
                    int u = x + dir[0];
                    int v = y + dir[1];

                    if (u < 0 || u >= m || v < 0 || v >= n || board[u][v] != 'E') {
                        continue;
                    }

                    queue.add(new int[]{u, v});
                }
            }
        }

        return board;
    }


    public static void main(String[] args) {
        char[][] board = {
                {'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E', 'E', 'E', 'M'},
                {'E', 'E', 'M', 'E', 'E', 'E', 'E', 'E'},
                {'M', 'E', 'E', 'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'M', 'M', 'E', 'E', 'E', 'E'}};

        Minesweeper_529 program = new Minesweeper_529();

        var res = program.updateBoard(board, new int[]{0, 0});

        for (char[] ans : res) {
            for (char c : ans) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
}
