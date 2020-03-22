import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
  public static void main(String[] args) {
    PriorityQueue<String> pq = new PriorityQueue<>(Collections.reverseOrder());
    
    String[] products = { "mobile", "mouse", "moneypot", "monitor", "mousepad" };

    for (String product : products) {
      pq.add(product);
    }

    while (!pq.isEmpty()) {
      System.out.println(pq.poll());
    }
  }
}