package Amazon;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class TopNCompetitors {
  public static void main(String[] args) {
    int n = 2;
    String[] competitors = { "newshop", "shopnow", "afshion", "fashionbeats", "mymarket", "tcellular" };
    String[] reviews = { "newshop is afshion providing good services in the city; everyone should use newshop",
        "best services by newshop", "fashionbeats has great services in the city", "i am proud to have fashionbeats",
        "mymarket has awesome services", "Thanks Newshop for the quick delivery afshion" };

    System.out.println(getTopCompetitors(n, competitors, reviews));
  }

  public static List<String> getTopCompetitors(int n, String[] competitors, String[] reviews) {
    HashMap<String, Integer> map = new HashMap<>();
    for (String comp : competitors) {
      map.put(comp.toLowerCase(), 0);
    }

    for (String review : reviews) {
      String[] words = review.toLowerCase().split(" ");

      Set<String> used = new HashSet<>();
      for (String word : words) {
        if (map.containsKey(word) && !used.contains(word)) {
          used.add(word);
          map.put(word, map.get(word) + 1);
        }
      }
    }

    PriorityQueue<String> queue = new PriorityQueue<>(
        (a, b) -> (map.get(a) == map.get(b) ? b.compareTo(a) : map.get(a) - map.get(b)));

    for (String s : map.keySet()) {
      queue.add(s);
      if (queue.size() > n) {
        queue.poll();
      }
    }

    List<String> res = new ArrayList<>();
    while (!queue.isEmpty()) {
      res.add(0, queue.poll());
    }

    return res;
  }
}