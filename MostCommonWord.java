import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 819. Most Common Word
 * https://leetcode.com/problems/most-common-word/
 * 
 * Given a paragraph and a list of banned words, return the most frequent word that is not in the list of banned words.  It is guaranteed there is at least one word that isn't banned, and that the answer is unique.

 * Words in the list of banned words are given in lowercase, and free of punctuation.
 * Words in the paragraph are not case sensitive.  The answer is in lowercase.
 * 
 * Example:
 * paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
 * banned = ["hit"]
 * Output: "ball"
 * 
 * Explanation: 
 * "hit" occurs 3 times, but it is a banned word.
 * "ball" occurs twice (and no other word does), so it is the most frequent non-banned word in the paragraph. 
 * 
 * Words in the paragraph are not case sensitive
 * Punctuation is ignored (even if adjacent to words, such as "ball,")
 * 
 * 1 <= paragraph.length <= 1000.
 * 0 <= banned.length <= 100.
 * 1 <= banned[i].length <= 10.
 * The answer is unique, and written in lowercase (even if its occurrences in paragraph may have uppercase symbols, and even if it is a proper noun.)
 * paragraph only consists of letters, spaces, or the punctuation symbols !?',;.
 * There are no hyphens or hyphenated words.
 * Words only consist of letters, never apostrophes or other punctuation symbols.
 *  */
public class MostCommonWord {

  public String mostCommonWord(String p, String[] banned) {
    Set<String> b = new HashSet<>(Arrays.asList(banned));
    Map<String, Integer> map = new HashMap<>();
    StringBuilder sb = new StringBuilder();
    String max = null;

    for (int i = 0; i <= p.length(); i++) {
      if (i < p.length() && Character.isLetter(p.charAt(i))) {
        sb.append(p.charAt(i));
      } else if (sb.length() > 0) {
        String cand = sb.toString().toLowerCase();
        sb.setLength(0);
        if (!b.contains(cand)) {
          map.put(cand, map.getOrDefault(cand, 0) + 1);
          if (map.get(cand) > map.getOrDefault(max, 0)) {
            max = cand;
          }
        }
      }
    }

    return max;
  }
}