package leetcode;

import java.util.*;

// On a 2x3 board, there are 5 tiles represented by the integers 1 through 5, and an empty square represented by 0.
//
// A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.
//
// The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].
//
// Given a puzzle board, return the least number of moves required so that the state of the board is solved.
// If it is impossible for the state of the board to be solved, return -1.
public class SlidingPuzzle_773 {

    // the board is always 2*3
    public int slidingPuzzle(int[][] board) {

        int steps = 0;

        Queue<BoardState> queue = new LinkedList<>();
        Queue<BoardState> temp = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();

        queue.add(new BoardState(board));

        while (!queue.isEmpty()) {
            BoardState current = queue.poll();

            visited.add(current.getId());

            if (current.isValid()) {
                return steps;
            }

            for (BoardState next : current.getNextState()) {
                if (!visited.contains(next.getId())) {
                    temp.add(next);
                }
            }

            if (queue.isEmpty()) {
                queue = temp;
                temp = new LinkedList<>();
                steps++;
            }
        }

        return -1;
    }


    private class BoardState {

        private int[][] board;

        int zeroX;
        int zeroY;

        BoardState(int[][] grid) {
            board = grid;

            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == 0) {
                        zeroX = i;
                        zeroY = j;
                        break;
                    }
                }
            }
        }

        // use a string to represent the 2d array
        // checking if this board state is visited
        String getId() {
            StringBuilder id = new StringBuilder();
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 3; j++) {
                    id.append(board[i][j]);
                }
            }
            return id.toString();
        }

        public boolean isValid() {
            return board[0][0] == 1 && board[0][1] == 2 && board[0][2] == 3 && board[1][0] == 4 && board[1][1] == 5;
        }

        List<BoardState> getNextState() {

            List<BoardState> states = new ArrayList<>();

            for (int[] dir : dirs) {
                int x = zeroX + dir[0];
                int y = zeroY + dir[1];

                if (x >= 0 && x < 2 && y >= 0 && y < 3) {

                    // deep copy
                    int[][] newBoard = new int[2][3];
                    for (int i = 0; i < 2; i++) {
                        System.arraycopy(board[i], 0, newBoard[i], 0, 3);
                    }

                    newBoard[zeroX][zeroY] = newBoard[x][y];
                    newBoard[x][y] = 0;

                    states.add(new BoardState(newBoard));
                }
            }

            return states;
        }

        private int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    }


}
