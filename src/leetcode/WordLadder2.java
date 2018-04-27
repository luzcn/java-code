package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Find all the shortest transition path
 */
public class WordLadder2 {
    // usually find all problems can use dfs,
    // but here it needs all shortest, so we still have to use bfs
    // first, we need to generate a graph
    // then, go through this graph and find all the shortest path

    public Map<String, Set<String>> graph = new HashMap<>();
    // public Map<String, Set<String>> parent = new HashMap<>();
    private Set<String> wordSet = new HashSet<>();
    private List<List<String>> result = new ArrayList<>();

    private void constructGraph() {
        for (String word : wordSet) {

            for (int i = 0; i < word.length(); i++) {
                char old = word.charAt(i);

                for (char c = 'a'; c <= 'z'; c++) {
                    if (c == old) {
                        continue;
                    }

                    String newWord = word.substring(0, i) + c + word.substring(i + 1);
                    if (this.wordSet.contains(newWord)) {
                        graph.computeIfAbsent(word, k -> new HashSet<>()).add(newWord);
                    }
                }
            }
        }
    }


    // recursively generate the path
    private void generatePath(String word, Map<String, Set<String>> parent, List<String> path) {
        path.add(word);

        if (!result.isEmpty() && path.size() > result.get(result.size() - 1).size()) {
            return;
        }

        if (!parent.containsKey(word)) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (String str : parent.getOrDefault(word, new HashSet<>())) {
            generatePath(str, parent, path);

            path.remove(path.size() - 1);
        }

    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        if (wordList.isEmpty() || wordList.stream().noneMatch(x -> x.equals(endWord))) {
            return new ArrayList<>();
        }

        this.wordSet.addAll(wordList);
        wordSet.add(beginWord);

        constructGraph();

        Map<String, Set<String>> parent = new HashMap<>();

        // bfs candidate
        Set<String> currentLevelCandidates = new HashSet<>();
        Set<String> nextLevelCandidates = new HashSet<>();

        // check if visited
        Set<String> visited = new HashSet<>();

        // start from the end word
        currentLevelCandidates.add(endWord);

        while (!currentLevelCandidates.isEmpty()) {
            // mark all the nodes on the same level as visited
            visited.addAll(currentLevelCandidates);

            for (String s : currentLevelCandidates) {

                if (s.equals(beginWord)) {
                    generatePath(s, parent, new ArrayList<>());
                    continue;
                }

                for (String word : this.graph.getOrDefault(s, new HashSet<>())) {
                    if (!visited.contains(word)) {

                        // add the word, which is considered as the parent of s
                        nextLevelCandidates.add(word);

                        // build this parent mapping
                        parent.computeIfAbsent(word, k -> new HashSet<>()).add(s);
                    }
                }
            }

            currentLevelCandidates = nextLevelCandidates;
            nextLevelCandidates = new HashSet<>();
        }

        return this.result;
    }
}
