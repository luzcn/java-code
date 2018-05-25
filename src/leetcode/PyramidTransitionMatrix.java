package leetcode;

import java.util.*;

// We are stacking blocks to form a pyramid. Each block has a color which is a one letter string, like `'Z'`.
//
// For every block of color `C` we place not in the bottom row,
// we are placing it on top of a left block of color `A` and right block of color `B`.
//
// We are allowed to place the block there only if `(A, B, C)` is an allowed triple.
//
// We start with a bottom row of bottom, represented as a single string.
// We also start with a list of allowed triples allowed. Each allowed triple is represented as a string of length 3.
//
// Return true if we can build the pyramid all the way to the top, otherwise false.
//
// Example 1:
// Input: bottom = "XYZ", allowed = ["XYD", "YZE", "DEA", "FFF"]
// Output: true
// Explanation:
// We can stack the pyramid like this:
//     A
//    / \
//   D   E
//  / \ / \
// X   Y   Z
//
// This works because ('X', 'Y', 'D'), ('Y', 'Z', 'E'), and ('D', 'E', 'A') are allowed triples.
// Example 2:
// Input: bottom = "XXYX", allowed = ["XXX", "XXY", "XYX", "XYY", "YXZ"]
// Output: false
// Explanation:
// We can't stack the pyramid to the top.
// Note that there could be allowed triples (A, B, C) and (A, B, D) with C != D.
//
// Note:
// - bottom will be a string with length in range [2, 8].
// - allowed will have length in range [0, 200].
// - Letters in all strings will be chosen from the set {'A', 'B', 'C', 'D', 'E', 'F', 'G'}.
public class PyramidTransitionMatrix {

    private String candidates = "ABCDEFG";

    private void permutation(String current, List<String> res, int length, String bottom, HashSet<String> allowed) {
        if (current.length() == length) {
            boolean isValid = true;
            for (int i = 0; i < current.length(); i++) {
                String block = "" + bottom.charAt(i) + bottom.charAt(i + 1) + current.charAt(i);

                if (!allowed.contains(block)) {
                    isValid = false;
                }
            }
            if (isValid) {
                res.add(current);
            }
            return;
        }

        for (int i = 0; i < candidates.length(); i++) {
            current += candidates.charAt(i);

            permutation(current, res, length, bottom, allowed);

            current = current.substring(0, current.length() - 1);
        }
    }


    private boolean dfs(int N, String bottom, HashSet<String> allowed) {
        if (N == 0) {
            return true;
        }

        List<String> topList = new ArrayList<>();
        permutation("", topList, N, bottom, allowed);

        for (String top : topList) {

            if (dfs(N - 1, top, allowed)) {
                return true;
            }
        }

        return false;
    }

    public boolean pyramidTransition(String bottom, List<String> allowed) {
        int n = bottom.length();
        HashSet<String> allowedSet = new HashSet<>(allowed);

        return dfs(n - 1, bottom, allowedSet);
    }

}
