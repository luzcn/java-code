package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

// Given two sentences words1, words2 (each represented as an array of strings),
// and a list of similar word pairs pairs, determine if two sentences are similar.
//
// For example, words1 = ["great", "acting", "skills"] and words2 = ["fine", "drama", "talent"] are similar,
// if the similar word pairs are pairs = [["great", "good"], ["fine", "good"], ["acting","drama"], ["skills","talent"]].
//
//Note that the similarity relation is transitive.
// For example, if "great" and "good" are similar, and "fine" and "good" are similar, then "great" and "fine" are similar.
//
// Similarity is also symmetric. For example, "great" and "fine" being similar is the same as "fine" and "great" being similar.
//
// Also, a word is always similar with itself.
// For example, the sentences words1 = ["great"], words2 = ["great"], pairs = [] are similar,
// even though there are no specified similar word pairs.
//
//Finally, sentences can only be similar if they have the same number of words.
// So a sentence like words1 = ["great"] can never be similar to words2 = ["doubleplus","good"].
public class SentenceSimilarity_737 {

  // dfs solution
  public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {

    if (words1.length != words2.length) {
      return false;
    }

    for (String[] p : pairs) {
      graph.computeIfAbsent(p[0], k -> new ArrayList<>()).add(p[1]);
      graph.computeIfAbsent(p[1], k -> new ArrayList<>()).add(p[0]);
    }

    int n = words1.length;
    for (int i = 0; i < n; i++) {
      String w1 = words1[i];
      String w2 = words2[i];

      if (!dfs(w1, w2, new HashSet<>())) {
        return false;
      }
    }

    return true;
  }

  private HashMap<String, List<String>> graph = new HashMap<>();

  private boolean dfs(String node, String target, HashSet<String> visited) {
    if (node.equals(target)) {
      return true;
    }

    if (visited.contains(node)) {
      return false;
    }

    visited.add(node);

    for (String next : graph.getOrDefault(node, new ArrayList<>())) {
      if (dfs(next, target, visited)) {
        return true;
      }
    }

    return false;
  }


  // union-find solution
  public boolean areSentencesSimilarTwoUF(String[] words1, String[] words2, String[][] pairs) {

    if (words1.length != words2.length) {
      return false;
    }

    for (String[] p : pairs) {
      String w1 = p[0];
      String w2 = p[1];

      // make each node as the root of itself first
      root.putIfAbsent(w1, w1);
      root.putIfAbsent(w2, w2);

      // update the root relation
      root.put(getRoot(w1), getRoot(w2));
    }

    int n = words1.length;
    for (int i = 0; i < n; i++) {
      String w1 = words1[i];
      String w2 = words2[i];

      if (w1.equals(w2)) {
        continue;
      }

      if (getRoot(w1) == null || getRoot(w2) == null) {
        return false;
      }

      if (!getRoot(w1).equals(getRoot(w2))) {
        return false;
      }
    }

    return true;
  }

  private HashMap<String, String> root = new HashMap<>();

  private String getRoot(String s) {
    if (root.get(s) == null) {
      return null;
    }

    while (!s.equals(root.get(s))) {
      s = root.get(s);
    }

    return s;
  }
}

//    public static void main(String[] args) {
//        SentenceSimilarity_737 sm = new SentenceSimilarity_737();
//
//        String[] words1 = {"this", "summer", "thomas", "get", "really", "very", "rich", "and", "have", "any", "actually", "wonderful", "and", "good", "truck", "every", "morning", "he", "drives", "an", "extraordinary", "truck", "around", "the", "nice", "city", "to", "eat", "some", "extremely", "extraordinary", "food", "as", "his", "meal", "but", "he", "only", "entertain", "an", "few", "well", "fruits", "as", "single", "lunch", "he", "wants", "to", "eat", "single", "single", "and", "really", "healthy", "life"};
//        String[] words2 = {"this", "summer", "thomas", "get", "very", "extremely", "rich", "and", "possess", "the", "actually", "great", "and", "wonderful", "vehicle", "every", "morning", "he", "drives", "unique", "extraordinary", "automobile", "around", "unique", "fine", "city", "to", "drink", "single", "extremely", "nice", "meal", "as", "his", "super", "but", "he", "only", "entertain", "a", "few", "extraordinary", "food", "as", "some", "brunch", "he", "wants", "to", "take", "any", "some", "and", "really", "healthy", "life"};
//        String[][] pairs = {{"good", "nice"}, {"good", "excellent"}, {"good", "well"}, {"good", "great"}, {"fine", "nice"}, {"fine", "excellent"}, {"fine", "well"}, {"fine", "great"}, {"wonderful", "nice"}, {"wonderful", "excellent"}, {"wonderful", "well"}, {"wonderful", "great"}, {"extraordinary", "nice"}, {"extraordinary", "excellent"}, {"extraordinary", "well"}, {"extraordinary", "great"}, {"one", "a"}, {"one", "an"}, {"one", "unique"}, {"one", "any"}, {"single", "a"}, {"single", "an"}, {"single", "unique"}, {"single", "any"}, {"the", "a"}, {"the", "an"}, {"the", "unique"}, {"the", "any"}, {"some", "a"}, {"some", "an"}, {"some", "unique"}, {"some", "any"}, {"car", "vehicle"}, {"car", "automobile"}, {"car", "truck"}, {"auto", "vehicle"}, {"auto", "automobile"}, {"auto", "truck"}, {"wagon", "vehicle"}, {"wagon", "automobile"}, {"wagon", "truck"}, {"have", "take"}, {"have", "drink"}, {"eat", "take"}, {"eat", "drink"}, {"entertain", "take"}, {"entertain", "drink"}, {"meal", "lunch"}, {"meal", "dinner"}, {"meal", "breakfast"}, {"meal", "fruits"}, {"super", "lunch"}, {"super", "dinner"}, {"super", "breakfast"}, {"super", "fruits"}, {"food", "lunch"}, {"food", "dinner"}, {"food", "breakfast"}, {"food", "fruits"}, {"brunch", "lunch"}, {"brunch", "dinner"}, {"brunch", "breakfast"}, {"brunch", "fruits"}, {"own", "have"}, {"own", "possess"}, {"keep", "have"}, {"keep", "possess"}, {"very", "super"}, {"very", "actually"}, {"really", "super"}, {"really", "actually"}, {"extremely", "super"}, {"extremely", "actually"}};
//
//        System.out.println(sm.areSentencesSimilarTwoUF(words1, words2, pairs));
//    }
