package leetcode;

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


    // insert word prefix and sufix pair
    // e.g. "apple", we can insert "#apple", "e#apple", "le#apple", "ple#apple", "pple#apple", "apple#apple"
    // now, for the query with ['a', 'e'], we can search "e#a"

    private TrieNode root;

    public PrefixAndSuffixSearch(String[] words) {
        root = new TrieNode();

        for (int weight = 0; weight < words.length; weight++) {
            String word = words[weight] + "{";

            for (int i = 0; i < word.length(); i++) {
                TrieNode current = root;
                current.weight = weight;

                // j is used as circular index
                for (int j = i; j < 2 * word.length() - 1; j++) {
                    int index = word.charAt(j % word.length()) - 'a';
                    if (current.children[index] == null) {
                        current.children[index] = new TrieNode();
                    }
                    current = current.children[index];
                    current.weight = weight;
                }
            }
        }
    }

    public int f(String prefix, String suffix) {

        TrieNode current = root;

        for (char c : (suffix + "{" + prefix).toCharArray()) {
            int index = c - 'a';
            if (current.children[index] == null)
                return -1;

            current = current.children[index];
        }
        return current.weight;
    }

    private class TrieNode {
        TrieNode[] children;
        int weight;

        public TrieNode() {
            children = new TrieNode[27];
            weight = 0;
        }
    }
}
