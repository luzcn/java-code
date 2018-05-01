package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.
 *
 * If possible, output any possible result.  If not possible, return the empty string.
 *
 * Example 1:
 *
 * Input: S = "aab"
 * Output: "aba"
 * Example 2:
 *
 * Input: S = "aaab"
 * Output: ""
 * Note:
 *
 * S will consist of lowercase letters and have length in range [1, 500].
 */
public class ReorganizeString {

    // Similar to Rearrange String K distance apart,
    // here k is 1
    public String reorganizeString(String s) {

        if (s == null || s.isEmpty()) {
            return s;
        }

        StringBuilder res = new StringBuilder();
        int n = s.length();

        // count the character frequency
        // put all the character into a max heap, ordered by its corresponding frequency
        HashMap<Character, Integer> map = new HashMap<>();

        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Character> heap = new PriorityQueue<>((c1, c2) -> {
            if (map.get(c1).equals(map.get(c2))) {
                return c1.compareTo(c2);
            }
            return map.get(c2) - map.get(c1);
        });

        // add all character into heap
        heap.addAll(map.keySet());

        while (!heap.isEmpty()) {

            int len = Math.min(n, 2);
            ArrayList<Character> temp = new ArrayList<>();

            for (int i = 0; i < len; i++) {

                if (heap.isEmpty()) {
                    return "";
                }

                char c = heap.poll();
                map.put(c, map.get(c) - 1);
                res.append(c);

                if (map.get(c) > 0) {
                    temp.add(c);
                }

                // finished processing one character, need to decrease the length
                n--;
            }

            heap.addAll(temp);
        }

        return res.toString();
    }
}
