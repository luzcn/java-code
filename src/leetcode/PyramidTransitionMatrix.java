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

    private HashMap<String, List<Character>> map = new HashMap<>();

    private boolean dfs(String bottom, String current) {
        if (bottom.length() == 2 && current.length() == 1) {
            return true;
        }

        if (bottom.length() == current.length() + 1) {
            return dfs(current, "");
        }

        int pos = current.length();
        String prefix = bottom.substring(pos, pos + 2);

        if (map.containsKey(prefix)) {
            for (char c : map.get(prefix)) {
                if (dfs(bottom, current + c)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean pyramidTransition(String bottom, List<String> allowed) {
        int n = bottom.length();
        // HashSet<String> allowedSet = new HashSet<>(allowed);

        // pre-process the allowed strings
        // create a mapping of first 2 chars key and list of last char as value
        // e.g. "XYD" => "XY"-> ['D']
        for (String word : allowed) {
            map.computeIfAbsent(word.substring(0, 2), k -> new ArrayList<>()).add(word.charAt(2));
        }

        return dfs(bottom, "");
    }

}
