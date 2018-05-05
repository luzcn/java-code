package careercup;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Given an array, write a program to generate a random permutation of array elements.
 *
 * This question is also asked as “shuffle a deck of cards” or “randomize a given array”.
 * https://www.geeksforgeeks.org/shuffle-a-given-array/
 */
public class ShuffleArray {

    private Random random = new Random();


    // A simple solution is to create an auxiliary array temp[] which is initially a copy of nums[].
    // - Randomly select an element from temp[],
    // - copy the randomly selected element to arr[0] and remove the selected element from temp[].
    //
    // Repeat the same process n times and keep copying elements to arr[1], arr[2], … .
    //
    // The time complexity of this solution will be O(n^2).
    public void shuffleBruteForce(int[] nums) {
        List<Integer> temp = new LinkedList<>();

        temp.addAll(Arrays.stream(nums).boxed().collect(Collectors.toList()));

        for (int i = 0; i < nums.length; i++) {
            int index = random.nextInt(temp.size());

            // randomly get an element from the temp array
            // assign the element to the nums[i],
            // then, remove the element from the temp array
            nums[i] = temp.get(index);
            temp.remove(index);
        }

    }

    // Fisher–Yates shuffle Algorithm works in O(n) time complexity.
    //
    // The assumption here is, we are given a function rand() that generates random number in O(1) time.
    //
    // The idea is to start from the last element, swap it with a randomly selected element from the whole array (including last).
    //
    // Now consider the array from 0 to n-2 (size reduced by 1), and repeat the process till we hit the first element.
    public void shuffle(int[] nums) {
        for (int i = nums.length - 1; i > 0; i--) {
            int j = random.nextInt(i);
            swap(nums, i, j);
        }
    }

    private void swap(int[] nums, int i, int j) {
        if (i == j) {
            return;
        }

        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
