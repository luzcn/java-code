package leetcode;

import java.util.ArrayList;
import java.util.List;


// Given an array of words and a width maxWidth,
// format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.
//
// You should pack your words in a greedy approach; that is,
// pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.
//
// Extra spaces between words should be distributed as evenly as possible.
//
// If the number of spaces on a line do not divide evenly between words,
// the empty slots on the left will be assigned more spaces than the slots on the right.
//
// For the last line of text, it should be left justified and no extra space is inserted between words.
//
// Note:
//
// - A word is defined as a character sequence consisting of non-space characters only.
// - Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
// The input array words contains at least one word.
//
// Example 1:
//
// Input:
// words = ["This", "is", "an", "example", "of", "text", "justification."]
// maxWidth = 16
// Output:
// [
//    "This    is    an",
//    "example  of text",
//    "justification.  "
// ]
// Example 2:
//
// Input:
// words = ["What","must","be","acknowledgment","shall","be"]
// maxWidth = 16
// Output:
// [
//   "What   must   be",
//   "acknowledgment  ",
//   "shall be        "
// ]
// Explanation: Note that the last line is "shall be    " instead of "shall     be",
//              because the last line must be left-justified instead of fully-justified.
//              Note that the second line is also left-justified becase it contains only one word.
// Example 3:
//
// Input:
// words = ["Science","is","what","we","understand","well","enough","to","explain",
//          "to","a","computer.","Art","is","everything","else","we","do"]
// maxWidth = 20
// Output:
// [
//   "Science  is  what we",
//   "understand      well",
//   "enough to explain to",
//   "a  computer.  Art is",
//   "everything  else  we",
//   "do                  "
// ]
public class TextJustification_68 {

  public List<String> fullJustify(String[] words, int maxWidth) {
    List<String> res = new ArrayList<>();
    if (words.length == 0 || maxWidth <= 0) {
      return res;
    }

    int index = 0;
    while (index < words.length) {
      int wordLength = words[index].length();
      int last = index + 1;

      // find the max words that can fit in one line
      while (last < words.length) {
        if (wordLength + words[last].length() + 1 > maxWidth) {
          break;
        }

        wordLength += words[last].length() + 1;
        last++;
      }

      StringBuilder sb = new StringBuilder();
      int wordCount = last - index - 1;

      if (last == words.length || wordCount == 0) {
        // a single word or processing the last line, need to left-justified
        for (int i = index; i < last; i++) {
          sb.append(words[i]).append(" ");
        }

        // remove the last " ", because the last word may not need it.
        sb.deleteCharAt(sb.length() - 1);
        for (int i = sb.length(); i < maxWidth; i++) {
          sb.append(" ");
        }
      } else {

        // middle-justified

        int spaces = (maxWidth - wordLength) / wordCount;
        int rest = (maxWidth - wordLength) % wordCount;

        for (int i = index; i < last; i++) {

          sb.append(words[i]);

          // between each word
          if (i < last - 1) {
            for (int j = 0; j <= spaces; j++) {
              sb.append(" ");
            }

            if (rest > 0) {
              sb.append(" ");
              rest--;
            }
          }
        }
      }

      res.add(sb.toString());

      index = last;
    }

    return res;
  }
}