package careercup;

import java.util.ArrayList;
import java.util.List;

/**
 * facebook-interview-questions1
 *
 * Interleave list of lists in Java Example: input = [[1,2,3], [9, 0], [5], [-4,-5,-2,-3,-1]];
 * output = [1,9,5,-4,2,0,-5,3,-2,-3,-1]
 */
public class InterleaveListOfList {

  public List<Integer> interleave(List<List<Integer>> dataList) {
    if (dataList == null || dataList.isEmpty()) {
      return null;
    }

    int maxLength = dataList.stream().mapToInt(List::size).max().getAsInt();
    List<Integer> result = new ArrayList<>();

    for (int i = 0; i < maxLength; i++) {
      for (List<Integer> data : dataList) {

        if (data == null || data.size() <= i) {
          continue;
        }

        result.add(data.get(i));
      }
    }

    return result;
  }
}
