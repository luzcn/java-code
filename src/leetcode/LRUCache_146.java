package leetcode;

// Created by zhenlu on 1/25/17.

import java.util.HashMap;
import java.util.LinkedList;

// Design and implement a data structure for Least Recently Used (LRU) cache.
// It should support the following operations: get and put.

// get(key) - Get the value of the key if the key exists in the cache, otherwise return -1.

// put(key, value) - Set or insert the value if the key is not already present.
// When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

// LRUCache_146 cache = new LRUCache_146( 2) /* capacity */;
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

public class LRUCache_146 {

  private int capacity = 0;

  // key, index of listData
  private HashMap<Integer, CacheData> map = new HashMap<>();
  private LinkedList<CacheData> listData = new LinkedList<>();


  public LRUCache_146(int capacity) {

    this.capacity = capacity;
  }

  public int get(int key) {
    if (!map.containsKey(key)) {
      return -1;
    }

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
    } else {
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

// LRU with self-defined Double linked list
// public class LRUCache_146 {
//
//     private int capacity;
//
//     private DListNode head;
//     private DListNode tail;
//     private HashMap<Integer, DListNode> map = new HashMap<>();
//
//     public LRUCache_146(int capacity) {
//         this.capacity = capacity;
//
//         head = new DListNode(0, 0);
//         tail = new DListNode(0, 0);
//         head.next = tail;
//         tail.prev = head;
//
//     }
//
//     public int get(int key) {
//
//         if (map.get(key) == null) {
//             return -1;
//         }
//
//         int value = map.get(key).value;
//         moveToHead(key, value);
//         return value;
//     }
//
//     public void put(int key, int value) {
//
//         if (!map.containsKey(key)) {
//
//             if (map.size() == this.capacity) {
//                 // always remove the node before tail
//                 DListNode toRemove = tail.prev;
//                 remove(toRemove);
//             }
//
//             add(new DListNode(key, value));
//             map.put(key, head.next);
//         } else {
//             moveToHead(key, value);
//         }
//     }
//
//     private void moveToHead(int key, int value) {
//
//         DListNode toRemove = map.get(key);
//         remove(toRemove);
//
//         add(new DListNode(key, value));
//         map.put(key, head.next);
//     }
//
//     // always add this new node after head
//     private void add(DListNode node) {
//
//         node.next = head.next;
//         node.prev = head;
//
//         head.next.prev = node;
//         head.next = node;
//     }
//
//     // always remove the node
//     private void remove(DListNode toRemove) {
//         if (tail.prev == head) {
//             // no data
//             return;
//         }
//
//         DListNode pre = toRemove.prev;
//         DListNode next = toRemove.next;
//
//         pre.next = next;
//         next.prev = pre;
//
//         toRemove.next = null;
//         toRemove.prev = null;
//
//         map.remove(toRemove.key);
//     }
//
//     private class DListNode {
//
//         DListNode prev;
//         DListNode next;
//
//         int key;
//         int value;
//
//
//         DListNode(int key, int value) {
//             this.key = key;
//             this.value = value;
//
//             prev = null;
//             next = null;
//         }
//     }
// }