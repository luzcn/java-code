import java.util.Arrays;

import leetcode.PalindromePairs_336;

public class Program {

    public static void main(String[] args) {
        PalindromePairs_336 ps = new PalindromePairs_336();

        var res = ps.palindromePairs(new String[]{"a", ""});

        System.out.println(res);
    }
}
