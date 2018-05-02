import leetcode.DungeonGame;

import java.util.Arrays;
import java.util.stream.Collectors;


public class Program {

    public static void main(String[] args) {
        int[][] dungeon = new int[][]{
                {-2, -3, 3},
                {-5, -10, 1},
                {10, 30, -5}};

        DungeonGame dg = new DungeonGame();
        System.out.println(dg.calculateMinimumHP(dungeon));

        for (int[] cs : dg.dp) {
            Arrays.stream(cs).mapToObj(i -> i + "\t").forEach(System.out::print);
            System.out.println();
        }
    }
}