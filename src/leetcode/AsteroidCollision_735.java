package leetcode;

import java.util.LinkedList;

// We are given an array asteroids of integers representing asteroids in a row.
//
// For each asteroid, the absolute value represents its size,
// and the sign represents its direction (positive meaning right, negative meaning left).
//
// Each asteroid moves at the same speed.
//
// Find out the state of the asteroids after all collisions.
// - If two asteroids meet, the smaller one will explode.
// - If both are the same size, both will explode.
// - Two asteroids moving in the same direction will never meet.
//
// Example 1:
// Input:
// asteroids = [5, 10, -5]
// Output: [5, 10]
// Explanation:
// The 10 and -5 collide resulting in 10.  The 5 and 10 never collide.
// Example 2:
// Input:
// asteroids = [8, -8]
// Output: []
// Explanation:
// The 8 and -8 collide exploding each other.
// Example 3:
// Input:
// asteroids = [10, 2, -5]
// Output: [10]
// Explanation:
// The 2 and -5 collide resulting in -5.  The 10 and -5 collide resulting in 10.
// Example 4:
// Input:
// asteroids = [-2, -1, 1, 2]
// Output: [-2, -1, 1, 2]
// Explanation:
// The -2 and -1 are moving left, while the 1 and 2 are moving right.
// Asteroids moving the same direction never meet, so no asteroids will meet each other.
public class AsteroidCollision_735 {

  public int[] asteroidCollision(int[] asteroids) {
    if (asteroids.length < 2) {
      return asteroids;
    }

    // use stack
    LinkedList<Integer> stack = new LinkedList<>();

    for (int n : asteroids) {
      if (n > 0) {
        // any positive number cannot case any collision
        stack.push(n);
      } else {
        // indicate if current asteroid is exploded, if not, need to add this value to stack
        boolean exploded = false;
        while (!stack.isEmpty() && stack.getLast() > 0) {

          if (stack.getLast() < 0 - n) {
            stack.pop();
          } else if (stack.getLast() == 0 - n) {
            stack.pop();
            exploded = true;
            break;
          } else {
            exploded = true;
            break;
          }
        }

        if (!exploded) {
          stack.push(n);
        }
      }
    }

    return stack.stream().mapToInt(x -> x).toArray();
  }

  // public int[] asteroidCollision(int[] asteroids) {
  //     int n = asteroids.length;
  //     if (n == 0) {
  //         return new int[0];
  //     }
  //
  //     Deque<Integer> stack = new ArrayDeque<>();
  //
  //     for (int a : asteroids) {
  //         if (a > 0) {
  //             // positive number cannot cause any collision, directly add to the stack
  //             stack.addLast(a);
  //             continue;
  //         }
  //
  //         while (!stack.isEmpty() && stack.getLast() > 0 && stack.getLast() < -a) {
  //
  //             // stack peek element is positive and less than current asteroid size (absolute value)
  //             // need to remove this peek element from the stack
  //             stack.pollLast();
  //         }
  //
  //         if (!stack.isEmpty() || stack.getLast() == -a) {
  //             // if the peek element equals the current asteroid size,
  //             // need to remove from stack
  //             stack.pollLast();
  //         } else if (stack.isEmpty() || stack.getLast() < 0) {
  //
  //             stack.addLast(a);
  //         }
  //     }
  //
  //     return stack.stream().mapToInt(x -> x).toArray();
  // }

}
