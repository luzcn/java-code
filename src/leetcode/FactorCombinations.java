package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Numbers can be regarded as product of its factors. For example,
 * <p>
 * 8 = 2 x 2 x 2;
 * = 2 x 4.
 * Write a function that takes an integer n and return all possible combinations of its factors.
 * 1. You may assume that n is always positive.
 * 2. Factors should be greater than 1 and less than n.
 */
public class FactorCombinations {


    private void dfs(List<List<Integer>> result, List<Integer> current, int n, int index) {
        if (n == 1) {
            if (current.size() > 1) {
                result.add(new ArrayList<>(current));
            }

            return;
        }

        for (int i = index; i <= n; i++) {
            if (n % i == 0) {
                current.add(i);

                dfs(result, current, n / i, i);

                current.remove(current.size() - 1);
            }
        }
    }

    public List<List<Integer>> getFactors(int n) {

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();

        dfs(result, current, n, 2);

        return result;
    }

}
