package leetcode;

import java.util.ArrayList;
import java.util.List;

// Design a search autocomplete system for a search engine.
// Users may input a sentence (at least one word and end with a special character '#').
//
// For each character they type except '#', you need to return the top 3 historical hot sentences
// that have prefix the same as the part of sentence already typed. Here are the specific rules:
//
// - The hot degree for a sentence is defined as the number of times a user typed the exactly same sentence before.
// - The returned top 3 hot sentences should be sorted by hot degree (The first is the hottest one).
// If several sentences have the same degree of hot, you need to use ASCII-code order (smaller one appears first).
//
// - If less than 3 hot sentences exist, then just return as many as you can.
//
// - When the input is a special character, it means the sentence ends, and in this case, you need to return an empty list.
//
// Your job is to implement the following functions:
//
// The constructor function:
//
// AutocompleteSystem(String[] sentences, int[] times): This is the constructor.
// The input is historical data. Sentences is a string array consists of previously typed sentences.
// Times is the corresponding times a sentence has been typed. Your system should record these historical data.
//
// Now, the user wants to input a new sentence. The following function will provide the next character the user types:
//
// List<String> input(char c): The input c is the next character typed by the user.
// The character will only be lower-case letters ('a' to 'z'), blank space (' ') or a special character ('#').
// Also, the previously typed sentence should be recorded in your system.
//
// The output will be the top 3 historical hot sentences that have prefix the same as the part of sentence already typed.

public class DesignSearchAutocompleteSystem {

  // the user input string and frequency mapping
  // private HashMap<String, Integer> map = new HashMap<>();

  // The trie root node
  private TrieNode root = new TrieNode();

  private String currentSentence = "";

  private void insert(String word, int times) {

    TrieNode current = this.root;
    for (char c : word.toCharArray()) {
      int index = c - 'a';
      if (Character.isWhitespace(c)) {
        index = 26;
      }

      if (current.children[index] == null) {
        current.children[index] = new TrieNode();
      }
      current = current.children[index];
    }

    // + is used for increase the frequency
    current.times += times;
    current.sentence = word;
  }

  // dfs search all the sentence which has prefix "currentSentence"
  private void findAllSuggestion(String s, TrieNode node, List<TrieNode> res) {

    if (node.times > 0) {
      res.add(new TrieNode(s, node.times));
    }

    for (char c = 'a'; c <= 'z'; c++) {
      int index = c - 'a';

      if (node.children[index] != null) {
        findAllSuggestion(s + c, node.children[index], res);
      }
    }

    if (node.children[26] != null) {
      findAllSuggestion(s + " ", node.children[26], res);
    }

  }

  private List<TrieNode> query() {

    TrieNode current = root;
    List<TrieNode> res = new ArrayList<>();

    for (char c : this.currentSentence.toCharArray()) {
      int index = c - 'a';

      if (Character.isWhitespace(c)) {
        index = 26;
      }

      if (current.children[index] == null) {
        return res;
      }
      current = current.children[index];
    }

    // finished parsing the user input sentence now
    // start from the current trie node, dfs find all the sentences that have currentSentence as prefix
    findAllSuggestion(this.currentSentence, current, res);

    return res;
  }

  public DesignSearchAutocompleteSystem(String[] sentences, int[] times) {

    if (sentences.length != times.length) {
      return;
    }

    for (int i = 0; i < sentences.length; i++) {

      this.insert(sentences[i], times[i]);
    }
  }

  public List<String> input(char c) {

    List<String> res = new ArrayList<>();

    if (c == '#') {
      // end of sentence
      this.insert(currentSentence, 1);
      this.currentSentence = "";
    } else {
      // append the current char to query sentence
      currentSentence += c;
      List<TrieNode> candidates = this.query();

      // sort the candidates list ordered by times and alphabetic
      candidates.sort((x, y) -> {
        if (x.times == y.times) {
          return x.sentence.compareTo(y.sentence);
        } else {
          return y.times - x.times;
        }
      });

      for (int i = 0; i < Math.min(3, candidates.size()); i++) {
        res.add(candidates.get(i).sentence);
      }
    }

    return res;
  }


  private class TrieNode {

    TrieNode[] children;
    int times;
    String sentence;

    TrieNode() {
      // 0...25 for a...z, 26 is reserved for whitespace
      children = new TrieNode[27];
      times = 0;
      sentence = "";
    }

    TrieNode(String s, int t) {
      // 0...25 for a...z, 26 is reserved for whitespace
      children = new TrieNode[27];
      times = t;
      sentence = s;
    }
  }
}

// public static void main(String[] args) {
//
//     String[] sentence = new String[]{"i love you", "island", "iroman", "i love leetcode"};
//     int[] tiems = new int[]{5, 3, 2, 2};
//
//     DesignSearchAutocompleteSystem ds = new DesignSearchAutocompleteSystem(sentence, tiems);
//
//
//     System.out.println(ds.input('i'));
// }