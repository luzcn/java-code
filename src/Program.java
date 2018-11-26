import java.util.Arrays;
import leetcode.LoudAndRich_851;


public class Program {

  public static void main(String[] args) {

    LoudAndRich_851 loudAndRich_851 = new LoudAndRich_851();

    int[][] richer = new int[][]{{1, 0}, {2, 1}, {3, 1}, {3, 7}, {4, 3}, {5, 3}, {6, 3}};

    int[] quiet = new int[]{3, 2, 5, 4, 6, 1, 7, 0};
    var res = loudAndRich_851.loudAndRich(richer, quiet);

    Arrays.stream(res).forEach(System.out::println);
  }


}