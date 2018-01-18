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

    private Map<Integer, Integer> mapKeyValue;
    private Map<Integer, Integer> mapKeyFrequency;
    private Map<Integer, LinkedHashSet<Integer>> mapFrequencySets;

    private int min;
    private int capacity;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.mapKeyValue = new HashMap<>();
        this.mapKeyFrequency = new HashMap<>();
        this.min = 1;
        this.mapFrequencySets = new HashMap<>();

        mapFrequencySets.put(1, new LinkedHashSet<>());
    }

    public int get(int key) {
        if (!mapKeyValue.containsKey(key)) {
            return -1;
        }

        int value = mapKeyValue.get(key);
        int frequency = mapKeyFrequency.get(key);

        // update frequency
        mapKeyFrequency.put(key, frequency + 1);
        mapFrequencySets.get(frequency).remove(key);

        if (min == frequency && mapFrequencySets.get(frequency).isEmpty()) {
            min++;
        }

        // update frequency and keys map
        if (!mapFrequencySets.containsKey(frequency + 1)) {
            mapFrequencySets.put(frequency + 1, new LinkedHashSet<>());
        }

        mapFrequencySets.get(frequency + 1).add(key);

        return value;
    }

    public void put(int key, int value) {
        if (capacity <= 0)
            return;

        if (mapKeyValue.containsKey(key)) {

            // update key value
            mapKeyValue.put(key, value);

            get(key);

            return;
        }

        if (mapKeyValue.size() >= capacity) {
            // remove the min frequency keys

            int keyToRemove = mapFrequencySets.get(min).iterator().next();
            mapFrequencySets.get(min).remove(keyToRemove);
            mapKeyValue.remove(keyToRemove);
            mapKeyFrequency.remove(keyToRemove);
        }
        mapKeyValue.put(key, value);
        mapKeyFrequency.put(key, 1);
        min = 1;
        mapFrequencySets.get(1).add(key);
    }
}