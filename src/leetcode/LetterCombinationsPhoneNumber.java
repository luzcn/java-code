package leetcode;

import java.util.ArrayList;
import java.util.List;

// Given a digit string, return all possible letter combinations that the number could represent. A
// mapping of digit to letters(just like on the telephone buttons) is given below. { '0', " " }, {
// '1', "" }, { '2', "abc" }, { '3', "def" }, { '4', "ghi" }, { '5', "jkl" }, { '6', "mno" }, { '7',
// "pqrs" }, { '8',  "tuv" }, { '9', "wxyz" }, { '//',  "+" }, { '#',  "#" }
//
// Input:Digit string "23" Output : ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
//
public class LetterCombinationsPhoneNumber {

  private final String[] letters = {" ", "", "abc", "def", "ghi",
      "jkl", "mno", "pqrs", "tuv", "wxyz"};
  private final List<String> result = new ArrayList<>();


  private void dfs(String digits, int index, String current) {
    if (index == digits.length()) {
      this.result.add(current);
      return;
    }

    String letter = letters[digits.charAt(index) - '0'];

    for (char c : letter.toCharArray()) {
      dfs(digits, index + 1, current + c);
    }
  }

  public List<String> letterCombinations(String digits) {

    if (digits.isEmpty()) {
      return result;
    }

    dfs(digits, 0, "");
    return result;
  }
}
