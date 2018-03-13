package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string that consists of only uppercase English letters,
 * you can replace any letter in the string with another letter at most k times.
 *
 * Find the length of a longest substring containing all repeating letters you can get after performing the above operations.
 *
 * Example 1:
 * Input:
 * s = "ABAB", k = 2
 *
 * Output:
 * 4
 *
 * Explanation:
 * Replace the two 'A's with two 'B's or vice versa.
 *
 *
 * Example 2:
 * Input:
 * s = "AABABBA", k = 1
 *
 * Output:
 * 4
 *
 * Explanation:
 * Replace the one 'A' in the middle with 'B' and form "AABBBBA".
 * The substring "BBBB" has the longest repeating letters, which is 4.
 */
public class LongestRepeatingCharacterReplacement {
    public int characterReplacement(String s, int k) {

        // similar to at most k distinct characters
        int result = 0;
        Map<Character, Integer> map = new HashMap<>();
        int begin = 0, end = 0;
        int count = 0;

        while (end < s.length()) {
            char c = s.charAt(end);
            // increase the appearance
            map.put(c, map.getOrDefault(c, 0) + 1);

            // here, count is the longest repeated characters
            count = Math.max(count, map.get(c));

            // current length - the longest repeated chars
            // is the total non-repeat char appearance, which are numbers need to replace
            while (end - begin + 1 - count > k) {
                char first = s.charAt(begin++);
                map.put(first, map.get(first) - 1);
            }
            result = Math.max(result, end - begin + 1);
            end++;
        }

        return result;
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