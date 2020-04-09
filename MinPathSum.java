/**
 * 64. Minimum Path Sum
 * https://leetcode.com/problems/minimum-path-sum/
 * 
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

 * Note: You can only move either down or right at any point in time.

 * Example:
 * Input:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * Output: 7
 * Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 */
public class MinPathSum {

  // my solution
  public int minPathSum(int[][] grid) {
    int[][] dp = new int[grid.length][grid[0].length];

    for (int i = 0; i < grid.length; i++) {
      for (int k = 0; k < grid[i].length; k++) {
        if (i == 0 && k == 0) {
          dp[i][k] = grid[i][k];
        } else if (i == 0) {
          dp[i][k] = grid[i][k] + dp[i][k - 1];
        } else if (k == 0) {
          dp[i][k] = grid[i][k] + dp[i - 1][k];
        } else {
          dp[i][k] = grid[i][k] + Math.min(dp[i][k - 1], dp[i - 1][k]);
        }
      }
    }

    return dp[dp.length - 1][dp[0].length - 1];
  }

  // LC O(n) space solution (modified)
  public int minPathSum2(int[][] grid) {
    int[] dp = new int[grid[0].length];

    for (int i = 0; i < grid.length; i++) {
      for (int k = 0; k < grid[i].length; k++) {
        if (i == 0 && k == 0) {
          dp[k] = grid[i][k];
        } else if (i == 0) {
          dp[k] = grid[i][k] + dp[k - 1];
        } else if (k == 0) {
          dp[k] = grid[i][k] + dp[k];
        } else {
          dp[k] = grid[i][k] + Math.min(dp[k], dp[k - 1]);
        }
      }
    }

    return dp[dp.length - 1];
  }
}