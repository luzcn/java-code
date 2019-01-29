package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

// You are given several logs that each log contains a unique id and timestamp.
//
// Timestamp is a string that has the following format: Year:Month:Day:Hour:Minute:Second, for example, 2017:01:01:23:59:59.
//
// All domains are zero-padded decimal numbers.
//
// Design a log storage system to implement the following functions:
//
// void Put(int id, string timestamp): Given a log's unique id and timestamp, store the log in your storage system.
//
//
// int[] Retrieve(String start, String end, String granularity):
// Return the id of logs whose timestamps are within the range from start to end.
//
// Start and end all have the same format as timestamp.
// However, granularity means the time level for consideration.
//
// For example, start = "2017:01:01:23:59:59", end = "2017:01:02:23:59:59", granularity = "Day",
// it means that we need to find the logs within the range from Jan. 1st 2017 to Jan. 2nd 2017.
//
// Example 1:
// put(1, "2017:01:01:23:59:59");
// put(2, "2017:01:01:22:59:59");
// put(3, "2016:01:01:00:00:00");
// retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Year");
// return [1,2,3], because you need to return all logs within 2016 and 2017.
//
// retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Hour");
// return [1,2], because you need to return all logs start from 2016:01:01:01 to 2017:01:01:23, where log 3 is left outside the range.
public class DesignLogStorageSystem_635 {


  private TreeMap<Long, Integer> map = new TreeMap<>();

  public DesignLogStorageSystem_635() {

  }

  public void put(int id, String timestamp) {
    int[] st = Arrays.stream(timestamp.split(":")).mapToInt(Integer::parseInt).toArray();

    map.put(convert(st), id);
  }

  public List<Integer> retrieve(String s, String e, String gra) {

    List<Integer> res = new ArrayList<>();

    long start = getGranularity(s, gra);
    long end = getGranularity(e, gra);

    for (long key : map.tailMap(start).keySet()) {
      if (key <= end) {
        res.add(map.get(key));
      }
    }

    return res;
  }


  private long convert(int[] st) {
    st[1] = st[1] - (st[1] == 0 ? 0 : 1);
    st[2] = st[2] - (st[2] == 0 ? 0 : 1);

    return (st[0] - 1999L) * (31 * 12) * 24 * 60 * 60 + st[1] * 31 * 24 * 60 * 60
        + st[2] * 24 * 60 * 60 + st[3] * 60 * 60 + st[4] * 60 + st[5];
  }


  private long getGranularity(String s, String granularity) {

    HashMap<String, Integer> map = new HashMap<>();
    map.put("Year", 0);
    map.put("Month", 1);
    map.put("Day", 2);
    map.put("Hour", 3);
    map.put("Minute", 4);
    map.put("Second", 5);

    String[] st = s.split(":");

    String[] res = new String[6];
    Arrays.fill(res, "00");

    for (int i = 0; i <= map.get(granularity); i++) {
      res[i] = st[i];
    }

    int[] t = Arrays.stream(st).mapToInt(Integer::parseInt).toArray();

    return convert(t);
  }
}
