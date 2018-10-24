package leetcode;

/**
 * Design a data structure that supports the following two operations:
 *
 * void addWord(word) bool search(word) search(word) can search a literal word or a regular
 * expression string containing only letters "a-z" or "." A "." means it can represent any one
 * letter.
 *
 * For example:
 *
 * addWord("bad") addWord("dad") addWord("mad") search("pad") -> false search("bad") -> true
 * search(".ad") -> true search("b..") -> true
 *
 * Thought: Use Trie (suffix tree) data structure
 */
public class AddandSearchWordDatastructure {

  private TrieNode root;

  public AddandSearchWordDatastructure() {
    root = new TrieNode();
  }

  /**
   * Adds a word into the data structure.
   */
  public void addWord(String word) {
    TrieNode current = this.root;

    for (char c : word.toCharArray()) {
      int index = c - 'a';
      if (current.children[index] == null) {
        current.children[index] = new TrieNode();
      }
      current = current.children[index];
    }

    // a complete word, need to increase the count
    current.count++;
  }

  /**
   * Returns if the word is in the data structure. A word could contain the dot character '.' to
   * represent any one letter.
   */
  public boolean search(String word) {

    return dfs(word, 0, this.root);
  }


  private boolean dfs(String word, int index, TrieNode current) {
    if (current == null) {
      return false;
    }

    // need to check if the input word is a complete word in dictionary
    // i.e. the dictionary has "abc", input "ab" can match the prefix tree,
    // but it is not a valid word.
    if (index >= word.length()) {
      return current.count > 0;
    }

    char c = word.charAt(index);
    if (c != '.') {
      return dfs(word, index + 1, current.children[c - 'a']);
    } else {
      for (TrieNode n : current.children) {
        if (n != null && dfs(word, index + 1, n)) {
          return true;
        }
      }
    }

    return false;
  }

  private class TrieNode {

    // indicate if it is a complete word
    int count;
    TrieNode[] children;

    TrieNode() {
      count = 0;
      // only consider 'a...z' 26 alphabet
      children = new TrieNode[26];
    }
  }
}
