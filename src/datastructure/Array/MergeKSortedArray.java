package datastructure.Array;

import java.util.*;

public class MergeKSortedArray {

    public List<Integer> merge(List<List<Integer>> list) {
        List<Integer> res = new ArrayList<>();

        int n = list.size();
        if (n == 0) {
            return res;
        }

        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(x -> list.get(x[0]).get(x[1])));

        for (int i = 0; i < n; i++) {

            if (list.get(i) != null && list.get(i).size() > 0) {
                minHeap.add(new int[]{i, 0});
            }
        }

        while (!minHeap.isEmpty()) {
            int[] coord = minHeap.poll();
            int x = coord[0];
            int y = coord[1];

            int value = list.get(x).get(y);

            res.add(value);

            // list.get(x).size() - 1) is the last element
            if (y != list.get(x).size() - 1) {
                minHeap.add(new int[]{x, y + 1});
            }
        }

        return res;
    }
}
