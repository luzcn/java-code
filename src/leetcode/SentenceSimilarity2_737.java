package leetcode;

import java.util.*;

public class SentenceSimilarity2_737 {

    private HashMap<String, HashSet<String>> map = new HashMap<>();
    private HashSet<String> visited = new HashSet<>();

    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
        if (words1.length != words2.length) {
            return false;
        }
        int n = words1.length;

        for (String[] pair : pairs) {
            map.computeIfAbsent(pair[0], k -> new HashSet<>()).add(pair[1]);
            map.computeIfAbsent(pair[1], k -> new HashSet<>()).add(pair[0]);
        }

        for (int i = 0; i < n; i++) {
            if (!words1[i].equals(words2[i]) && (!dfs(words1[i], words2[i]))) {
                return false;
            }
        }

        return true;
    }

    private boolean dfs(String node, String target) {
        if (node.equals(target)) {
            return true;
        }

        if (visited.contains(node)) {
            return false;
        }

        visited.add(node);

        for (String next : map.getOrDefault(node, new HashSet<>())) {

            if (dfs(next, target)) {
                return true;
            }
        }

        visited.remove(node);
        return false;
    }
}
