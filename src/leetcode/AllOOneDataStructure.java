package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Implement a data structure supporting the following operations:
 *
 * Inc(Key) - Inserts a new key with value 1. Or increments an existing key by 1. Key is guaranteed
 * to be a non-empty string.
 *
 * Dec(Key) - If Key's value is 1, remove it from the data structure. Otherwise decrements an
 * existing key by 1. If the key does not exist, this function does nothing. Key is guaranteed to be
 * a non-empty string.
 *
 * GetMaxKey() - Returns one of the keys with maximal value. If no element exists, return an empty
 * string "".
 *
 * GetMinKey() - Returns one of the keys with minimal value. If no element exists, return an empty
 * string "".
 *
 * Challenge: Perform all these in O(1) startTime complexity.
 */

public class AllOOneDataStructure {

  // all we need is to create an ordered double linked list.
  private class Bucket {

    int value;
    Set<String> keys;

    Bucket prev;
    Bucket next;

    Bucket(int value) {
      this.value = value;
      this.keys = new HashSet<>();

      this.prev = null;
      this.next = null;
    }
  }

  // the hashmap of key and Bucket object
  private Map<String, Bucket> map;

  // the head and tail of double linked list
  private Bucket head;
  private Bucket tail;

  /**
   * Initialize your data structure here.
   */
  public AllOOneDataStructure() {
    this.map = new HashMap<>();
    this.head = null;
    this.tail = null;
  }

  private void updateIncrease(Bucket prev, Bucket current, String key) {
    current.keys.remove(key);
    prev.keys.add(key);
    map.put(key, prev);

    if (current.keys.isEmpty()) {
      // need to remove this current bucket node from the list
      Bucket next = current.next;

      if (next == null) {
        // tail node
        tail = prev;
        tail.next = null;
      } else {
        prev.next = next;
        next.prev = prev;
      }
    } else {
      // update the prev and current links
      prev.next = current;
      current.prev = prev;
    }
  }

  private void updateDecrease(Bucket current, Bucket next, String key) {
    current.keys.remove(key);
    next.keys.add(key);

    if (current.keys.isEmpty()) {
      // need to remove this current bucket node from the list
      Bucket prev = current.prev;

      if (prev == null) {
        // head node
        head = next;
        head.prev = null;

      } else {
        prev.next = next;
        next.prev = prev;
      }

    } else {
      // no need to remove current node,
      // update the current and next links
      current.next = next;
      next.prev = current;
    }

    map.put(key, next);
  }

  /**
   * Inserts a new key with value 1. Or increments an existing key by 1.
   */
  public void inc(String key) {
    if (head == null) {
      // the doubled linked list is empty, need to create one
      head = new Bucket(1);
      head.keys.add(key);

      map.put(key, head);
      tail = head;

      return;
    }

    if (!map.containsKey(key)) {
      // the value of new key is 1,
      // always add to the tail.
      if (tail.value == 1) {
        // if the tail node value is 1,
        // directly add to the keys set.
        tail.keys.add(key);
      } else {
        // otherwise, create a new tail node with value 1
        Bucket newBucket = new Bucket(1);
        newBucket.keys.add(key);

        // update the tail and new bucket links
        newBucket.prev = tail;
        tail.next = newBucket;
        tail = newBucket;
      }

      // don't forget to update the map
      map.put(key, tail);
    } else {
      // increase the value
      Bucket current = map.get(key);
      Bucket prev = current.prev;
      int value = current.value;

      if (prev == null) {
        // update the head node
        head = new Bucket(value + 1);
        updateIncrease(head, current, key);
      } else if (prev.value == value + 1) {
        updateIncrease(prev, current, key);
      } else {
        // insert a new bucket into the list
        Bucket newBucket = new Bucket(value + 1);
        prev.next = newBucket;
        newBucket.prev = prev;

        updateIncrease(newBucket, current, key);
      }
    }
  }


  /**
   * Decrements an existing key by 1. If Key's value is 1, remove it from the data structure.
   */
  public void dec(String key) {

    if (!map.containsKey(key)) {
      return;
    }

    Bucket current = map.get(key);
    Bucket prev = current.prev;
    Bucket next = current.next;
    int value = current.value;

    if (value == 1) {
      // absolute tail node
      current.keys.remove(key);
      map.remove(key);

      if (current.keys.isEmpty()) {
        tail = prev;

        if (tail == null) {
          head = null;
        } else {
          tail.next = null;
        }
      }
    } else if (next == null) {
      Bucket newBucket = new Bucket(value - 1);
      updateDecrease(current, newBucket, key);
      tail = newBucket;
    } else {
      if (next.value == value - 1) {
        updateDecrease(current, next, key);
      } else {
        Bucket newBucket = new Bucket(value - 1);
        newBucket.next = next;
        next.prev = newBucket;

        updateDecrease(current, newBucket, key);
      }
    }
  }

  /**
   * Returns one of the keys with maximal value.
   */
  public String getMaxKey() {

    if (head == null) {
      return "";
    }

    return head.keys.iterator().next();
  }

  /**
   * Returns one of the keys with Minimal value.
   */
  public String getMinKey() {

    if (tail == null) {
      return "";
    }

    return tail.keys.iterator().next();
  }
}

/**
 * Your AllOne object will be instantiated and called as such: AllOne obj = new AllOne();
 * obj.inc(key); obj.dec(key); String param_3 = obj.getMaxKey(); String param_4 = obj.getMinKey();
 */
