package leetcode;

import java.util.*;


// We are given an elevation map, heights[i] representing the height of the terrain at that index.
// The width at each index is 1. After V units of water fall at index K, how much water is at each index?
//
// Water first drops at index K and rests on top of the highest terrain or water at that index.
// Then, it flows according to the following rules:
//
// - If the droplet would eventually fall by moving left, then move left.
// - Otherwise, if the droplet would eventually fall by moving right, then move right.
// - Otherwise, rise at it's current position.
// - Here, "eventually fall" means that the droplet will eventually be at a lower level if it moves in that direction.
// Also, "level" means the height of the terrain plus any water in that column.
//
// We can assume there's infinitely high terrain on the two sides out of bounds of the array.
// Also, there could not be partial water being spread out evenly on more than 1 grid block
// - each unit of water has to be in exactly one block.
public class PourWater_755 {

    public int[] pourWater(int[] heights, int V, int K) {

        return pourWaterBruteForce(heights, V, K);

    }

    // brute force solution
    private int[] pourWaterBruteForce(int[] heights, int V, int K) {

        while (V-- > 0) {
            int index = K;
            for (int i = K - 1; i >= 0; i--) {
                if (heights[i] > heights[index]) {
                    break;
                } else if (heights[i] < heights[index]) {
                    index = i;
                }
            }

            if (index != K) {
                heights[index]++;
                continue;
            }

            for (int i = K + 1; i < heights.length; i++) {
                if (heights[i] > heights[index]) {
                    break;
                } else if (heights[i] < heights[index]) {
                    index = i;
                }
            }

            heights[index]++;
        }
        return heights;
    }
}
