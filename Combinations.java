import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Combinations {
  public static void main(String[] args) {
    List<List<Integer>> res = combine(3, 5);
    res.forEach(System.out::println);
  }

  public static List<List<Integer>> combine(int n, int k) {
    List<List<Integer>> res = new ArrayList<>();

    combine(1, n, k, new LinkedList<>(), res);

    return res;
  }

  private static void combine(int start, int end, int k, LinkedList<Integer> combo, List<List<Integer>> res) {
    if (combo.size() == k) {
      res.add(new ArrayList<>(combo));
      return;
    }

    for (int i = start; i <= end; i++) {
      combo.add(i);
      combine(i + 1, end, k, combo, res);
      combo.removeLast();
    }

  }

}
