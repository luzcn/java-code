package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
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
public class RepeatedDNASequences {

    public List<String> findRepeatedDnaSequences(String s) {

        // use a hashmap to save the 10-letter-long substring
        // if found repeat, then add to the result
        List<String> result = new ArrayList<>();

        if (s == null || s.length() == 0) {
            return result;
        }

        HashMap<String, Integer> set = new HashMap();

        for (int i = 0; i <= s.length() - 10; i++) {

            String dna = s.substring(i, i + 10);

            if (set.containsKey(dna)) {

                if (set.get(dna) == 1) {
                    result.add(dna);
                }
            }

            set.put(dna, set.getOrDefault(dna, 0) + 1);
        }

        return result;

    }
}
