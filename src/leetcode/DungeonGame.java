package leetcode;

/**
 * The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon.
 *
 * The dungeon consists of M x N rooms laid out in a 2D grid.
 *
 * Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.
 *
 * The knight has an initial health point represented by a positive integer.
 * If at any point his health point drops to 0 or below, he dies immediately.
 *
 * Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms;
 * other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).
 *
 * In order to reach the princess as quickly as possible,
 * the knight decides to move only rightward or downward in each step.
 *
 *
 *
 * Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.
 */
public class DungeonGame {

    public int[][] dp;

    public int calculateMinimumHP(int[][] dungeon) {
        // dp solution
        int m = dungeon.length;
        if (m == 0)
            return 0;

        int n = dungeon[0].length;
        dp = new int[m][n];

        // start from the right-bottom (the princess) position
        // set the right bottom value as the dungeon value
        dp[m - 1][n - 1] = dungeon[m - 1][n - 1];

        // first compute the last column
        for (int i = m - 2; i >= 0; i--) {
            if (dp[i + 1][n - 1] > 0) {
                // if the next level cell is positive number, we do not need extra HP to reach cell [i+1][j]
                // so we save this dungeon[i][j] in dp
                dp[i][n - 1] = dungeon[i][n - 1];
            } else {
                // if the next level cell is negative
                // we sum up the dp[i+1][j] + dungeon[i][j]
                // because we need at lease |dp[i+1][j] + dungeon[i][j]| + 1 HP that can reach the cell [i+1][j]
                dp[i][n - 1] = dp[i + 1][n - 1] + dungeon[i][n - 1];
            }
        }

        // compute the last row
        for (int j = n - 2; j >= 0; j--) {
//            if (dp[m - 1][j + 1] > 0) {
//                dp[m - 1][j] = dungeon[m - 1][j];
//            } else {
//                dp[m - 1][j] = dp[m - 1][j + 1] + dungeon[m - 1][j];
//            }

            dp[m - 1][j] = dp[m - 1][j + 1] > 0 ? dungeon[m - 1][j] : dp[m - 1][j + 1] + dungeon[m - 1][j];
        }

        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                if (dp[i + 1][j] > 0 || dp[i][j + 1] > 0) {
                    dp[i][j] = dungeon[i][j];
                } else {
                    int value1 = dp[i + 1][j];
                    int value2 = dp[i][j + 1];

                    // because they are both negative, take the larger one
                    // e.g. -4 > -10, and we need at least 4 + 1 HP to reach cell [i][j]
                    dp[i][j] = dungeon[i][j] + Math.max(value1, value2);
                }
            }
        }


        int hp = dp[0][0];
        return hp > 0 ? 1 : 0 - hp + 1;
    }
}
