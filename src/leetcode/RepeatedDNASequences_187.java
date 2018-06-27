package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

// All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T,
// for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.
//
// Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.
//
// Example:
//
// Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
//
// Output: ["AAAAACCCCC", "CCCCCAAAAA"]
public class RepeatedDNASequences_187 {

    public List<String> findRepeatedDnaSequences(String s) {

        // use a hashmap to save the 10-letter-long substring
        // if found repeat, then add to the result
        List<String> result = new ArrayList<>();

        if (s == null || s.length() == 0) {
            return result;
        }

        HashMap<String, Integer> set = new HashMap<>();

        for (int i = 0; i <= s.length() - 10; i++) {

            String dna = s.substring(i, i + 10);

            if (set.containsKey(dna) && set.get(dna) == 1) {

                result.add(dna);
            }

            set.put(dna, set.getOrDefault(dna, 0) + 1);
        }

        return result;

    }

    // use rolling hash
    public List<String> findRepeatedDnaSequencesRollingHash(String s) {
        List<String> result = new ArrayList<>();

        if (s == null || s.length() == 0) {
            return result;
        }

        // compute the first 10 substring rolling hash value
        long hash = getHashValue(s.substring(0, 10));
        HashMap<Long, Integer> seen = new HashMap<>();
        seen.put(hash, 1);

        for (int i = 10; i < s.length(); i++) {

            hash = updateHashValue(hash, s.charAt(i - 10), s.charAt(i));

            if (seen.containsKey(hash) && seen.get(hash) == 1) {

                // e.g. i is 10, so the start index is 1 and end index is 11
                result.add(s.substring(i - 9, i + 1));
            }

            seen.put(hash, seen.getOrDefault(hash, 0) + 1);
        }
        return result;
    }

    // the rolling hash value is s[0]*7^0 + s[1]*7^1 + s[2]*7^3 ...
    private long getHashValue(String s) {
        long value = 0;
        int m = 1;

        for (int i = 0; i < s.length(); i++) {
            value += m * (s.charAt(i) - 'A');
            m *= 7;
        }

        return value;
    }

    // the new hash value is (hash-s[0])/7 + s[i] * 7^i
    private long updateHashValue(long hash, char prev, char current) {

        // long res = hash - prev * multiplier;
        return (hash - (prev - 'A')) / 7 + (current - 'A') * ((long) (Math.pow(7, 9)));
    }
}
