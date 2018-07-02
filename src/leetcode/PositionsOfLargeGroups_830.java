package leetcode;

import java.util.*;

// In a string S of lowercase letters, these letters form consecutive groups of the same character.
//
// For example, a string like S = "abbxxxxzyy" has the groups "a", "bb", "xxxx", "z" and "yy".
//
// Call a group large if it has 3 or more characters.
// We would like the starting and ending positions of every large group.
//
// The final answer should be in lexicographic order.
//
//
//
// Example 1:
//
// Input: "abbxxxxzzy"
// Output: [[3,6]]
// Explanation: "xxxx" is the single large group with starting  3 and ending positions 6.
// Example 2:
//
// Input: "abc"
// Output: []
// Explanation: We have "a","b" and "c" but no large group.
// Example 3:
//
// Input: "abcdddeeeeaabbbcd"
// Output: [[3,5],[6,9],[12,14]]
//
public class PositionsOfLargeGroups_830 {

    // two pointer solution
    public List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> res = new ArrayList<>();

        int begin = 0;
        int end = 0;
        int count = 0;

        while (end < s.length()) {

            if (s.charAt(end) == s.charAt(begin)) {
                count++;
                end++;
            } else {
                if (count >= 3) {
                    res.add(Arrays.asList(begin, end - 1));
                }
                begin = end;
                count = 0;
            }
        }
        if (count >= 3) {
            res.add(Arrays.asList(begin, end - 1));
        }

        return res;
    }
}
