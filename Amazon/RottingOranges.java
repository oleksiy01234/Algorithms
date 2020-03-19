package Amazon;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class RottingOranges {

  // my solution
  public int orangesRotting(int[][] grid) {
    Set<String> rotten = new HashSet<>();
    Set<String> fresh = new HashSet<>();
    Set<String> infected = new HashSet<>();

    for (int i = 0; i < grid.length; i++) {
      for (int k = 0; k < grid[i].length; k++) {
        if (grid[i][k] == 1) {
          fresh.add(i + "," + k);
        } else if (grid[i][k] == 2) {
          rotten.add(i + "," + k);
        }
      }
    }

    int count = 0;

    while (!fresh.isEmpty()) {
      for (String s : rotten) {
        int i = Integer.parseInt(s.split(",")[0]);
        int k = Integer.parseInt(s.split(",")[1]);

        String top = (i - 1) + "," + k;
        String rig = i + "," + (k + 1);
        String bot = (i + 1) + "," + k;
        String lef = i + "," + (k - 1);

        if (fresh.remove(top)) {
          infected.add(top);
        }

        if (fresh.remove(rig)) {
          infected.add(rig);
        }

        if (fresh.remove(bot)) {
          infected.add(bot);
        }

        if (fresh.remove(lef)) {
          infected.add(lef);
        }
      }

      if (infected.isEmpty()) {
        return -1;
      }

      count++;
      rotten = new HashSet<>(infected);
      infected.clear();
    }

    return count;
  }

  // LC solution
  public int orangesRotting2(int[][] grid) {
    int rowCount = grid.length, colCount = grid[0].length;
    int[] dirRows = new int[] { -1, 0, 1, 0 };
    int[] dirCols = new int[] { 0, -1, 0, 1 };

    // queue : all starting cells with rotten oranges
    Queue<Integer> queue = new ArrayDeque<>();
    Map<Integer, Integer> depth = new HashMap<>();
    for (int r = 0; r < rowCount; ++r) {
      for (int c = 0; c < colCount; ++c) {
        if (grid[r][c] == 2) {
          int code = r * colCount + c;
          queue.add(code);
          depth.put(code, 0);
        }
      }
    }

    int ans = 0;
    while (!queue.isEmpty()) {
      int code = queue.remove();
      int r = code / colCount, c = code % colCount;
      for (int k = 0; k < 4; ++k) {
        int nr = r + dirRows[k];
        int nc = c + dirCols[k];
        if (0 <= nr && nr < rowCount && 0 <= nc && nc < colCount && grid[nr][nc] == 1) {
          grid[nr][nc] = 2;
          int ncode = nr * colCount + nc;
          queue.add(ncode);
          depth.put(ncode, depth.get(code) + 1);
          ans = depth.get(ncode);
        }
      }
    }

    for (int[] row : grid) {
      for (int v : row) {
        if (v == 1) {
          return -1;
        }
      }
    }
    return ans;

  }

}