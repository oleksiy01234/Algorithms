import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 127. Word Ladder
 * https://leetcode.com/problems/word-ladder/
 * 
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

 * Only one letter can be changed at a time.
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * Note:
 * 
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume beginWord and endWord are non-empty and are not the same.
 * Example 1:
 * 
 * Input:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * 
 * Output: 5
 * 
 * Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 * Example 2:
 * 
 * Input:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * 
 * Output: 0
 * 
 * Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 */
public class WordLadder {

  public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    boolean[] used = new boolean[wordList.size()];
    Queue<String> q = new LinkedList<>();
    Map<String, Integer> levels = new HashMap<>();
    q.add(beginWord);

    levels.put(beginWord, 1);
    while (!q.isEmpty()) {
      String word = q.poll();
      if (word.equals(endWord)) {
        return levels.get(word);
      }

      for (int i = 0; i < wordList.size(); i++) {
        if (used[i]) {
          continue;
        }

        String neighbor = wordList.get(i);
        if (oneEdit(word, neighbor)) {
          used[i] = true;
          q.add(neighbor);
          levels.put(neighbor, levels.get(word) + 1);
        }
      }
    }

    return 0;
  }

  boolean oneEdit(String s1, String s2) {
    boolean editUsed = false;

    for (int i = 0; i < s1.length(); i++) {
      if (s1.charAt(i) != s2.charAt(i)) {
        if (editUsed) {
          return false;
        }
        editUsed = true;
      }
    }

    return editUsed;
  }
}