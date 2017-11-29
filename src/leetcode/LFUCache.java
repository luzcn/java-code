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
//Could you do both operations in O(1) time complexity?

public class LFUCache {

    private int capacity = 0;

    public void put(int key, int value) {

    }

    public int get(int key) {
        return 0;
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