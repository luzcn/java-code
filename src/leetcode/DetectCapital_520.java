package leetcode;

import java.util.*;

// Given a word, you need to judge whether the usage of capitals in it is right or not.
//
// We define the usage of capitals in a word to be right when one of the following cases holds:
//
// - All letters in this word are capitals, like "USA".
// - All letters in this word are not capitals, like "leetcode".
// - Only the first letter in this word is capital if it has more than one letter, like "Google".
//
// Otherwise, we define that this word doesn't use capitals in a right way.
// Example 1:
// Input: "USA"
// Output: True
//
// Example 2:
// Input: "FlaG"
// Output: False
public class DetectCapital_520 {

    public boolean detectCapitalUse(String word) {
        if (word.isEmpty()) {
            return false;
        }

        int n = word.length();

        boolean isFirstCap = Character.isUpperCase(word.charAt(0));
        int count = 0;

        for (int i = 1; i < n; i++) {
            if (Character.isUpperCase(word.charAt(i))) {
                count++;
            }
        }

        // count == 0 means only first is uppercase, or non uppercase
        // isFirstCap && count == n - 1 mean all are uppercase
        return count == 0 || (isFirstCap && count == n - 1);

        //         if (isFirstCap) {
        //             if (count == 0 || count == n - 1) {
        //                 return true;
        //             }
        //         } else if (count == 0) {
        //                 return true;
        //         }
        //         return false;
    }
}
