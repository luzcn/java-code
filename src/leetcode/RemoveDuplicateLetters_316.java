package leetcode;

import java.util.*;

// Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once.
// You must make sure your result is the smallest in lexicographical order among all possible results.
//
// Example 1:
//
// Input: "bcabc"
// Output: "abc"
// Example 2:
//
// Input: "cbacdcbc"
// Output: "acdb"
public class RemoveDuplicateLetters_316 {

    // the general idea is greedy
    // 1. count the frequency of each characters
    // 2. use a helper array "process" to indicate if the char is used
    // 3. for each char "c", comparing with the result string last character "l", if the frequency of "l" > 0 and c < l,
    // replace the l with c, mark the process[l-'a'] as false

    public String removeDuplicateLetters(String s) {

        if (s.isEmpty()) {
            return s;
        }

        int n = s.length();
        // only lower case
        int[] count = new int[26];
        boolean[] processed = new boolean[26];

        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            int index = c - 'a';
            count[index]--;

            if (processed[index]) {
                continue;
            }

            // if the top char "l" in stack has more characters and current c is less than l
            // remove l and keep running this step
            while (!stack.isEmpty() && count[stack.peek() - 'a'] > 0 && c < stack.peek()) {

                // mark the char "l" as not processed
                processed[stack.peek() - 'a'] = false;

                // remove the top char
                stack.pop();
            }

            stack.push(c);
            processed[c - 'a'] = true;
        }

        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            res.append(stack.pop());
        }

        return res.reverse().toString();
    }
}
