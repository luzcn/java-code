package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Given a string, determine if a permutation of the string could form a palindrome.
 *
 * Example 1:
 *
 * Input: "code"
 * Output: false
 * Example 2:
 *
 * Input: "aab"
 * Output: true
 * Example 3:
 *
 * Input: "carerac"
 * Output: true
 */
public class PalindromePermutation {

    HashMap<Character, Integer> map = new HashMap<>();

    public boolean canPermutePalindrome(String s) {
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int count = 0;

        for (int frequency : map.values()) {
            if (frequency % 2 != 0 && ++count > 1) {
                return false;
            }
        }
        return true;
    }


    // Palindrome Permutation II
    // Given a string s, return all the palindromic permutations (without duplicates) of it.
    // Return an empty list if no palindromic permutation could be form.
    //
    // Example 1:
    //
    // Input: "aabb"
    // Output: ["abba", "baab"]
    // Example 2:
    //
    // Input: "abc"
    // Output: []

    // public List<String> generatePalindromes(String s) {
    //     List<String> result = new ArrayList<>();
    //
    // }
}
