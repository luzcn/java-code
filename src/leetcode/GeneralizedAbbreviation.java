package leetcode;

import java.util.*;

// Write a function to generate the generalized abbreviations of a word.
//
// Note: The order of the output does not matter.
//
// Example:
//
// Input: "word"
// Output:
// ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
//
public class GeneralizedAbbreviation {


    private List<String> res = new ArrayList<>();

    // dfs
    // similar to subset, string permutation
    // each iteration, compute the i - start distance, if > 0, append to the current string
    // also need to add the ith character

    private void dfs(String word, int index, List<String> current) {
        if (index >= word.length()) {
            String result = "";
            for (String s : current) {
                result += s;
            }
            res.add(result);
            return;
        }

        for (int i = index; i <= word.length(); i++) {
            int distance = i - index;
            if (distance > 0) {
                current.add(Integer.toString(distance));
            }

            if (i < word.length()) {
                current.add("" + word.charAt(i));
            }

            dfs(word, i + 1, current);

            if (distance > 0) {
                current.remove(current.size() - 1);
            }

            if (i < word.length()) {
                current.remove(current.size() - 1);
            }
        }
    }

    public List<String> generateAbbreviations(String word) {

        dfs(word, 0, new ArrayList<>());

        return this.res;
    }
}
