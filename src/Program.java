import leetcode.LongestLineOfConsecutiveOneInMatrix_562;

public class Program {

  public static void main(String[] args) {
    LongestLineOfConsecutiveOneInMatrix_562 longestLineOfConsecutiveOneInMatrix_562 = new LongestLineOfConsecutiveOneInMatrix_562();

    int[][] grid = {
        {0, 1, 0, 1, 1},
        {1, 1, 0, 0, 1},
        {0, 0, 0, 1, 0},
        {1, 0, 1, 1, 1},
        {1, 0, 0, 0, 1}};

    // [[0,1,0,1,1],[1,1,0,0,1],[0,0,0,1,0],[1,0,1,1,1],[1,0,0,0,1]]

    System.out.println(longestLineOfConsecutiveOneInMatrix_562.longestLine(grid));

  }

}