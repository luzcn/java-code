package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given a non-empty string s and an integer k,
 * rearrange the string such that the same characters are at least distance k from each other.
 *
 * All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string "".
 *
 * Example 1:
 * s = "aabbcc", k = 3
 *
 * Result: "abcabc"
 *
 * The same letters are at least distance 3 from each other.
 * Example 2:
 * s = "aaabc", k = 3
 *
 * Answer: ""
 *
 * It is not possible to rearrange the string.
 * Example 3:
 * s = "aaadbbcc", k = 2
 *
 * Answer: "abacabcd"
 *
 * Another possible answer is: "abcabcda"
 *
 * The same letters are at least distance 2 from each other.
 */
public class RearrangeStringKDistanceApart {

    public String rearrangeString(String s, int k) {
        if (k == 0) {
            return s;
        }

        Map<Character, Integer> map = new HashMap<>();
        // count the frequency of each character
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Character> heap = new PriorityQueue<>((c1, c2) -> {
            if (!map.get(c1).equals(map.get(c2))) {
                return map.get(c2) - map.get(c1);
            } else {
                return c1.compareTo(c2);
            }
        });
        StringBuilder result = new StringBuilder();
        int length = s.length();

        // add all distinct characters into the heap
        heap.addAll(map.keySet());

        while (!heap.isEmpty()) {
            ArrayList<Character> temp = new ArrayList<>();
            int minLength = Math.min(length, k);

            for (int i = 0; i < minLength; i++) {
                if (heap.isEmpty()) {
                    // we cannot insert any distinct characters
                    // directly return
                    return "";
                }

                char c = heap.poll();
                result.append(c);

                // update the hash map
                map.put(c, map.get(c) - 1);

                if (map.get(c) > 0) {
                    temp.add(c);
                }

                // finished processing one character
                // added one char to the result
                // so need to decrease the original string length.
                length--;
            }

            heap.addAll(temp);
        }

        return result.toString();
    }

//    private class Pair<T, U> {
//
//        T count;
//        U character;
//
//        Pair(T cn, U ch) {
//            count = cn;
//            character = ch;
//        }
//    }
}
