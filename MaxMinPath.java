import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * MaxMinPath
 */
public class MaxMinPath {

  public int maximumMinimumPath(int[][] a) {
    PriorityQueue<int[]> pq = new PriorityQueue<>((a1, a2) -> (a2[2] - a1[2]));

    int[][] dp = new int[a.length][a[0].length];
    for (int[] v : dp) {
      Arrays.fill(v, -1);
    }

    pq.add(new int[] { 0, 0, a[0][0] });
    dp[0][0] = a[0][0];

    int[] dRows = { 0, 0, 1, -1 };
    int[] dCols = { 1, -1, 0, 0 };

    while (!pq.isEmpty()) {
      int[] cell = pq.poll();

      for (int i = 0; i < 4; i++) {
        int row = cell[0] + dRows[i];
        int col = cell[1] + dCols[i];
        if (row < 0 || col < 0 || row >= a.length || col >= a[0].length || dp[row][col] != -1) {
          continue;
        }

        dp[row][col] = Math.min(cell[2], a[row][col]);
        if (row == a.length - 1 && col == a[0].length - 1) {
          return dp[row][col];
        }

        pq.add(new int[] { row, col, dp[row][col] });
      }
    }

    return -1;
  }
}