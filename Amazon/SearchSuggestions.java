package Amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1268. Search Suggestions System
 */
public class SearchSuggestions {
  public static void main(String[] args) {
    String[] products = { "mobile", "mouse", "moneypot", "monitor", "mousepad" };
    String searchWord = "mouse";

    List<List<String>> res = new SearchSuggestions().suggestedProducts(products, searchWord);
    System.out.println(res.toString());
  }

  class Trie {
    Map<Character, Trie> map = new HashMap<>();
    List<String> words = new ArrayList<>();

    void add(String word, int index) {
      if (words.size() < 3) {
        words.add(word);
      }

      if (index == word.length()) {
        return;
      }

      char letter = word.charAt(index);

      if (!map.containsKey(letter)) {
        map.put(letter, new Trie());
      }

      map.get(letter).add(word, index + 1);
    }
  }

  public List<List<String>> suggestedProducts(String[] products, String searchWord) {
    Arrays.sort(products);
    Trie root = new Trie();

    for (String p : products) {
      root.add(p, 0);
    }

    List<List<String>> res = new ArrayList<>();
    for (char c : searchWord.toCharArray()) {
      if (root != null) {
        root = root.map.get(c);
      }

      if (root == null) {
        res.add(new ArrayList<>());
      } else {
        res.add(root.words);
      }
    }
    return res;
  }
}