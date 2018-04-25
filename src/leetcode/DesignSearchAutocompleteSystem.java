package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Design a search autocomplete system for a search engine.
 * Users may input a sentence (at least one word and end with a special character '#').
 *
 * For each character they type except '#', you need to return the top 3 historical hot sentences
 * that have prefix the same as the part of sentence already typed. Here are the specific rules:
 *
 * - The hot degree for a sentence is defined as the number of times a user typed the exactly same sentence before.
 * - The returned top 3 hot sentences should be sorted by hot degree (The first is the hottest one).
 * If several sentences have the same degree of hot, you need to use ASCII-code order (smaller one appears first).
 *
 * - If less than 3 hot sentences exist, then just return as many as you can.
 *
 * - When the input is a special character, it means the sentence ends, and in this case, you need to return an empty list.
 *
 * Your job is to implement the following functions:
 *
 * The constructor function:
 *
 * AutocompleteSystem(String[] sentences, int[] times): This is the constructor.
 * The input is historical data. Sentences is a string array consists of previously typed sentences.
 * Times is the corresponding times a sentence has been typed. Your system should record these historical data.
 *
 * Now, the user wants to input a new sentence. The following function will provide the next character the user types:
 *
 * List<String> input(char c): The input c is the next character typed by the user.
 * The character will only be lower-case letters ('a' to 'z'), blank space (' ') or a special character ('#').
 * Also, the previously typed sentence should be recorded in your system.
 *
 * The output will be the top 3 historical hot sentences that have prefix the same as the part of sentence already typed.
 */
public class DesignSearchAutocompleteSystem {

    private TrieNode root;
    private TrieNode readCurrent;
    private StringBuilder previousInput = new StringBuilder();
    private HashMap<String, Integer> map = new HashMap<>();

    private void insert(String sentence) {
        TrieNode current = root;
        for (char c : sentence.toCharArray()) {

            int index = c - 'a';

            if (Character.isWhitespace(c)) {
                index = 27;
            }
            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            current = current.children[index];

            if (current.historicalData.stream().noneMatch(x -> x.equals(sentence))) {
                current.historicalData.add(sentence);
            }

            // sort the historical sentences by frequency in decreasing order
            current.historicalData.sort((x, y) -> {
                if (map.get(x).equals(map.get(y))) {
                    return x.compareTo(y);
                }

                return map.get(y) - map.get(x);
            });
        }
    }

    public DesignSearchAutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        readCurrent = root;

        for (int i = 0; i < sentences.length; i++) {
            String sentence = sentences[i];
            int frequency = times[i];

            // save the historical sentence and the corresponding frequency
            map.put(sentence, frequency);
            this.insert(sentence);
        }
    }

    public List<String> input(char c) {

        if (c == '#') {
            String prev = this.previousInput.toString();
            map.put(prev, map.getOrDefault(prev, 0) + 1);
            this.insert(prev);

            // reset the previous string;
            this.previousInput = new StringBuilder();
            this.readCurrent = root;
            return new ArrayList<>();
        }

        this.previousInput.append(c);

        List<String> result = new ArrayList<>();

        int index = c - 'a';
        if (Character.isWhitespace(c)) {
            index = 27;
        }
        if (readCurrent.children[index] == null) {
            return new ArrayList<>();
        }

        readCurrent = readCurrent.children[index];

        // get the top 3 historical data
        for (int i = 0; i < Math.min(3, readCurrent.historicalData.size()); i++) {
            result.add(readCurrent.historicalData.get(i));
        }
        return result;
    }


    private class TrieNode {

        TrieNode[] children;
        List<String> historicalData;

        TrieNode() {
            children = new TrieNode[256];
            historicalData = new ArrayList<>();
        }

    }
}
