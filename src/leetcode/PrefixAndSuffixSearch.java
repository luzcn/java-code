package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given many words, words[i] has weight i.
 *
 * Design a class WordFilter that supports one function, WordFilter.f(String prefix, String suffix).
 *
 * It will return the word with given prefix and suffix with maximum weight. If no word exists, return -1.
 *
 * Examples:
 * Input:
 * WordFilter(["apple"])
 * WordFilter.f("a", "e") // returns 0
 * WordFilter.f("b", "") // returns -1
 *
 *
 * Note:
 * words has length in range [1, 15000].
 * For each test case, up to words.length queries WordFilter.f may be made.
 * words[i] has length in range [1, 10].
 * prefix, suffix have lengths in range [0, 10].
 * words[i] and prefix, suffix queries consist of lowercase letters only.
 */
public class PrefixAndSuffixSearch {

    // insert word prefix and suffix pair into a Trie
    // e.g. "apple", we can insert "#apple", "e#apple", "le#apple", "ple#apple", "pple#apple", "apple#apple"
    // now, for the query with ['a', 'e'], we can search "e#a"

    private TrieNode root;

    private void insert(List<String> suffixWords, int weight) {

        for (String word : suffixWords) {
            TrieNode current = root;

            for (char c : word.toCharArray()) {
                int index = c - 'a';

                if (c == '#') {
                    index = 26;
                }

                if (current.children[index] == null) {
                    current.children[index] = new TrieNode();
                }
                current = current.children[index];

                // add the weigh to current trie node
                current.weight = weight;
            }
        }
    }


    public PrefixAndSuffixSearch(String[] words) {
        root = new TrieNode();

        for (int weight = 0; weight < words.length; weight++) {
            String word = words[weight];

            List<String> suffixWords = new ArrayList<>();

            // do not forget the string "#apple"
            // when i the string length, substring returns empty string
            for (int i = word.length(); i >= 0; i--) {
                suffixWords.add(word.substring(i) + "#" + word);
            }
            insert(suffixWords, weight);
        }
    }

    public int f(String prefix, String suffix) {
        // search the string suffix + "#" + prefix
        String word = suffix + "#" + prefix;

        TrieNode current = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';

            if (c == '#') {
                index = 26;
            }

            if (current.children[index] == null) {
                return -1;
            }
            current = current.children[index];
        }

        return current.weight;
    }

    private class TrieNode {

        TrieNode[] children;
        int weight;

        TrieNode() {
            children = new TrieNode[27];
            weight = 0;
        }
    }
}
