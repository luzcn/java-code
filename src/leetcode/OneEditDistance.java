package leetcode;

/**
 * Given two strings s and t, determine if they are both one edit distance apart.
 *
 * Note:
 *
 * There are 3 possiblities to satisify one edit distance apart:
 *
 * - Insert a character into s to get t
 * - Delete a character from s to get t
 * - Replace a character of s to get t
 *
 * Example 1:
 *
 * Input: s = "ab", t = "acb"
 * Output: true
 * Explanation: We can insert 'c' into s to get t.
 * Example 2:
 *
 * Input: s = "cab", t = "ad"
 * Output: false
 * Explanation: We cannot get t from s by only one step.
 * Example 3:
 *
 * Input: s = "1203", t = "1213"
 * Output: true
 * Explanation: We can replace '0' with '1' to get t.
 */
public class OneEditDistance {

    // Similar to edit distance, but has more restrictions
    // the brute force solution is using "EditDistance" dp solution to get all the edit distance
    // and comparing with 1, this takes O(n^2) both time and space.
    // However; there are more constraints if only allows 1 edit distance.
    // 1. |s.size() - t.size()| <= 1
    // 2. if s.size() == t.size(), once we find a mismatch character s[i], t[j]; we can only replace.
    //3. if s.size() < t.size(), for mismatch, only insert. And only delete if s.size() > t.size().
    public boolean isOneEditDistance(String s, String t) {
        int m = s.length(), n = t.length();

        int length = Math.min(m, n);

        for (int i = 0; i < length; i++) {

            if (s.charAt(i) == t.charAt(i)) {
                continue;
            }

            if (m == n) {
                return s.substring(i + 1).equals(t.substring(i + 1));
            }

            if (m < n) {
                // insert the mismatch character to s
                return s.substring(i).equals(t.substring(i + 1));
            } else {
                // delete from s
                return s.substring(i + 1).equals(t.substring(i));
            }
        }

        // all [0...length] chars are equivalent
        // check if these two string length difference is 1
        return Math.abs(m - n) == 1;
    }
}
