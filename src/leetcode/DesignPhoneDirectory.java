package leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

// Design a Phone Directory which supports the following operations:
//
// get: Provide a number which is not assigned to anyone.
// check: Check if a number is available or not.
// release: Recycle or release a number.
// Example:
//
// // Init a phone directory containing a total of 3 numbers: 0, 1, and 2.
// PhoneDirectory directory = new PhoneDirectory(3);
//
// // It can return any available phone number. Here we assume it returns 0.
// directory.get();
//
// // Assume it returns 1.
// directory.get();
//
// // The number 2 is available, so return true.
// directory.check(2);
//
// // It returns 2, the only number that is left.
// directory.get();
//
// // The number 2 is no longer available, so return false.
// directory.check(2);
//
// // Release number 2 back to the pool.
// directory.release(2);
//
// // Number 2 is available again, return true.
// directory.check(2);
public class DesignPhoneDirectory {

  // use double linked list to save the available numbers
  // and a hash set to save the used number
  // the construction is O(n), all other operations are O(1), and totally O(n) space

  private LinkedList<Integer> availableNumbers;
  private Set<Integer> usedNumbers;


  /**
   * Initialize your data structure here
   *
   * @param maxNumbers - The maximum numbers that can be stored in the phone directory.
   */
  public DesignPhoneDirectory(int maxNumbers) {
    this.availableNumbers = new LinkedList<>();
    this.usedNumbers = new HashSet<>();

    for (int i = 0; i < maxNumbers; i++) {
      availableNumbers.add(i);
    }

  }

  /**
   * Provide a number which is not assigned to anyone.
   *
   * @return - Return an available number. Return -1 if none is available.
   */
  public int get() {
    if (this.availableNumbers.isEmpty()) {
      return -1;
    }

    int number = availableNumbers.getFirst();
    availableNumbers.removeFirst();

    // add to the used list
    this.usedNumbers.add(number);

    return number;
  }

  /**
   * Check if a number is available or not.
   */
  public boolean check(int number) {
    return !this.usedNumbers.contains(number);
  }

  /**
   * Recycle or release a number.
   */
  public void release(int number) {
    if (check(number)) {
      return;
    }

    // remove the number from used set and put to the last of available list
    this.usedNumbers.remove(number);
    this.availableNumbers.addLast(number);
  }
}
