package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;


/**
 * Find all the shortest transition path
 */
public class WordLadder2 {
    // usually find all problems can use dfs,
    // but here it needs all shortest, so we still have to use bfs
    // first, we need to generate a graph, to save the time, remember we need to add the beginWord to this graph
    // then, go through this graph and find all the shortest path

    private HashSet<String> dict;
    private HashMap<String, Set<String>> graph = new HashMap<>();
    private HashMap<String, Set<String>> path = new HashMap<>();
    private List<List<String>> res = new ArrayList<>();

    // pre-process the given word list to speed up search
    private void buildGraph() {

        for (String word : dict) {
            for (int i = 0; i < word.length(); i++) {

                for (char c = 'a'; c <= 'z'; c++) {
                    if (c == word.charAt(i)) {
                        continue;
                    }

                    String next = word.substring(0, i) + c + word.substring(i + 1);

                    if (dict.contains(next)) {
                        graph.computeIfAbsent(word, k -> new HashSet<>()).add(next);
                    }
                }
            }
        }
    }

    // recursively find all the path
    private void generatePath(String word, List<String> current) {
        current.add(word);

        if (!res.isEmpty() && current.size() > res.get(res.size() - 1).size()) {
            return;
        }

        if (this.path.get(word) == null) {
            this.res.add(new ArrayList<>(current));
            return;
        }

        for (String next : this.path.get(word)) {
            generatePath(next, current);
            current.remove(current.size() - 1);
        }
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        dict = new HashSet<>(wordList);
        dict.add(beginWord);

        buildGraph();

        // bfs find the shortest path, then recursively generate all the path
        HashSet<String> candidate = new HashSet<>();
        candidate.add(endWord);

        HashSet<String> temp = new HashSet<>();
        HashSet<String> visited = new HashSet<>();

        while (!candidate.isEmpty()) {

            visited.addAll(candidate);

            for (String current : candidate) {
                if (current.equals(beginWord)) {
                    // generate path
                    generatePath(current, new ArrayList<>());
                    continue;
                }

                for (String next : graph.getOrDefault(current, new HashSet<>())) {
                    if (!visited.contains(next)) {
                        temp.add(next);

                        this.path.computeIfAbsent(next, k -> new HashSet<>()).add(current);
                    }
                }
            }

            candidate = temp;
            temp = new HashSet<>();
        }

        return res;
    }


    //////////////////////////
    // If only need to return one valid path
    // we can use Hashmap to save the <next, current> pair
    public List<String> findLaddersOnePath(String beginWord, String endWord, List<String> wordList) {
        List<String> res = new ArrayList<>();

        HashSet<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) {
            return res;
        }

        HashMap<String, String> path = new HashMap<>();
        HashSet<String> seen = new HashSet<>();

        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        path.put(beginWord, null);

        while (!queue.isEmpty()) {

            seen.addAll(queue);
            String current = queue.poll();

            for (int i = 0; i < current.length(); i++) {
                for (char c = 'a'; c <= 'z'; c++) {
                    if (c == current.charAt(i)) {
                        continue;
                    }

                    String next = current.substring(0, i) + c + current.substring(i + 1);
                    if (next.equals(endWord)) {
                        path.put(endWord, current);

                        String temp = endWord;
                        while (temp != null) {
                            res.add(temp);
                            temp = path.get(temp);
                        }

                        Collections.reverse(res);
                        return res;
                    } else if (dict.contains(next) && !seen.contains(next)) {
                        queue.add(next);
                        path.put(next, current);
                    }
                }
            }
        }
        return res;
    }
}
