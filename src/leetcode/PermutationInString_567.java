package leetcode;

import java.util.*;

// Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1.
// In other words, one of the first string's permutations is the substring of the second string.
//
// Example 1:
// Input:s1 = "ab" s2 = "eidbaooo"
// Output:True
// Explanation: s2 contains one permutation of s1 ("ba").
//
// Example 2:
// Input:s1= "ab" s2 = "eidboaoo"
// Output: False
// Note:
// - The input strings only contain lower case letters.
// - The length of both given strings is in range [1, 10,000].
public class PermutationInString_567 {

    // if two strings have equivalent char frequency, then there are permutations
    private boolean isAnagram(String s, int[] hasChars) {
        int[] chars = new int[26];

        for (char c : s.toCharArray()) {
            chars[c - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if (chars[i] != hasChars[i]) {
                return false;
            }
        }

        return true;
    }

    public boolean checkInclusion(String s1, String s2) {

        // pre-process s1, get all the char frequency
        int[] hasChars = new int[26];
        for (char c : s1.toCharArray()) {
            hasChars[c - 'a']++;
        }

        // if we found any char c from s2 is also appearing in s1,
        // take the substring of s1 length and check if they are permutations.
        for (int i = 0; i <= s2.length() - s1.length(); i++) {
            char c = s2.charAt(i);
            if (hasChars[c - 'a'] > 0) {
                if (isAnagram(s2.substring(i, i + s1.length()), hasChars)) {
                    return true;
                }
            }
        }

        return false;
    }
}
