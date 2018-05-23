import java.util.Arrays;

import leetcode.DailyTemperatures;

public class Program {

    public static void main(String[] args) {

        DailyTemperatures ds = new DailyTemperatures();

        int[] temp = {73, 74, 75, 71, 69, 72, 76, 73};

        var res = ds.dailyTemperatures(temp);

        Arrays.stream(res).forEach(System.out::println);
    }

}