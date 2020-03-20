import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Syntax {
  public static void main(String[] args) {
    forLoop();
  }

  static void forLoop() {
    List<Integer> list = Arrays.asList(1, 4, 5, 6);
    list.forEach(n -> System.out.print(n + ", "));
  }

  static void comparator() {
    PriorityQueue<String> words = new PriorityQueue<>(Collections.reverseOrder());
    words.add("12");

    PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> 1);
    pq.add("");

    String[] logs = new String[10];
    Arrays.sort(logs, (log1, log2) -> {
      return log1.isEmpty() ? 1 : -1;
    });
  }
}