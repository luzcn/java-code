package datastructure.String;

import java.util.*;

// Pattern Mathing problem.
// Given an input text string s and a string pattern,
// find the substring that can match this pattern.
// Assume, the inputs are always valid.
public class KMP {

    // O(m * n) time
    int PatternMatchingBF(String s, String p) {

        int i = 0;
        int j = 0;
        for (i = 0; i < s.length(); i++) {
            for (j = 0; j < p.length(); j++) {
                if (s.charAt(i + j) != p.charAt(j)) {
                    break;
                }
            }

            if (j == p.length()) {
                return i;
            }
        }

        return -1;
    }


    //  pre-process the pattern string, compute an auxiliary array lps[] which has identical size with the pattern.
    //  for each i in [0...n-1], lps[i] = the longest size of prefix which is also a suffix of substring p[0...i].
    private int[] computeFailureTable(String p) {
        int n = p.length();
        int[] lps = new int[n];

        // the first element is always 0.
        lps[0] = 0;

        for (int i = 1; i < n; i++) {
            int t = lps[i - 1];

            while (t > 0 && p.charAt(i) != p.charAt(t)) {
                t = lps[t - 1];
            }

            if (p.charAt(i) == p.charAt(t)) {
                t++;
            }

            lps[i] = t;
        }

        return lps;
    }

    public int PatternMatchingKMP(String s, String p) {
        int[] lps = computeFailureTable(p);

        int i = 0;
        int j = 0;

        while (i < s.length()) {
            if (s.charAt(i) == p.charAt(j)) {
                i++;
                j++;
            } else {

                // because not match
                // keep the i not change, but move j as far as possible
                // if cannot move j forward, increase i
                if (j == 0) {
                    i++;
                } else {
                    j = lps[j - 1];
                }
            }

            if (j == p.length()) {
                return i - j;   // i has been moved to the end of the matched sub-string
            }
        }

        return -1;
    }
}
