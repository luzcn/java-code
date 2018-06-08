package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/////
// Design a data structure that supports all following operations in average O(1) startTime.
//
// - insert(val): Inserts an item val to the set if not already present.
// - remove(val): Removes an item val from the set if present.
// - getRandom: Returns a random element from current set of elements.
//
// Each element must have the same probability of being returned.
///
public class RandomizedSet {

    // use two hashmap and one ArrayList, it takes O(1) to remove the last element.

    // private Map<Integer, Integer> mapKeyValue = new HashMap<>();
    private Map<Integer, Integer> mapValueIndex;
    private List<Integer> dataList;
    private Random random;

    /**
     * Initialize your data structure here.
     */
    public RandomizedSet() {
        this.mapValueIndex = new HashMap<>();
        this.dataList = new ArrayList<>();
        this.random = new Random();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (mapValueIndex.containsKey(val)) {
            return false;
        }

        dataList.add(val);
        mapValueIndex.put(val, dataList.size() - 1);

        return true;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        if (!mapValueIndex.containsKey(val)) {
            return false;
        }

        // the solution is:
        // 1. find the index of the value
        // 2. copy the last element to this index, and update the last element in the hashmap
        // 3. remove the last element, remove the val from the hashmap

        // find the index that needs to remove
        int index = mapValueIndex.get(val);

        // if not remove the last element.
        if (index < dataList.size() - 1) {
            // copy the last element to the current need to remove index
            int lastElement = dataList.get(dataList.size() - 1);

            // update the data list
            dataList.set(index, lastElement);

            // update the map
            mapValueIndex.put(lastElement, index);
        }

        // update the map
        mapValueIndex.remove(val);

        // remove the last element
        dataList.remove(dataList.size() - 1);
        return true;
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {

        int value = dataList.get(random.nextInt(dataList.size()));

        return value;
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */