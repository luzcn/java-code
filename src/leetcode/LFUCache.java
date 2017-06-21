package leetcode;


// Design and implement a data structure for Least Frequently Used (LFU) cache.
// It should support the following operations: get and put.

//  get(key) - Get the value of the key if the key exists in the cache, otherwise return -1.
//  put(key, value) - Set or insert the value if the key is not already present.
// When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item.
//
// For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency),
// the least recently used key would be evicted.

//Follow up:
//Could you do both operations in O(1) time complexity?

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class LFUCache {

    private int capacity = 0;

    private HashMap<Integer, CacheData> map = new HashMap<Integer, CacheData>();
    private PriorityQueue<CacheData> minHeap = new PriorityQueue<CacheData>(Comparator.comparingInt(v -> v.frequency));


    public LFUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {

        int result = -1;

        if (map.containsKey(key)) {

            CacheData toUpdate = map.get(key);
            result = toUpdate.value;

            // update the frequency
            minHeap.remove(toUpdate);
            map.put(key, new CacheData(key, result, toUpdate.frequency + 1));
            minHeap.add(map.get(key));

        }
        return result;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            CacheData toUpdate = map.get(key);

            // update the frequency
            minHeap.remove(toUpdate);
            map.put(key, new CacheData(key, value, toUpdate.frequency + 1));
            minHeap.add(map.get(key));
        }
        else {
            if (map.size() == this.capacity) {
                CacheData toRemove = minHeap.poll();
                if (toRemove != null)
                    map.remove(toRemove.key);
            }
            map.put(key, new CacheData(key, value, 1));
            minHeap.add(map.get(key));
        }
    }

    class CacheData {
        int key, value, frequency;

        CacheData(int key, int value, int freq) {
            this.key = key;
            this.value = value;
            frequency = freq;
        }
    }
}