package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string that consists of only uppercase English letters,
 * you can replace any letter in the string with another letter at most k times.
 * <p>
 * Find the length of a longest substring containing all repeating letters you can get after performing the above operations.
 */
public class LongestRepeatingCharacterReplacement {
    public int characterReplacement(String s, int k) {

        // similar to at most k distinct characters
        int maxLen = 0;
        int begin = 0, end = 0;
        int counter = 0;

        Map<Character, Integer> map = new HashMap<>();

        while (end < s.length()) {
            char c = s.charAt(end);
            if (!map.containsKey(c) || map.get(c) == 0) {
                map.put(c, 1);
            } else {
                map.put(c, map.get(c) + 1);
            }
            // longest repeating character
            counter = Math.max(counter, map.get(c));
            end++;

            // the characters need to replace
            while (end - begin - counter > k) {
                char cs = s.charAt(begin);
                map.put(cs, map.get(cs) - 1);
                begin++;
            }

            maxLen = Math.max(maxLen, end - begin);
        }

        return maxLen;
    }
}


// public int characterReplacement(String s, int k) {
//     int len = s.length();
//     int[] count = new int[26];
//     int start = 0, maxCount = 0, maxLength = 0;
//     for (int end = 0; end < len; end++) {
//         maxCount = Math.max(maxCount, ++count[s.charAt(end) - 'A']);
//         while (end - start + 1 - maxCount > k) {
//             count[s.charAt(start) - 'A']--;
//             start++;
//         }
//         maxLength = Math.max(maxLength, end - start + 1);
//     }
//     return maxLength;
// }