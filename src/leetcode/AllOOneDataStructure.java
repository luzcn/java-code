package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

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

    private HashMap<String, Bucket> map;
    private Bucket head;
    private Bucket tail;

    private void updateIncrease(Bucket prev, Bucket current, String key) {
        prev.keys.add(key);
        current.keys.remove(key);

        if (current.keys.isEmpty()) {
            prev.next = current.next;

            if (current.next != null) {
                // not tail node
                current.next.prev = prev;
            } else {
                tail = prev;
            }
        } else {
            prev.next = current;
            current.prev = prev;
        }

        map.put(key, prev);
    }

    private void updateDecrease(Bucket current, Bucket next, String key) {
        next.keys.add(key);
        current.keys.remove(key);

        if (current.keys.isEmpty()) {
            // need to remove the current node
            if (current.prev == null) {
                // head node
                head = next;
                current.next = null;
            } else {
                current.prev.next = next;
                next.prev = current.prev;
            }
        } else {
            current.next = next;
            next.prev = current;
        }

        map.put(key, next);
    }

    /**
     * Initialize your data structure here.
     */
    public AllOOneDataStructure() {
        map = new HashMap<>();
        tail = null;
        head = null;
    }

    /**
     * Inserts a new key <Key> with value 1. Or increments an existing key by 1.
     */
    public void inc(String key) {

        if (head == null) {
            head = new Bucket(1);
            head.keys.add(key);

            map.put(key, head);
            tail = head;

            return;
        }

        if (!map.containsKey(key)) {
            // add to tail if the the tail has value 1
            // or, create a new bucket with value 1 as the new tail
            if (tail.value == 1) {
                tail.keys.add(key);
                map.put(key, tail);
            } else {
                Bucket newBucket = new Bucket(1);
                newBucket.keys.add(key);

                // update the tail node
                newBucket.prev = tail;
                tail.next = newBucket;
                tail = newBucket;

                map.put(key, newBucket);
            }
        } else {
            // contains the key,
            Bucket current = map.get(key);
            int value = current.value;

            // the key is in the head bucket, need to create a new head.
            if (current == head) {
                head = new Bucket(value + 1);
                updateIncrease(head, current, key);
            } else {
                // not head
                // 1. move the key to the prev bucket, if the prev value equals current value + 1
                Bucket prev = current.prev;
                if (prev.value == current.value + 1) {
                    updateIncrease(prev, current, key);
                } else {
                    // 2. insert a new bucket with current value + 1
                    Bucket newBucket = new Bucket(value + 1);
                    prev.next = newBucket;
                    newBucket.prev = prev;

                    updateIncrease(newBucket, current, key);
                }
            }
        }

    }


    /**
     * Decrements an existing key by 1. If Key's value is 1, remove it from the data structure.
     */
    public void dec(String key) {
        if (!map.containsKey(key))
            return;

        Bucket current = map.get(key);
        Bucket prev = current.prev;
        Bucket next = current.next;
        int value = current.value;

        if (value == 1) {
            // it should be tail node
            current.keys.remove(key);
            map.remove(key);

            if (current.keys.isEmpty()) {
                tail = prev;

                if (tail == null)
                    head = null;
                else {
                    tail.next = null;
                    current.prev = null;
                    current.next = null;
                }
            }

            return;
        }

        if (next == null) {
            // tail node
            tail = new Bucket(value - 1);
            updateDecrease(current, tail, key);
        } else {
            // not tail node
            // 1. if next node has value - 1
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
        if (head == null || head.keys.isEmpty()) {
            return "";
        }
        return head.keys.iterator().next();
    }

    /**
     * Returns one of the keys with Minimal value.
     */
    public String getMinKey() {
        if (tail == null || tail.keys.isEmpty()) {
            return "";
        }

        return tail.keys.iterator().next();
    }

    private class Bucket {

        int value;
        Set<String> keys;

        Bucket prev;
        Bucket next;

        public Bucket(int val) {
            this.value = val;
            keys = new HashSet<>();
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
