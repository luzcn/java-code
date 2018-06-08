package leetcode;

// There is a new alien language which uses the latin alphabet.
// However, the order among letters are unknown to you.
//
// You receive a list of non-empty words from the dictionary, where words are sorted lexicographically
// by the rules of this new language.

import java.util.*;

// Derive the order of letters in this language.
//
// Example 1:
//
// Input:
// [
//   "wrt",
//   "wrf",
//   "er",
//   "ett",
//   "rftt"
// ]
//
// Output: "wertf"
// Example 2:
//
// Input:
// [
//   "z",
//   "x"
// ]
//
// Output: "zx"
// Example 3:
//
// Input:
// [
//   "z",
//   "x",
//   "z"
// ]
//
// Output: ""
//
// Explanation: The order is invalid, so return "".
// Note:
// - You may assume all letters are in lowercase.
// - You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
// - If the order is invalid, return an empty string.
// - There may be multiple valid order of letters, return any one of them is fine.
public class AlienDictionary {

    private HashMap<Character, HashSet<Character>> graph = new HashMap<>();
    private StringBuilder res = new StringBuilder();
    private HashSet<Character> visited = new HashSet<>();

    // The idea is construct a graph, then topological sort with checking if there is cycle in the graph

    // The problem says the "words" are sorted, but not for characters in each word.
    // e.g. from the first example, we have order "wertf", so ["ettf, efwt] is also valid
    public String alienOrder(String[] words) {

        if (words.length == 0) {
            return "";
        }

        if (words.length == 1) {
            return words[0];
        }

        // build the graph
        this.buildGraph(words);

        // topological sort
        for (Map.Entry<Character, HashSet<Character>> entry : graph.entrySet()) {
            if (!this.topoSort(entry.getKey(), new HashSet<>())) {
                return "";
            }
        }

        return res.reverse().toString();
    }


    private boolean topoSort(char node, HashSet<Character> ancestor) {
        if (ancestor.contains(node)) {
            return false;
        }

        if (this.visited.contains(node)) {
            return true;
        }

        this.visited.add(node);
        ancestor.add(node);
        for (char child : graph.getOrDefault(node, new HashSet<>())) {
            if (!topoSort(child, ancestor)) {
                return false;
            }
        }
        ancestor.remove(node);

        this.res.append(node);

        return true;
    }

    private void buildGraph(String[] words) {
        for (int i = 1; i < words.length; i++) {

            // comparing each two words pair
            int m = words[i - 1].length();
            int n = words[i].length();
            boolean isFound = false;

            int length = Math.max(m, n);

            for (int j = 0; j < length; j++) {

                // first, implicitly add each character into the graph
                if (j < m) {
                    graph.putIfAbsent(words[i - 1].charAt(j), new HashSet<>());
                }

                if (j < n) {
                    graph.putIfAbsent(words[i].charAt(j), new HashSet<>());
                }

                // find one edge, do not need to continue search and add edges, otherwise, it could make the graph cyclic
                if (j < m && j < n && words[i - 1].charAt(j) != words[i].charAt(j) && !isFound) {
                    // graph.computeIfAbsent(words[i - 1].charAt(j), k -> new HashSet<>()).add(words[i].charAt(j));

                    graph.get(words[i - 1].charAt(j)).add(words[i].charAt(j));
                    isFound = true;
                }
            }
        }
    }
}
