import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ConcatenatedWords
 */
public class ConcatenatedWords {

  class Trie {
    int maxWords = 3;
    Map<Character, Trie> map = new HashMap<>();
    boolean endWord = false;

    void add(String word, int index) {
      if (index == word.length()) {
        endWord = true;
        return;
      }

      char letter = word.charAt(index);

      if (!map.containsKey(letter)) {
        map.put(letter, new Trie());
      }

      map.get(letter).add(word, index + 1);
    }

    boolean exists(Trie root, String word, boolean initialSearch) {
      if (word.isEmpty()) {
        return endWord && !initialSearch;
      }

      if (endWord && root.exists(root, word, false)) {
        return true;
      }

      char letter = word.charAt(0);
      return map.containsKey(letter) && map.get(letter).exists(root, word.substring(1), initialSearch);
    }
  }

  public static void main(String[] args) {
    ConcatenatedWords cw = new ConcatenatedWords();
    List<String> res = cw
        .findAllConcatenatedWordsInADict(new String[] { "c", "t", "ca", "cat", "dog", "dogs", "dogcat", "catcat" });
    System.out.println(res.toString());
  }

  public List<String> findAllConcatenatedWordsInADict(String[] words) {
    Trie root = new Trie();

    for (String word : words) {
      root.add(word, 0);
    }

    List<String> res = new ArrayList<>();

    for (String word : words) {
      if (root.exists(root, word, true)) {
        res.add(word);
      }
    }

    return res;
  }
}