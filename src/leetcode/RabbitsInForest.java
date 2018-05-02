package leetcode;

import java.util.Arrays;

/**
 * In a forest, each rabbit has some color.
 * Some subset of rabbits (possibly all of them) tell you how many other rabbits have the same color as them.
 *
 * Those answers are placed in an array.
 *
 * Return the minimum number of rabbits that could be in the forest.
 *
 * Examples:
 * Input: answers = [1, 1, 2]
 * Output: 5
 * Explanation:
 * The two rabbits that answered "1" could both be the same color, say red.
 * The rabbit than answered "2" can't be red or the answers would be inconsistent.
 *
 * Say the rabbit that answered "2" was blue.
 * Then there should be 2 other blue rabbits in the forest that didn't answer into the array.
 * The smallest possible number of rabbits in the forest is therefore 5: 3 that answered plus 2 that didn't.
 *
 * Input: answers = [10, 10, 10]
 * Output: 11
 *
 * Input: answers = []
 * Output: 0
 */
public class RabbitsInForest {

    // Each rabbit that says a different number must be a different color,
    // and a rabbit only tells us information about rabbits of its color.
    // We can count the number of rabbits of each color separately.
    public int numRabbits(int[] answers) {

        if (answers.length == 0) {
            return 0;
        }

        // sort the array
        Arrays.sort(answers);

        int pre = answers[0];
        int current = pre;
        int sum = 0;

        for (int i = 1; i < answers.length; i++) {
            if (answers[i] == pre) {
                // find same value,
                // these two could be grouped in one color

                if (--current < 0) {
                    current = answers[i];
                    sum += pre + 1;
                }
            } else {
                sum += pre + 1;
                current = answers[i];
            }

            pre = answers[i];
        }

        sum += pre + 1;
        return sum;
    }
}
