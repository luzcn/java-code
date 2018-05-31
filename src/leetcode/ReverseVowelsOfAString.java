package leetcode;

import java.util.*;

// Write a function that takes a string as input and reverse only the vowels of a string.
//
// Example 1:
// Given s = "hello", return "holle".
//
// Example 2:
// Given s = "leetcode", return "leotcede".
//
// Note:
// The vowels does not include the letter "y".
public class ReverseVowelsOfAString {

    // two pointer solution
    public String reverseVowels(String s) {
        HashSet<Character> vowels = new HashSet<>(Arrays.asList('a', 'o', 'u', 'e', 'i', 'A', 'O', 'U', 'E', 'I'));
        char[] strs = s.toCharArray();

        int begin = 0;
        int end = strs.length - 1;

        while (begin < end) {

            if (!vowels.contains(strs[begin])) {
                begin++;
            } else if (!vowels.contains(strs[end])) {
                end--;
            } else {
                // swap
                char temp = strs[begin];
                strs[begin] = strs[end];
                strs[end] = temp;

                begin++;
                end--;
            }
        }

        StringBuilder res = new StringBuilder();

        for (char c : strs) {
            res.append(c);
        }
        return res.toString();
    }
}
