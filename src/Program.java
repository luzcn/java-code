import java.util.Arrays;

import leetcode.PourWater_755;

public class Program {

    public static void main(String[] args) {
        PourWater_755 sl = new PourWater_755();
        int[] heights = {1, 2, 3, 4, 3, 2, 1, 2, 3, 4, 3, 2, 1};

        var res = sl.pourWater(heights, 5, 5);

        Arrays.stream(res).forEach(x -> System.out.print(x + " "));
    }
}
