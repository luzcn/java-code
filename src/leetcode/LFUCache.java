package leetcode;

// Design and implement a data structure for Least Frequently Used (LFU) cache.
// It should support the following operations: get and put.
//
//  get(key) - Get the value of the key if the key exists in the cache, otherwise return -1.
//  put(key, value) - Set or insert the value if the key is not already present.
//
// When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item.
//
// For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency),
// the least recently used key would be evicted.

//Follow up:
//Could you do both operations in O(1) startTime complexity?

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class LFUCache {

    private int cap;

    // key -> value hashmap
    private Map<Integer, Integer> mapKeyValue = new HashMap<>();

    // key -> frequency hashmap
    private Map<Integer, Integer> mapKeyFrequency = new HashMap<>();

    // frequency -> list of keys hahsmap
    private Map<Integer, LinkedHashSet<Integer>> mapFrequencyKeySet = new HashMap<>();

    // the least frequency
    private int min;

    public LFUCache(int capacity) {
        this.cap = capacity;
        this.min = 1;
    }

    private void updateFrequency(int key) {
        // get the frequency of this key
        int frequency = mapKeyFrequency.get(key);

        // increase frequency by 1
        mapKeyFrequency.put(key, frequency + 1);

        // remove this key from the frequency mapping list
        mapFrequencyKeySet.get(frequency).remove(key);

        // and add the key to new frequency mapping list
        mapFrequencyKeySet.computeIfAbsent(frequency + 1, k -> new LinkedHashSet<>()).add(key);

        // update the min value
        if (min == frequency && mapFrequencyKeySet.get(frequency).isEmpty()) {
            // mapFrequencyKeySet.remove(frequency);
            min++;
        }
    }

    public int get(int key) {
        if (!mapKeyValue.containsKey(key)) {
            return -1;
        }

        // get value
        int value = mapKeyValue.get(key);

        this.updateFrequency(key);

        return value;
    }

    public void put(int key, int value) {

        if (this.cap <= 0) {
            return;
        }

        if (mapKeyValue.containsKey(key)) {
            mapKeyValue.put(key, value);

            this.updateFrequency(key);

            return;
        }

        if (mapKeyValue.size() >= this.cap) {
            // remove the leaset frequency key,
            // if there are multiple keys, remove the first inserted
            int keyToRemove = mapFrequencyKeySet.get(min).iterator().next();
            mapFrequencyKeySet.get(min).remove(keyToRemove);

            mapKeyValue.remove(keyToRemove);
            mapKeyFrequency.remove(keyToRemove);
        }

        mapKeyValue.put(key, value);
        mapKeyFrequency.put(key, 1);

        mapFrequencyKeySet.computeIfAbsent(1, k -> new LinkedHashSet<>()).add(key);
        min = 1;
    }
}