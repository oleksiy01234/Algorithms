import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class Trie {
  int maxProducts = 3;
  boolean completeWord = false;
  Map<Character, Trie> kids = new HashMap<>();
  PriorityQueue<String> products = new PriorityQueue<>(Collections.reverseOrder()); // top n products

  void add(String word, int index) {
    products.add(word);
    if (products.size() > maxProducts) {
      products.poll();
    }

    if (index == word.length()) {
      completeWord = true;
      return;
    }

    char letter = word.charAt(index);

    if (!kids.containsKey(letter)) {
      kids.put(letter, new Trie());
    }

    kids.get(letter).add(word, index + 1);
  }

  List<String> getProducts() {
    List<String> res = new ArrayList<>();
    while (!products.isEmpty()) { // can't addAll because PQ is not necessarily sorted at every level
      res.add(0, products.poll());
    }
    return res;
  }

  // iterative insertion
  public void insert(String s) {
    Trie current = this;
    for (int i = 0; i < s.length(); i++) {
      char ch = s.charAt(i);
      Trie node = current.kids.get(ch);
      if (node == null) {
        node = new Trie();
        current.kids.put(ch, node);
      }
      current = node;
    }

    //mark the current nodes endOfWord as true
    current.completeWord = true;
  }

  // recursive insertion
  public void insertRec(String word) {
    insertRec(word, 0);
  }

  private void insertRec(String s, int index) {
    if (index == s.length()) {
      //if end of word is reached then mark endOfWord as true on current node
      completeWord = true;
      return;
    }

    char c = s.charAt(index);

    //if node does not exist in map then create one and put it into map
    if (!kids.containsKey(c)) {
      kids.put(c, new Trie());
    }
    kids.get(c).insertRec(s, index + 1);
  }

  // iterative search
  public boolean search(String word) {
    Trie n = this;

    for (int i = 0; i < word.length(); i++) {
      char ch = word.charAt(i);
      Trie kid = n.kids.get(ch);
      //if kid does not exist for given char then return false
      if (kid == null) {
        return false;
      }
      n = kid;
    }

    //return true of current's endOfWord is true else return false.
    return n.completeWord;
  }

  // recursive search
  public boolean searchRec(String word) {
    return searchRec(word, 0);
  }

  private boolean searchRec(String word, int index) {
    if (index == word.length()) {
      return completeWord; //return true of current's endOfWord is true else return false.
    }

    char c = word.charAt(index);
    if (!kids.containsKey(c)) {
      return false;
    }

    return kids.get(c).searchRec(word, index + 1);
  }

  // recursive deletion
  public void delete(String word) {
    delete(word, 0);
  }

  // returns true if parent should delete the mapping
  private boolean delete(String word, int index) {
    if (index == word.length()) {
      //when end of word is reached only delete if currrent.endOfWord is true.
      if (!completeWord) {
        return false;
      }

      completeWord = false;
      //if current has no other mapping then return true
      return kids.size() == 0;
    }
    char c = word.charAt(index);
    
    if (!kids.containsKey(c)) {
      return false;
    }
    boolean shouldDeleteCurrentNode = kids.get(c).delete(word, index + 1);

    //if true is returned then delete the mapping of character and trienode reference from map.
    if (shouldDeleteCurrentNode) {
      kids.remove(c);
      //return true if no mappings are left in the map.
      return kids.isEmpty();
    }
    return false;
  }

  public static void main(String[] args) {
    Trie trie = new Trie();

    trie.insertRec("abcd");
    trie.insertRec("abc");
    trie.insertRec("akk");
    trie.insertRec("a");

    System.out.println(trie.searchRec("abcd"));
    System.out.println(trie.searchRec("abd"));
    System.out.println(trie.searchRec("abc"));
    System.out.println(trie.searchRec("aa"));
    System.out.println(trie.searchRec("a"));
    System.out.println(trie.searchRec(""));
    System.out.println();

    trie.delete("abc");
    trie.delete("a");

    System.out.println(trie.searchRec("abcd"));
    System.out.println(trie.searchRec("abd"));
    System.out.println(trie.searchRec("abc"));
    System.out.println(trie.searchRec("aa"));
    System.out.println(trie.searchRec("a"));
    System.out.println(trie.searchRec(""));
  }
}