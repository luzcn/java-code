package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Design a data structure that supports all following operations in average O(1) time.
 *
 * Note: Duplicate elements are allowed. insert(val): Inserts an item val to the collection.
 * remove(val): Removes an item val from the collection if present. getRandom: Returns a random
 * element from current collection of elements. The probability of each element being returned is
 * linearly related to the number of same value the collection contains.
 *
 * Example:
 *
 * // Init an empty collection. RandomizedCollection collection = new RandomizedCollection();
 *
 * // Inserts 1 to the collection. Returns true as the collection did not contain 1.
 * collection.insert(1);
 *
 * // Inserts another 1 to the collection. Returns false as the collection contained 1. Collection
 * now contains [1,1]. collection.insert(1);
 *
 * // Inserts 2 to the collection, returns true. Collection now contains [1,1,2].
 * collection.insert(2);
 *
 * // getRandom should return 1 with the probability 2/3, and returns 2 with the probability 1/3.
 * collection.getRandom();
 *
 * // Removes 1 from the collection, returns true. Collection now contains [1,2].
 * collection.remove(1);
 *
 * // getRandom should return 1 and 2 both equally likely. collection.getRandom();
 */

public class RandomizedSetWithDuplicate {

  private Map<Integer, Set<Integer>> map;
  private List<Integer> values;
  private Random random;

  /**
   * Initialize your data structure here.
   */
  public RandomizedSetWithDuplicate() {
    this.map = new HashMap<>();
    this.values = new ArrayList<>();
    this.random = new Random();
  }

  /**
   * Inserts a value to the collection. Returns true if the collection did not already contain the
   * specified element.
   */
  public boolean insert(int val) {
    boolean result = false;

    if (!map.containsKey(val)) {
      result = true;
      map.put(val, new LinkedHashSet<>());
    }

    values.add(val);
    map.get(val).add(values.size() - 1);

    return result;
  }

  /**
   * Removes a value from the collection. Returns true if the collection contained the specified
   * element.
   */
  public boolean remove(int val) {
    if (!map.containsKey(val)) {
      return false;
    }

    // get the first index of the value
    int index = map.get(val).iterator().next();

    // get the last element value
    int lastValue = values.get(values.size() - 1);

    if (val != lastValue) {
      // copy the last value to the index need to remove
      values.set(index, lastValue);

      // update the last value index set
      // 1. remove the last value index, which is the the size() - 1
      // 2. add the index to the last value index set.
      map.get(lastValue).remove(values.size() - 1);
      map.get(lastValue).add(index);

      // remove the last element
      values.remove(values.size() - 1);

      // remove the first index of the given value
      map.get(val).remove(index);
    } else {
      map.get(val).remove(values.size() - 1);
      values.remove(values.size() - 1);
    }

    if (map.get(val).isEmpty()) {
      map.remove(val);
    }

    return true;
  }

  /**
   * Get a random element from the collection.
   */
  public int getRandom() {

    return values.get(random.nextInt(values.size()));
  }
}
