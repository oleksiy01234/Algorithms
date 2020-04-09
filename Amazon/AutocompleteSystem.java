package Amazon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AutocompleteSystem {
  class Sentence {
    Sentence(String sentence, int frequency) {
      this.sentence = sentence;
      this.frequency = frequency;
    }

    String sentence;
    int frequency;
  }

  class Trie {
    int frequency;
    Trie[] branches = new Trie[27];
  }

  Trie root;
  String thisSentence = "";

  public int charToInt(char c) {
    return c == ' ' ? 26 : c - 'a';
  }

  public void insert(Trie t, String s, int frequency) {
    for (char c : s.toCharArray()) {
      int cIntValue = charToInt(c);

      if (t.branches[cIntValue] == null) {
        t.branches[cIntValue] = new Trie();
      }

      t = t.branches[cIntValue];
    }
    t.frequency += frequency;
  }

  public List<Sentence> lookup(Trie trie, String s) {
    List<Sentence> list = new ArrayList<>();

    for (char c : s.toCharArray()) {
      int cIntValue = charToInt(c);
      if (trie.branches[cIntValue] == null) {
        return new ArrayList<Sentence>();
      }
      trie = trie.branches[cIntValue];
    }

    traverse(s, trie, list);
    return list;
  }

  public void traverse(String s, Trie t, List<Sentence> list) {
    if (t.frequency > 0) {
      list.add(new Sentence(s, t.frequency));
    }

    for (char i = 'a'; i <= 'z'; i++) {
      if (t.branches[i - 'a'] != null) {
        traverse(s + i, t.branches[i - 'a'], list);
      }
    }

    if (t.branches[26] != null) {
      traverse(s + ' ', t.branches[26], list);
    }
  }

  public AutocompleteSystem(String[] sentences, int[] times) {
    root = new Trie();
    for (int i = 0; i < sentences.length; i++) {
      insert(root, sentences[i], times[i]);
    }
  }

  public List<String> input(char c) {
    List<String> res = new ArrayList<>();
    if (c == '#') {
      insert(root, thisSentence, 1);
      thisSentence = "";
    } else {
      thisSentence += c;
      List<Sentence> list = lookup(root, thisSentence);
      Collections.sort(list, (a, b) -> {
        int freqDiff = a.frequency - b.frequency;
        if (freqDiff != 0) {
          return freqDiff;
        }

        return a.sentence.compareTo(b.sentence);
      });

      for (int i = 0; i < Math.min(3, list.size()); i++) {
        res.add(list.get(i).sentence);
      }
    }
    return res;
  }
}