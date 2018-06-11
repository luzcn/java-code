package leetcode;

import java.util.*;

// Given a string, we can "shift" each of its letter to its successive letter,
// for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:
//
// "abc" -> "bcd" -> ... -> "xyz"
// Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.
//
// Example:
//
// Input: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
// Output:
// [
//   ["abc","bcd","xyz"],
//   ["az","ba"],
//   ["acef"],
//   ["a","z"]
// ]
public class GroupShiftedStrings_249 {

    // the idea is find the pattern of each string
    // and encode these strings
    // e.g. "xyz" => get the diff of chars s[i+1] - s[i]

    private String encode(String s) {
        StringBuilder res = new StringBuilder();

        for (int i = 1; i < s.length(); i++) {
            int diff = s.charAt(i) - s.charAt(i - 1);
            if (diff < 0) {
                diff += 26;
            }

            res.append('a' + diff);
        }

        return res.toString();
    }

    public List<List<String>> groupStrings(String[] strings) {

        List<List<String>> res = new ArrayList<>();

        if (strings.length == 0) {
            return res;
        }

        HashMap<String, List<String>> map = new HashMap<>();

        for (String s : strings) {
            String code = encode(s);

            map.computeIfAbsent(code, k -> new ArrayList<>()).add(s);
        }

        res.addAll(map.values());
        return res;
    }
}
