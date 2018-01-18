package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a digit string, return all possible letter combinations that the number could represent.
 * A mapping of digit to letters(just like on the telephone buttons) is given below.
 * { '0', " " },
 * { '1', "" },
 * { '2', "abc" },
 * { '3', "def" },
 * { '4', "ghi" },
 * { '5', "jkl" },
 * { '6', "mno" },
 * { '7', "pqrs" },
 * { '8',  "tuv" },
 * { '9', "wxyz" },
 * { '*',  "+" },
 * { '#',  "#" }
 *
 * Input:Digit string "23"
 * Output : ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 */
public class LetterCombinationsPhoneNumber {
    private final Map<Character, String> letters;
    private final List<String> result;

    public LetterCombinationsPhoneNumber() {
        this.letters = new HashMap<>();
        this.letters.put('0', " ");
        this.letters.put('1', "");
        this.letters.put('2', "abc");
        this.letters.put('3', "def");
        this.letters.put('4', "ghi");
        this.letters.put('5', "jkl");
        this.letters.put('6', "mno");
        this.letters.put('7', "pqrs");
        this.letters.put('8', "tuv");
        this.letters.put('9', "wxyz");
        this.letters.put('*', "+");
        this.letters.put('#', "#");

        this.result = new ArrayList<>();
    }

    private void dfs(String digits, int index, String current) {
        if (current.length() == digits.length()) {
            this.result.add(current);
            return;
        }

        for (int i = index; i < digits.length(); i++) {
            String letter = this.letters.get(digits.charAt(i));

            for (int j = 0; j < letter.length(); j++) {
                dfs(digits, i + 1, current + letter.charAt(j));
            }
        }
    }

    public List<String> letterCombinations(String digits) {

        dfs(digits, 0, "");
        return this.result;
    }
}
