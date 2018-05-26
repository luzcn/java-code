import leetcode.NumberOfDistinctIslands;

public class Program {

    public static void main(String[] args) {
        NumberOfDistinctIslands ns = new NumberOfDistinctIslands();

        int[][] grid = {{1, 0, 0, 0, 0}, {1, 0, 0, 0, 0}, {0, 0, 0, 1, 1}, {0, 0, 0, 0, 0}};

        System.out.println(ns.numDistinctIslands(grid));

    }
}