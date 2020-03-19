import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
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
    int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    while (!fresh.isEmpty()) {
      for (String s : rotten) {
        int row = Integer.parseInt(s.split(",")[0]);
        int col = Integer.parseInt(s.split(",")[1]);

        for (int[] dir : dirs) {
          String neighbor = (row + dir[0]) + "," + (col + dir[1]);
          if (fresh.remove(neighbor)) {
            infected.add(neighbor);
          }
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

  // LC solution (modified)
  public int orangesRotting2(int[][] grid) {
    int freshCount = 0;

    // q : all starting cells with rotten oranges
    Queue<String> q = new LinkedList<>();
    Map<String, Integer> depth = new HashMap<>();
    for (int r = 0; r < grid.length; ++r) {
      for (int c = 0; c < grid[r].length; ++c) {
        if (grid[r][c] == 1) {
          freshCount++;
        }
        if (grid[r][c] == 2) {
          String code = r + "," + c;
          q.add(code);
          depth.put(code, 0);
        }
      }
    }

    int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    int count = 0;

    while (!q.isEmpty()) {
      String code = q.remove();
      int row = Integer.parseInt(code.split(",")[0]);
      int col = Integer.parseInt(code.split(",")[1]);

      for (int[] dir : dirs) {
        int nr = row + dir[0];
        int nc = col + dir[1];
        if (nr < 0 || nr >= grid.length || nc < 0 || nc >= grid[nr].length || grid[nr][nc] != 1) {
          continue; // ignore invalid and non-fresh cells
        }

        freshCount--;
        grid[nr][nc] = 2;
        String neighborCode = nr + "," + nc;
        q.add(neighborCode);
        depth.put(neighborCode, depth.get(code) + 1);
        count = depth.get(neighborCode);
      }
    }

    return freshCount == 0 ? count : -1;
  }
}