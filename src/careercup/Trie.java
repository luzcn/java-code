package careercup;

// Created by zhenlu on 1/13/17.


import java.util.ArrayList;

public class Trie {

    private TrieNode root = new TrieNode();

    public void addWord(ArrayList<String> words) {
        if (words == null)
            return;

        for (String s : words) {

            TrieNode current = root;
            int index = 0;

            // for each character in the input string
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                index = c - 'a'; //Character.getNumericValue(c) - Character.getNumericValue('a');

                if (current.children.get(index) == null)
                    current.children.set(index, new TrieNode());

                current = current.children.get(index);
            }
            current.wordCount = 1; // indicate there is a complete word in the trie
        }
    }


    public boolean getWord(String word) {
        if (word == null || word.isEmpty())
            return false;

        TrieNode current = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = c - 'a'; //Character.getNumericValue(c) - Character.getNumericValue('a');

            if (current.children.get(index) != null) {
                current = current.children.get(index);
            } else {
                return false;
            }
        }

        return current.wordCount == 1;
    }

    private class TrieNode {

        ArrayList<TrieNode> children = new ArrayList<>(); // only consider lowercase alphabet
        int wordCount = 0;

        // constructor
        TrieNode() {
            for (int i = 0; i < 26; i++) {
                children.add(null);
            }
        }
    }
}
