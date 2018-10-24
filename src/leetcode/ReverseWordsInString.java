package leetcode;

// Given an input string, reverse the string word by word.
//
// Example:
//
// Input: "the sky is blue",
// Output: "blue is sky the".
// Note:
//
// A word is defined as a sequence of non-space characters.
// Input string may contain leading or trailing spaces.
// However, your reversed string should not contain leading or trailing spaces.

import java.util.ArrayList;
import java.util.List;

// You need to reduce multiple spaces between two words to a single space in the reversed string.
// Follow up: For C programmers, try to solve it in-place in O(1) space.
public class ReverseWordsInString {

  // the idea is reverse each single word, the reverse the entire word list
  public String reverseWords(String s) {
    if (s == null || s.isEmpty()) {
      return s;
    }

    String[] words = s.split(" ");

    List<String> reversedWordList = new ArrayList<>();

    for (String w : words) {

      if (w.isEmpty()) {
        continue;
      }

      String reversedWord = new StringBuilder(w).reverse().toString();
      reversedWordList.add(reversedWord);
    }

    StringBuilder res = new StringBuilder(String.join(" ", reversedWordList)).reverse();

    return res.toString();
  }

  // Given an input string , reverse the string word by word.
  //
  // Example:
  //
  // Input:  ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
  // Output: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
  // Note:
  //
  // A word is defined as a sequence of non-space characters.
  // The input string does not contain leading or trailing spaces.
  // The words are always separated by a single space.

  public void reverseWords2(char[] str) {

    if (str.length < 2) {
      return;
    }

    // skip the unnecessary whitespace
    int begin = 0;
    // while (begin < str.length && Character.isWhitespace(str[begin])) {
    //     begin++;
    // }

    int end = begin + 1;
    while (end < str.length) {
      if (Character.isWhitespace(end)) {
        this.reverse(str, begin, end - 1);

        begin = end + 1;
        end = begin;
      }
      end++;
    }

    // don't forget to reverse the last word
    reverse(str, begin, end - 1);

    // reverse the entire list
    reverse(str, 0, str.length - 1);
  }

  private void reverse(char[] str, int l, int r) {
    while (l < r) {

      char temp = str[l];
      str[l] = str[r];
      str[r] = temp;
      l++;
      r--;
    }
  }
}
