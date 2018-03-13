package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RomanToInteger {
    public int romanToInt(String s) {

        List<String> roman = Arrays.asList("M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I");
        List<Integer> value = Arrays.asList(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1);

        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < roman.size(); i++) {
            map.put(roman.get(i), value.get(i));
        }


        int result = 0;

        int i = 0;
        while (i < s.length()) {
            if (i != s.length() - 1 && map.containsKey(s.substring(i, i + 2))) {
                result += map.get(s.substring(i, i + 2));
                i++;
            } else {
                result += map.get(s.substring(i, i + 1));
            }
            i++;
        }

        return result;
    }
}
