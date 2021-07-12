import java.util.HashSet;
import java.util.Set;

public class Permutations {
  public static void main(String[] args) {
    int[] a = new int[] { 1, 2, 3, 4 };
    permute(a, new HashSet<>(), "");
  }

  static int count = 0;

  static void permute(int[] a, Set<Integer> used, String p) {
    if (used.size() == a.length) {
      System.out.println("Permutation " + ++count + ": " + p);
      return;
    }

    for (int n : a) {
      if (!used.contains(n)) {
        used.add(n);
        permute(a, used, p + "," + n);
        used.remove(n);
      }
    }
  }
}
