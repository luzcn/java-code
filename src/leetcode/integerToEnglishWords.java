package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.
 *
 * For example,
 * 123 -> "One Hundred Twenty Three"
 * 12345 -> "Twelve Thousand Three Hundred Forty Five"
 * 1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 */
public class integerToEnglishWords {
    private final Map<Integer, String> map = new HashMap<>();

    public integerToEnglishWords() {
        map.put(0, "Zero");
        map.put(1, " One");
        map.put(2, " Two");
        map.put(3, " Three");
        map.put(4, " Four");
        map.put(5, " Five");
        map.put(6, " Six");
        map.put(7, " Seven");
        map.put(8, " Eight");
        map.put(9, " Nine");
        map.put(10, " Ten");
        map.put(11, " Eleven");
        map.put(12, " Twelve");
        map.put(13, " Thirteen");
        map.put(14, " Forteen");
        map.put(15, " Fifteen");
        map.put(16, " Sixteen");
        map.put(17, " Seventeen");
        map.put(18, " Eighteen");
        map.put(19, " Nineteen");
        map.put(20, " Twenty");
        map.put(30, " Thirty");
        map.put(40, " Forty");
        map.put(50, " Fifty");
        map.put(60, " Sixty");
        map.put(70, " Seventy");
        map.put(80, " Eighty");
        map.put(90, " Ninety");
    }


    public String numberToWords(int num) {
        if (num == 0)
            return map.get(0);

        // String[] words = new String[]{"Billion", "Million", "Thousand", "Hundred"};
        // int[] values = new int[]{1000000000, 1000000, 1000, 100};
        StringBuilder sb = new StringBuilder();

        if (num >= 1000000000) {
            int extra = num / 1000000000;
            sb.append(process(extra)).append(" Billion");
            num %= 1000000000;
        }

        if (num >= 1000000) {
            int extra = num / 1000000;
            sb.append(process(extra)).append(" Million");
            num %= 1000000;
        }

        if (num >= 1000) {
            int extra = num / 1000;
            sb.append(process(extra)).append(" Thousand");
            num %= 1000;
        }

        if (num > 0) {
            sb.append(process(num));
        }

        return sb.toString().trim();
    }

    private String process(int num) {
        StringBuilder sb = new StringBuilder();

        int numHundred = num / 100;
        int remains = num % 100;

        if (numHundred > 0) {
            sb.append(map.get(numHundred)).append(" Hundred");
        }

        if (remains > 0) {
            if (map.containsKey(remains)) {
                sb.append(map.get(remains));
            } else {
                int lastDigit = remains % 10;
                int numTen = remains - lastDigit;

                sb.append(map.get(numTen)).append(map.get(lastDigit));
            }
        }

        return sb.toString().trim();
    }

}
