package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomPickIndex {

    Map<Integer, List<Integer>> map = new HashMap<>();
    Random random = new Random();

    public RandomPickIndex(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
    }

    public int pick(int target) {

        List<Integer> indexList = map.get(target);

        int i = random.nextInt(indexList.size());

        return indexList.get(i);
    }
}
