package Amazon;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class ComparatorSyntax {
  public void example() {
    PriorityQueue<String> words = new PriorityQueue<>(Collections.reverseOrder());
    words.add("12");

    PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> 1);
    pq.add("");

    String[] logs = new String[10];
    Arrays.sort(logs, (log1, log2) -> {
      if (log1.length() > 5) {
        return 1;
      }
      return -1;
    });
  }
}