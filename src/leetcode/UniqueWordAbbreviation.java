package leetcode;

import java.util.HashMap;

// An abbreviation of a word follows the form <first letter><number><last letter>.
// Below are some examples of word abbreviations:
//
// a) it                      --> it    (no abbreviation)
//
//      1
//      ↓
// b) d|o|g                   --> d1g
//
//               1    1  1
//      1---5----0----5--8
//      ↓   ↓    ↓    ↓  ↓
// c) i|nternationalizatio|n  --> i18n
//
//               1
//      1---5----0
//      ↓   ↓    ↓
// d) l|ocalizatio|n          --> l10n
// Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary.
// A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.
//
// Example:
//
// Given dictionary = [ "deer", "door", "cake", "card" ]
//
// isUnique("dear") -> false
// isUnique("cart") -> true
// isUnique("cane") -> false
// isUnique("make") -> true
public class UniqueWordAbbreviation {

  // the words in dictionary and their frequency mapping
  private HashMap<String, Integer> dict = new HashMap<>();

  // the word abbreviation and frequency mapping
  private HashMap<String, Integer> map = new HashMap<>();


  private String getAbbreviation(String s) {
    if (s.length() <= 2) {
      return s;
    }

    StringBuilder res = new StringBuilder();

    // append the first char of given string
    res.append(s.charAt(0));

    // append the abbreviation number
    res.append(Integer.toString(s.length() - 2));

    // append the last char
    res.append(s.charAt(s.length() - 1));

    return res.toString();
  }

  public UniqueWordAbbreviation(String[] dictionary) {

    for (String word : dictionary) {
      this.dict.put(word, dict.getOrDefault(word, 0) + 1);

      String abbreviation = this.getAbbreviation(word);
      this.map.put(abbreviation, map.getOrDefault(abbreviation, 0) + 1);
    }
  }

  public boolean isUnique(String word) {
    // two conditions will return true
    // 1. the abbreviation is not in the "map"
    // 2. the word in the dictionary and no other words have the same abbreviation,
    // the word frequency equals the abbreviation frequency
    // e.g dict[word] == map[abbreviation], they have the same frequency

    String abbreviation = getAbbreviation(word);

    if (!map.containsKey(abbreviation)) {
      return true;
    }

    if (dict.containsKey(word) && dict.get(word).equals(map.get(abbreviation))) {
      // the word is in the dictionary, and word frequency equals the word abbreviation frequency
      return true;
    }

    return false;
  }
}
