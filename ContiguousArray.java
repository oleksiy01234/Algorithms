import java.util.HashMap;
import java.util.Map;

public class ContiguousArray {
  // o(n) space+time solution
  public int findMaxLength(int[] a) {
    // map count->index
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, -1);

    int maxlen = 0, count = 0;
    for (int i = 0; i < a.length; i++) {
      if (a[i] == 1) {
        count++;
      } else {
        count--;
      }

      // if we have previously had this count,
      // zeros = ones between i and that index
      if (map.containsKey(count)) {
        maxlen = Math.max(maxlen, i - map.get(count));
      } else {
        map.put(count, i);
      }
    }

    return maxlen;
  }

  // n^2 solution
  public int findMaxLength2(int[] a) {
    int max = 0;

    for (int i = 0; i < a.length; i++) {
      int zeros = 0, ones = 0;
      for (int k = i; k < a.length; k++) {
        if (a[k] == 0) {
          zeros++;
        } else {
          ones++;
        }
        if (zeros == ones) {
          max = Math.max(max, zeros + ones);
        }
      }
    }

    return max;
  }
}