package leetcode;

// Given a rows x cols screen and a sentence represented by a list of non-empty words,
// find how many times the given sentence can be fitted on the screen.
//
// Note:
//
// A word cannot be split into two lines.
// The order of words in the sentence must remain unchanged.
// Two consecutive words in a line must be separated by a single space.
// Total words in the sentence won't exceed 100.
// Length of each word is greater than 0 and won't exceed 10.
// 1 ≤ rows, cols ≤ 20,000.
// Example 1:
//
// Input:
// rows = 2, cols = 8, sentence = ["hello", "world"]
//
// Output:
// 1
//
// Explanation:
// hello---
// world---
//
// The character '-' signifies an empty space on the screen.
// Example 2:
//
// Input:
// rows = 3, cols = 6, sentence = ["a", "bcd", "e"]
//
// Output:
// 2
//
// Explanation:
// a-bcd-
// e-a---
// bcd-e-
//
// The character '-' signifies an empty space on the screen.
// Example 3:
//
// Input:
// rows = 4, cols = 5, sentence = ["I", "had", "apple", "pie"]
//
// Output:
// 1
//
// Explanation:
// I-had
// apple
// pie-I
// had--
//
// The character '-' signifies an empty space on the screen.
public class SentenceScreenFitting_418 {

  // brute force
  public int wordsTyping(String[] sentence, int rows, int cols) {

    int count = 0;
    int currentRow = 0;  // current col index
    int currentCol = 0; // current row index
    boolean enoughSpace = true;

    while (enoughSpace) {

      for (int i = 0; i < sentence.length; i++) {
        String word = sentence[i];

        if (currentCol + word.length() <= cols) {
          // can fit the word into current line
          currentCol += word.length();
          // add the whitespace
          currentCol += 1;
        } else {

          // cannot fit in the current line
          // change to a new line
          currentRow++;

          if (currentRow >= rows) {
            enoughSpace = false;
            break;
          }

          currentCol = 0;
          if (currentCol + word.length() > cols) {
            // the current word is too long, cannot fit in a single empty line
            enoughSpace = false;
            break;
          }

          currentCol += word.length();
          currentCol += 1;
        }
      }

      if (enoughSpace) {
        count++;
      }

    }

    return count;
  }
}
