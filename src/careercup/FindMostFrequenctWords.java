package careercup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Give a string and a list of exclude words,
// find the most frequent words that are not in the exclude list and
// Example: "I like cheese. Jack like Jack and Jone's", exclude list is [like, I]
// output: [jack]
public class FindMostFrequenctWords {

    public List<String> findMostFrequentWords(String text, List<String> excludeList) {
        List<String> result = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        int maxFrequency = 1;

        if (text == null || text.length() == 0) {
            return result;
        }

        // use hash set to check if the word needs exclude
        Set<String> excludeSet = new HashSet<>(excludeList);

        for (String word : text.toLowerCase().split("\\W+")) {
            if (!excludeSet.contains(word)) {
                map.put(word, map.getOrDefault(word, 0) + 1);
                maxFrequency = Math.max(maxFrequency, map.get(word));
            }
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue().equals(maxFrequency)) {
                result.add(entry.getKey());
            }
        }

        return result;
    }

}
