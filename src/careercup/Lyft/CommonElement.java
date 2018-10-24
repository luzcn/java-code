package careercup.Lyft;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// 給兩個排序過的 array
// l1 = [1,2,4,8]
// l2 = [2,8,9]
// 回傳兩個 array 都有的 element, e.g. [2, 8]
//
// 後半：
// 實作一個 CommenElement class ，constructor input 是兩個 sorted iterator，
// 要求實作 hasNext 和 next，假設還有下一個 common element hasNext return true ，
// next 則是回傳 next common element
public class CommonElement implements Iterator<Integer> {

  public int[] findCommonElement(int[] l1, int[] l2) {
    List<Integer> res = new ArrayList<>();

    int i = 0;
    int j = 0;

    while (i < l1.length && j < l2.length) {
      if (l1[i] == l2[j]) {
        res.add(l1[i]);
        i++;
        j++;
      } else if (l1[i] < l2[j]) {
        i++;
      } else {
        j++;
      }
    }

    return res.stream().mapToInt(x -> x).toArray();
  }


  private Iterator<Integer> iterator1;
  private Iterator<Integer> iterator2;

  public CommonElement(Iterator<Integer> iterator1, Iterator<Integer> iterator2) {
    this.iterator1 = iterator1;
    this.iterator2 = iterator2;
  }

  @Override
  public boolean hasNext() {
    return iterator1.hasNext() && iterator2.hasNext();
  }

  @Override
  public Integer next() {

    int v1 = iterator1.next();
    int v2 = iterator2.next();

    while (iterator1 != null && iterator2 != null) {
      if (v1 == v2) {
        return v1;
      } else if (v1 < v2) {
        v1 = iterator1.next();
      } else {
        v2 = iterator2.next();
      }
    }

    return null;
  }
}
