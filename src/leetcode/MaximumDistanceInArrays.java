package leetcode;

import java.util.List;

/**
 * Given m arrays, and each array is sorted in ascending order.
 * Now you can pick up two integers from two different arrays (each array picks one) and calculate the distance.
 *
 * We define the distance between two integers a and b to be their absolute difference |a-b|.
 * Your task is to find the maximum distance.
 */
public class MaximumDistanceInArrays {

    public int maxDistance(List<List<Integer>> arrays) {
        int result = 0;
        int minValue = arrays.get(0).get(0);
        int maxValue = arrays.get(0).get(arrays.get(0).size() - 1);

        for (int i = 1; i < arrays.size(); i++) {

            int first = arrays.get(i).get(0);
            int last = arrays.get(i).get(arrays.get(i).size() - 1);

            // need to compute in each iteration, because the max and min values cannot come from the same array
            result = Math.max(result, Math.max(Math.abs(first - maxValue), Math.abs(last - minValue)));

            minValue = Math.min(minValue, first);
            maxValue = Math.max(maxValue, last);
        }

        return result;
    }

    // Brute Force solution, without using the sorted feature
    public int maxDistance1(List<List<Integer>> arrays) {
        int result = 0;

        for (int i = 0; i < arrays.size() - 1; i++) {
            for (int j = 0; j < arrays.get(i).size(); j++) {

                for (int k = i + 1; k < arrays.size(); k++) {
                    for (int l = 0; l < arrays.get(k).size(); l++) {
                        result = Math.max(result, Math.abs(arrays.get(i).get(j) - arrays.get(k).get(l)));
                    }
                }
            }
        }
        return result;
    }

    public int maxDistance2(List<List<Integer>> arrays) {
        int result = 0;
        for (int i = 0; i < arrays.size() - 1; i++) {
            for (int j = i + 1; j < arrays.size(); j++) {
                result = Math.max(result, Math.abs(arrays.get(i).get(0) - arrays.get(j).get(arrays.get(j).size() - 1)));
                result = Math.max(result, Math.abs(arrays.get(i).get(arrays.get(i).size() - 1) - arrays.get(j).get(0)));
            }
        }

        return result;
    }
}
