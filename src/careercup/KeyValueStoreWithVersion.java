package careercup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

// 多位kv store，大家可能在地理见到过，没有的话这里讲一下是个常见题，就是一个key可以有好多version的值，
// 然后给一个key和一个version，要求你给出大于等于这个version之后的值，如果没有给version的话就是最后的值。
public class KeyValueStoreWithVersion {

  // Hashmap + treemap

  private HashMap<Integer, TreeMap<Integer, List<Integer>>> mapKeyVersionedValue;

  public KeyValueStoreWithVersion() {
    this.mapKeyVersionedValue = new HashMap<>();
  }

  public void put(int key, int value, int version) {

    if (mapKeyVersionedValue.get(key) == null) {
      mapKeyVersionedValue.put(key, new TreeMap<>());
    }
    mapKeyVersionedValue.get(key).computeIfAbsent(version, k -> new ArrayList<>()).add(value);
  }

  public List<Integer> get(int key) {
    if (mapKeyVersionedValue.get(key) == null) {
      return new ArrayList<>();
    }

    return mapKeyVersionedValue.get(key).lastEntry().getValue();
  }

  public List<Integer> get(int key, int version) {
    if (mapKeyVersionedValue.get(key) == null) {
      return new ArrayList<>();
    }

    SortedMap<Integer, List<Integer>> s = mapKeyVersionedValue.get(key).tailMap(version);

    List<Integer> res = new ArrayList<>();
    for (List<Integer> values : s.values()) {
      res.addAll(values);
    }

    return res;
  }
}
