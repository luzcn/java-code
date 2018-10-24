package leetcode;

/**
 * Design and implement a data structure for a compressed string iterator. It should support the
 * following operations: next and hasNext.
 *
 * The given compressed string will be in the form of each letter followed by a positive integer
 * representing the number of this letter existing in the original uncompressed string.
 *
 * next() - if the original string still has uncompressed characters, return the next letter;
 * Otherwise return a white space. hasNext() - Judge whether there is any letter needs to be
 * uncompressed.
 *
 * Note: Please remember to RESET your class variables declared in StringIterator, as static/class
 * variables are persisted across multiple test cases. Please see here for more details.
 *
 * Example:
 *
 * StringIterator iterator = new StringIterator("L1e2t1C1o1d1e1");
 *
 * iterator.next(); // return 'L' iterator.next(); // return 'e' iterator.next(); // return 'e'
 * iterator.next(); // return 't' iterator.next(); // return 'C' iterator.next(); // return 'o'
 * iterator.next(); // return 'd' iterator.hasNext(); // return true iterator.next(); // return 'e'
 * iterator.hasNext(); // return false iterator.next(); // return ' '
 */
public class DesignCompressedStringIterator {

  private String compressedString;

  // the iterator index
  private int index;

  // current character that can be returned
  private char current;

  // the number of repeated current character
  private int count;

  public DesignCompressedStringIterator(String compressedString) {
    this.compressedString = compressedString;
    index = 0;
    count = 0;
  }

  public char next() {

    if (!hasNext()) {
      return ' ';
    }

    if (this.count > 0) {
      this.count--;
      return this.current;
    }

    // compute new char and length
    this.current = compressedString.charAt(this.index);
    int i = index + 1;
    int length = 0;
    while (i < this.compressedString.length() && Character
        .isDigit(this.compressedString.charAt(i))) {
      length = length * 10 + Character.getNumericValue(this.compressedString.charAt(i));
      i++;
    }

    index = i;

    // need to -1 here, because we used this character
    this.count = length - 1;

    return this.current;
  }

  public boolean hasNext() {
    return index < this.compressedString.length() || this.count > 0;
  }
}
