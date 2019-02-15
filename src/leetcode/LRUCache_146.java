package leetcode;

// Created by zhenlu on 1/25/17.

import java.util.HashMap;

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

// self-defined double linked list node
public class LRUCache_146 {

  private int capacity;
  private DListNode head;
  private DListNode tail;

  private HashMap<Integer, DListNode> map = new HashMap<>();

  public LRUCache_146(int capacity) {
    this.capacity = capacity;
    head = new DListNode(0, 0);
    tail = new DListNode(0, 0);
    head.next = tail;
    tail.prev = head;
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
    if (map.containsKey(key)) {
      moveToHead(key, value);
    } else {

      if (map.size() == capacity) {
        DListNode toRemove = tail.prev;

        remove(toRemove);
        map.remove(toRemove.key);
      }

      add(key, value);
      map.put(key, head.next);
    }
  }

  private void moveToHead(int key, int value) {
    // remove this node
    // add a new node to the head

    remove(map.get(key));
    add(key, value);
    map.put(key, head.next);
  }


  private void add(int key, int value) {
    DListNode newNode = new DListNode(key, value);

    newNode.next = head.next;
    head.next.prev = newNode;

    newNode.prev = head;
    head.next = newNode;
  }

  private void remove(DListNode node) {

    if (node.prev != null) {
      node.prev.next = node.next;
    }

    if (node.next != null) {
      node.next.prev = node.prev;
    }

    node.prev = null;
    node.next = null;
  }

  class DListNode {

    DListNode prev;
    DListNode next;

    int key;
    int value;

    DListNode(int key, int value) {
      this.key = key;
      this.value = value;

      prev = null;
      next = null;
    }
  }
}
