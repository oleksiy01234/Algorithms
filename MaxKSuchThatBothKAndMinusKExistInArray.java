import java.util.HashSet;
import java.util.Set;

public class MaxKSuchThatBothKAndMinusKExistInArray {
  public static void test() {
    MaxKSuchThatBothKAndMinusKExistInArray l = new MaxKSuchThatBothKAndMinusKExistInArray();
    System.out.println(l.largestK(new int[] { 3, 2, -2, 5, -3 }));
    System.out.println(l.largestK(new int[] { 1, 2, 3, -4 }));
  }

  public int largestK(int[] a) {
    int max = 0;
    Set<Integer> set = new HashSet<>();

    for (int n : a) {
      if (set.contains(-n)) {
        max = Math.max(max, Math.abs(n));
      }
      set.add(n);
    }

    return max;
  }
}
