package leetcode;

import java.util.*;

// Given an Android 3x3 key lock screen and two integers m and n,
// where 1 ≤ m ≤ n ≤ 9, count the total number of unlock patterns of the Android lock screen,
// which consist of minimum of m keys and maximum n keys.
//
// Rules for a valid pattern:
// - Each pattern must connect at least m keys and at most n keys.
// - All the keys must be distinct.
// - If the line connecting two consecutive keys in the pattern passes through any other keys,
// the other keys must have previously selected in the pattern. No jumps through non selected key is allowed.
// - The order of keys used matters.
public class AndroidUnlockPatterns {

    // 1 2 3
    // 4 5 6
    // 7 8 9
    //
    // the question requires no jump between two consecutive numbers
    // e.g. if we need to 1->9, then 5 must be visited previously
    // 1->3, then 2 must be visited
    // - so we can build a help 2d array "jump" to indicate the node (number) of each two not connected numbers.
    // i.e. jump[1][3] = jump[3][1] = 2
    // - starting from 1, for each next = [1...9] if next is not visited, and they are connect or the jump node is visited,
    // we consider it is a pattern
    // - we can observe the pattern of scanning starting from 1 is symmetric to 3, 7 and 9, so we can scan once and *4
    // - similar to 2,4,6,8
    // - we finally scan staring at 5
    public int numberOfPatterns(int m, int n) {
        if (m > n) {
            return 0;
        }

        jump[1][3] = jump[3][1] = 2;
        jump[4][6] = jump[6][4] = 5;
        jump[7][9] = jump[9][7] = 8;

        jump[1][7] = jump[7][1] = 4;
        jump[2][8] = jump[8][2] = 5;
        jump[3][9] = jump[9][3] = 6;

        jump[1][9] = jump[9][1] = jump[3][7] = jump[7][3] = 5;

        boolean[] visited = new boolean[10];

        dfs(m, n, 1, 1, visited);
        int r1 = res;

        res = 0;
        dfs(m, n, 1, 2, visited);
        int r2 = res;

        res = 0;
        dfs(m, n, 1, 5, visited);
        int r3 = res;

        return r1 * 4 + r2 * 4 + r3;
    }

    // 9X9 board
    private int[][] jump = new int[10][10];
    int res = 0;

    private void dfs(int m, int n, int len, int node, boolean[] visited) {

        if (len > n) {
            return;
        }

        if (len >= m) {
            res++;
        }

        visited[node] = true;
        for (int next = 1; next <= 9; next++) {
            int jumpNode = jump[node][next];

            // - next is visited,
            // - node and next are connected (jump == 0) or the jump node is visited
            if (!visited[next] && (jumpNode == 0 || visited[jumpNode])) {
                dfs(m, n, len + 1, next, visited);
            }
        }

        visited[node] = false;
    }
}
