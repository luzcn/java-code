package leetcode;

import java.util.*;

// Given a sequence of words, check whether it forms a valid word square.
//
// A sequence of words forms a valid word square if the kth row and column read the exact same string,
// where 0 ≤ k < max(numRows, numColumns).
//
// Note:
// The number of words given is at least 1 and does not exceed 500.
// Word length will be at least 1 and does not exceed 500.
// Each word contains only lowercase English alphabet a-z.
//
// Example 1:
// Input:
// [
//   "abcd",
//   "bnrt",
//   "crmy",
//   "dtye"
// ]
//
// Output:
// true
//
// Explanation:
// The first row and first column both read "abcd".
// The second row and second column both read "bnrt".
// The third row and third column both read "crmy".
// The fourth row and fourth column both read "dtye".
//
// Therefore, it is a valid word square.
// Example 2:
// Input:
// [
//   "abcd",
//   "bnrt",
//   "crm",
//   "dt"
// ]
//
// Output:
// true

public class ValidWordSquare {

    // brute force, O(m*n) time, m*n is the total character size, also O(m*n) space
    // use a hash map save the string and row index
    // for each column i, get all the word[i] and form a string, check if the sting is identical to the string with row index i.
    public boolean validWordSquare(List<String> words) {
        if (words.isEmpty()) {
            return true;
        }

        int n = words.size();
        HashMap<Integer, String> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.put(i, words.get(i));
        }

        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (String w : words) {

                // the strings may not have the same length
                if (i < w.length()) {
                    sb.append(w.charAt(i));
                }
            }
            if (!map.get(i).equals(sb.toString())) {
                return false;
            }
        }

        return true;
    }

    // Word Squares
    // https://leetcode.com/problems/word-squares/description/
    // Given a set of words (without duplicates), find all word squares you can build from them.
    //
    // A sequence of words forms a valid word square if the kth row and column read the exact same string,
    // where 0 ≤ k < max(numRows, numColumns).
    //
    // For example, the word sequence ["ball","area","lead","lady"]
    // forms a word square because each word reads the same both horizontally and vertically.
    //
    // b a l l
    // a r e a
    // l e a d
    // l a d y
    //
    // Example 1:
    // Input:
    // ["area","lead","wall","lady","ball"]
    //
    // Output:
    // [
    //   [ "wall",
    //     "area",
    //     "lead",
    //     "lady"
    //   ],
    //   [ "ball",
    //     "area",
    //     "lead",
    //     "lady"
    //   ]
    // ]
    //
    // Explanation:
    // The output consists of two word squares.
    // The order of output does not matter (just the order of words in each word square matters).
    public List<List<String>> wordSquares(String[] words) {

    }
}
