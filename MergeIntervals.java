import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals {
  public int[][] merge(int[][] intervals) {
    List<List<Integer>> res = new ArrayList<>();
    Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

    for (int[] interval : intervals) {
      if (res.isEmpty() || interval[0] > res.get(res.size() - 1).get(1)) {
        res.add(Arrays.asList(interval[0], interval[1]));
      } else {
        res.get(res.size() - 1).set(1, Math.max(res.get(res.size() - 1).get(1), interval[1]));
      }
    }

    int[][] aRes = new int[res.size()][2];
    for (int i = 0; i < aRes.length; i++) {
      aRes[i][0] = res.get(i).get(0);
      aRes[i][1] = res.get(i).get(1);
    }

    return aRes;
  }
}
