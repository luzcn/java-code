package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Implement a data structure supporting the following operations:
 * <p>
 * Inc(Key) - Inserts a new key with value 1. Or increments an existing key by 1. Key is guaranteed to be a non-empty string.
 * <p>
 * Dec(Key) - If Key's value is 1, remove it from the data structure.
 * Otherwise decrements an existing key by 1.
 * If the key does not exist, this function does nothing. Key is guaranteed to be a non-empty string.
 * <p>
 * GetMaxKey() - Returns one of the keys with maximal value. If no element exists, return an empty string "".
 * <p>
 * GetMinKey() - Returns one of the keys with minimal value. If no element exists, return an empty string "".
 * Challenge: Perform all these in O(1) time complexity.
 */
public class AllOOneDataStructure {
    private Map<String, Integer> map = new HashMap<>();
    private Stack<PairData> stackMax = new Stack<>();
    private Stack<PairData> stackMin = new Stack<>();

    /**
     * Initialize your data structure here.
     */
    public AllOOneDataStructure() {

    }

    /**
     * Inserts a new key <Key> with value 1. Or increments an existing key by 1.
     */
    public void inc(String key) {
        if (map.containsKey(key)) {
            map.put(key, map.get(key) + 1);
        } else {
            map.put(key, 1);
        }
    }

    /**
     * Decrements an existing key by 1. If Key's value is 1, remove it from the data structure.
     */
    public void dec(String key) {
        if (!map.containsKey(key))
            return;

        if (map.get(key) == 1) {
            map.remove(key);
            return;
        }

        map.put(key, map.get(key) - 1);
    }

    /**
     * Returns one of the keys with maximal value.
     */
    public String getMaxKey() {
        return null;
    }

    /**
     * Returns one of the keys with Minimal value.
     */
    public String getMinKey() {
        return null;
    }

    private class PairData {
        String key;
        int value;

        public PairData(String k, int v) {
            this.key = k;
            this.value = v;
        }
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
