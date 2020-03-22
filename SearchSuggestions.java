import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 1268. Search Suggestions System
 * https://leetcode.com/problems/search-suggestions-system/
 * 
 * Given an array of strings products and a string searchWord. We want to design a system that suggests at most three product names from products after each character of searchWord is typed. Suggested products should have common prefix with the searchWord. If there are more than three products with a common prefix return the three lexicographically minimums products.

 * Return list of lists of the suggested products after each character of searchWord is typed. 
 * 
 * Example 1:
 * Input: products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
 * Output: [
 *    ["mobile","moneypot","monitor"],
 *    ["mobile","moneypot","monitor"],
 *    ["mouse","mousepad"],
 *    ["mouse","mousepad"],
 *    ["mouse","mousepad"]
 * ]
 * Explanation: products sorted lexicographically = ["mobile","moneypot","monitor","mouse","mousepad"]
 * After typing m and mo all products match and we show user ["mobile","moneypot","monitor"]
 * After typing mou, mous and mouse the system suggests ["mouse","mousepad"]
 * 
 * Example 2:
 * Input: products = ["havana"], searchWord = "havana"
 * Output: [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
 * 
 * Example 3:
 * Input: products = ["bags","baggage","banner","box","cloths"], searchWord = "bags"
 * Output: [["baggage","bags","banner"],["baggage","bags","banner"],["baggage","bags"],["bags"]]
 * 
 * Example 4:
 * Input: products = ["havana"], searchWord = "tatiana"
 * Output: [[],[],[],[],[],[],[]]
 */
public class SearchSuggestions {
  public static void main(String[] args) {
    String[] products = { "mobile", "mouse", "moneypot", "monitor", "mousepad" };
    String searchWord = "mouse";

    List<List<String>> res = new SearchSuggestions().suggestedProducts(products, searchWord);
    System.out.println(res.toString());
  }

  // optimization: use arraylist, but sort products
  class Trie {
    Map<Character, Trie> map = new HashMap<>();
    PriorityQueue<String> products = new PriorityQueue<>(Collections.reverseOrder());

    void add(String word, int index) {
      products.add(word);
      if (products.size() > 3) {
        products.poll();
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

    List<String> get() {
      List<String> res = new ArrayList<>();
      while (!products.isEmpty()) {
        res.add(0, products.poll());
      }

      return res;
    }
  }

  // could also sort products[] and just use ArrayList in Trie, while keeping it <= 3
  public List<List<String>> suggestedProducts(String[] products, String searchWord) {
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
        res.add(root.get());
      }
    }
    return res;
  }
}