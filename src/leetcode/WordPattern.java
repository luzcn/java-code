package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a pattern and a string str, find if str follows the same pattern.
 * <p>
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.
 * <p>
 * Examples:
 * pattern = "abba", str = "dog cat cat dog" should return true.
 * pattern = "abba", str = "dog cat cat fish" should return false.
 * pattern = "aaaa", str = "dog cat cat dog" should return false.
 * pattern = "abba", str = "dog dog dog dog" should return false.
 */
public class WordPattern {

    public boolean wordPattern(String pattern, String str) {

        Map<Character, String> map = new HashMap<>();
        Map<String, Character> map2 = new HashMap<>();

        if (pattern.isEmpty()) {
            return str.isEmpty();
        }

        String[] strList = str.split(" ");

        if (strList.length != pattern.length()) {
            return false;
        }

        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            String s = strList[i];

            if (!map.containsKey(c)) {
                map.put(c, s);
            } else if (!map.get(c).equals(s)) {
                return false;
            }


            // "abba"
            // "dog dog dog dog"
            // should be false
            if (!map2.containsKey(s)) {
                map2.put(s, c);
            } else if (map2.get(s) != c) {
                return false;
            }
        }

        return true;

    }

}
