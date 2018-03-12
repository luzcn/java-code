package leetcode;

import java.util.HashMap;
import java.util.List;

/**
 * There is a brick wall in front of you.
 * The wall is rectangular and has several rows of bricks.
 * The bricks have the same height but different width.
 *
 * You want to draw a vertical line from the top to the bottom and cross the least bricks.
 *
 * The brick wall is represented by a list of rows.
 * Each row is a list of integers representing the width of each brick in this row from left to right.
 *
 * If your line go through the edge of a brick, then the brick is not considered as crossed.
 * You need to find out how to draw the line to cross the least bricks and return the number of crossed bricks.
 *
 * You cannot draw a line just along one of the two vertical edges of the wall, in which case the line will obviously cross no bricks.
 */
public class BrickWall {
    
    // Use a hasmap to save the line breaks
    // the key is the bricks width summary, value is count
    // if we find a summary in hashmap, it means there is a line break in the wall
    // so, wall.size() - the hashmap value is the cross bricks.
    public int leastBricks(List<List<Integer>> wall) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int result = wall.size();
        
        for (List<Integer> row : wall) {
            int sum = 0;
            for (int i = 0; i < row.size() - 1; i++) {
                sum += row.get(i);
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }
        
        for (int value : map.values()) {
            result = Math.min(result, wall.size() - value);
        }
        
        return result;
    }
    //
    // HashMap < Integer, Integer > map = new HashMap < > ();
    //     for (List < Integer > row: wall) {
    //     int sum = 0;
    //     for (int i = 0; i < row.size() - 1; i++) {
    //         sum += row.get(i);
    //         if (map.containsKey(sum))
    //             map.put(sum, map.get(sum) + 1);
    //         else
    //             map.put(sum, 1);
    //     }
    // }
    // int res = wall.size();
    //     for (int key: map.keySet())
    // res = Math.min(res, wall.size() - map.get(key));
    //     return res;
    
    // Brute Force solution
    // sweep the line step by step
    // - use an array pos[wall.size()] to keep track the position of each row
    // - if we find row.get(pos[i]] is not 0, increase the count
    // - otherwise, increase the position, pos[i]++
    // after swept 1 step, we need to remove 1 width brick from the original wall
    //
    public int leastBricksBF(List<List<Integer>> wall) {
        
        
        int[] pos = new int[wall.size()];
        int result = Integer.MAX_VALUE;
        
        int totalWidth = 0;
        for (int w : wall.get(0)) {
            totalWidth += w;
        }
        
        while (totalWidth > 0) {
            int count = 0;
            
            for (int i = 0; i < wall.size(); i++) {
                List<Integer> row = wall.get(i);
                
                if (row.get(pos[i]) != 0) {
                    count++;
                } else {
                    pos[i]++;
                }
                
                // need to minus 1 from the brick width
                // because we finished 1 step sweep
                row.set(pos[i], row.get(pos[i]) - 1);
                
            }
            
            result = Math.min(result, count);
            totalWidth--;
        }
        return result;
    }
}
