package leetcode;

// Implement a magic directory with buildDict, and search methods.
//
// For the method buildDict, you'll be given a list of non-repetitive words to build a dictionary.
//
// For the method search, you'll be given a word, and judge whether if you modify exactly one character into
// another character in this word, the modified word is in the dictionary you just built.
//
// Example 1:
// Input: buildDict(["hello", "leetcode"]), Output: Null
// Input: search("hello"), Output: False
// Input: search("hhllo"), Output: True
// Input: search("hell"), Output: False
// Input: search("leetcoded"), Output: False
public class ImplementMagicDictionary {

  private TrieNode root;

  /**
   * Initialize your data structure here.
   */
  public ImplementMagicDictionary() {
    root = new TrieNode();
  }

  /**
   * Build a dictionary through a list of words
   */
  public void buildDict(String[] dict) {
    for (String word : dict) {
      TrieNode current = root;

      for (char c : word.toCharArray()) {
        int index = c - 'a';

        if (current.children[index] == null) {
          current.children[index] = new TrieNode();
        }
        current = current.children[index];
      }
      // use this wordCount to indicate if the trie path is a complete word
      current.wordCount++;
    }
  }

  /**
   * Returns if there is any word in the trie that equals to the given word after modifying exactly
   * one character
   */
  public boolean search(String word) {
    return dfs(word, 0, root, 0);
  }


  private boolean dfs(String word, int pos, TrieNode node, int changes) {

    // only allow exactly 1 change
    if (changes > 1) {
      return false;
    }

    // this may not required
    if (node == null) {
      return false;
    }

    // the word chars are finished matching,
    // the trie path is a complete word,
    // and change is exactly one
    if (pos >= word.length() && node.wordCount > 0 && changes == 1) {
      return true;
    }

    // the searching index is out side the word size.
    if (pos >= word.length()) {
      return false;
    }

    char c = word.charAt(pos);
    int index = c - 'a';

    for (int i = 0; i < 26; i++) {
      TrieNode next = node.children[i];
      if (next != null) {
        if (i == index) {
          // match the char in word dictionary
          if (dfs(word, pos + 1, next, changes)) {
            return true;
          }
        } else {
          if (dfs(word, pos + 1, next, changes + 1)) {
            return true;
          }
        }
      }
    }

    return false;
  }

  private class TrieNode {

    TrieNode[] children;
    int wordCount;

    TrieNode() {
      children = new TrieNode[26];
      wordCount = 0;
    }
  }
}
