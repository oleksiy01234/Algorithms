import java.util.HashSet;
import java.util.Set;

/**
 * 694. Number of Distinct Islands
 * https://leetcode.com/problems/number-of-distinct-islands/
 * 
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected  
 * 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
 * 
 * Count the number of distinct islands. An island is considered to be the same as another if and only if one  
 * island can be translated (and not rotated or reflected) to equal the other.
 * 
 * Example 1:
 * 11000
 * 11000
 * 00011
 * 00011
 * Given the above grid map, return 1.
 * 
 * Example 2:
 * 11011
 * 10000
 * 00001
 * 11011
 * Given the above grid map, return 3.
 * 
 * Notice that:
 * 11
 * 1
 * and
 *  1
 * 11
 * are considered different island shapes, because we do not consider reflection / rotation.
 */
public class NumberOfDistinctIslands {

  // path signature. O(rc)
  public int numDistinctIslands(int[][] grid) {
    Set<String> islands = new HashSet<>();

    for (int i = 0; i < grid.length; i++) {
      for (int k = 0; k < grid[i].length; k++) {
        if (grid[i][k] == 1) {
          StringBuilder sb = new StringBuilder();
          dfs(sb, grid, i, k, 0, 0);
          islands.add(sb.toString());
        }
      }
    }

    return islands.size();
  }

  int[][] dirs = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

  void dfs(StringBuilder sb, int[][] grid, int i, int k, int iOffset, int kOffset) {
    if (i < 0 || k < 0 || i >= grid.length || k >= grid[i].length || grid[i][k] != 1) {
      return;
    }

    grid[i][k] = 0;
    sb.append(iOffset + "," + kOffset + ",");

    for (int[] dir : dirs) {
      dfs(sb, grid, i + dir[0], k + dir[1], iOffset + dir[0], kOffset + dir[1]);
    }
  }
}