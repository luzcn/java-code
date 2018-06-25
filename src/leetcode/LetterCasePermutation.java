package leetcode;

import java.util.ArrayList;
import java.util.List;

// Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.
// Return a list of all possible strings we could create.
//
// Examples:
// Input: S = "a1b2"
// Output: ["a1b2", "a1B2", "A1b2", "A1B2"]
//
// Input: S = "3z4"
// Output: ["3z4", "3Z4"]
//
// Input: S = "12345"
// Output: ["12345"]
// Note:
//
// S will be a string with length at most 12.
// S will consist only of letters or digits.
//
public class LetterCasePermutation {

    // recursive, bottom-up solution
    public List<String> letterCasePermutation(String S) {
        List<String> result = new ArrayList<>();

        if (S.isEmpty()) {
            result.add("");
            return result;
        }

        char c = S.charAt(0);

        List<String> words = letterCasePermutation(S.substring(1));

        for (String word : words) {
            result.add(c + word);

            if (!Character.isDigit(c)) {
                result.add((Character.isUpperCase(c) ? Character.toLowerCase(c) : Character.toUpperCase(c)) + word);
            }
        }

        return result;
    }
}
