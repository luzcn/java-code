package leetcode;

// Created by zhenlu on 1/25/17.

import java.util.HashMap;
import java.util.LinkedList;

// Design and implement a data structure for Least Recently Used (LRU) cache.
// It should support the following operations: get and put.

// get(key) - Get the value of the key if the key exists in the cache, otherwise return -1.

// put(key, value) - Set or insert the value if the key is not already present.
// When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

// LRUCache cache = new LRUCache( 2) /* capacity */;
//
// cache.put(1, 1);
// cache.put(2, 2);
// cache.get(1);       // returns 1
// cache.put(3, 3);    // remove key 2
// cache.get(2);       // returns -1 (not found)
// cache.put(4, 4);    // remove key 1
// cache.get(1);       // returns -1 (not found)

// cache.get(3);       // returns 3
// cache.get(4);       // returns 4

public class LRUCache {
    private int capacity = 0;

    // key, index of listData
    private HashMap<Integer, CacheData> map = new HashMap<>();
    private LinkedList<CacheData> listData = new LinkedList<>();


    public LRUCache(int capacity) {

        this.capacity = capacity;
    }

    public int get(int key) {
        if (!map.containsKey(key))
            return -1;

        int value = map.get(key).value;
        moveToHead(key, value);
        return value;
    }

    public void put(int key, int value) {
        if (!map.containsKey(key)) {
            if (map.size() == capacity) {
                int keyToRemove = listData.peekLast().key;
                listData.removeLast();
                map.remove(keyToRemove);
            }

            listData.addFirst(new CacheData(key, value));
            map.put(key, listData.peekFirst());
        }
        else {
            moveToHead(key, value);
        }

    }

    private void moveToHead(int key, int value) {
        CacheData toRemoveData = map.get(key);
        listData.remove(toRemoveData);

        listData.addFirst(new CacheData(key, value));
        map.put(key, listData.peekFirst());
    }

    private class CacheData {

        int key = 0;
        int value = 0;

        CacheData(int k, int v) {
            this.key = k;
            this.value = v;
        }
    }
}


//class LRUCache<K, V> extends LinkedHashMap<K, V> {
//    private  int capacity;
//
//    @Override
//    protected boolean removeEldestEntry(Map.Entry<K,V> eldest){
//        return size() > capacity;
//    }
//
//    LRUCache(int cap) {
//        super();
//        this.capacity = cap;
//    }
//}

// class LRUCache {
//
//     private DNode head;
//     private DNode tail;
//     private int capacity;
//     private HashMap<Integer, DNode> map = new HashMap<>();
//
//     public LRUCache(int capacity) {
//         head = null;
//         tail = null;
//         this.capacity = capacity;
//     }
//
//     public int get(int key) {
//         if (map.get(key) == null) {
//             return -1;
//         }
//
//         DNode node = map.get(key);
//         int value = node.value;
//
//         remove(node);
//         add(node);
//
//         return value;
//     }
//
//     public void put(int key, int value) {
//         if (this.capacity  <= 0) {
//             return;
//         }
//
//         if (map.size() == 1) {
//             tail = head;
//         }
//
//
//     }
//
//
//     private class DNode {
//         DNode prev;
//         DNode next;
//
//         int key;
//         int value;
//
//         DNode(int k, int v) {
//             key = k;
//             value = v;
//         }
//
//     }
//
//     private void add(DNode node) {
//         node.next = head;
//         head = node;
//     }
//
//     private void remove(DNode node) {
//         if (node.prev != null ) {
//             node.prev.next = next;
//         }
//
//         if (node.next != null) {
//             node.next.prev = node.prev;
//         }
//
//         node.next = null;
//         node.prev = null;
//     }
// }
