import java.util.LinkedList;
import java.util.Queue;

/**
 * 200. Number of Islands https://leetcode.com/problems/number-of-islands/
 * 
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of
 * islands. An island is surrounded by water and is formed by connecting
 * adjacent lands horizontally or vertically. You may assume all four edges of
 * the grid are all surrounded by water.
 * 
 * Example 1: 11110 11010 11000 00000 Output: 1
 * 
 * Example 2: 11000 11000 00100 00011 Output: 3
 */
public class NumberOfIslands {
  // to keep the grid the same, we can either:
  // 1) flag the cell as '2', so we know not to process it, and clean it up later;
  // 2) keep a hashset of visited cells, with strings of "row,col" format.

  // bfs solution
  public int numIslands(char[][] grid) {
    if (grid == null || grid.length == 0) {
      return 0;
    }

    int count = 0;

    for (int r = 0; r < grid.length; ++r) {
      for (int c = 0; c < grid[r].length; ++c) {
        if (grid[r][c] == '1') {
          count++;
          Queue<String> neighbors = new LinkedList<>();
          neighbors.add(r + "," + c);

          while (!neighbors.isEmpty()) {
            String[] id = neighbors.remove().split(",");
            int row = Integer.parseInt(id[0]);
            int col = Integer.parseInt(id[1]);

            if (row < 0 || col < 0 || row >= grid.length || col >= grid[row].length || grid[row][col] != '1') {
              continue;
            }
            grid[row][col] = '0';

            neighbors.add((row - 1) + "," + col);
            neighbors.add((row + 1) + "," + col);
            neighbors.add(row + "," + (col - 1));
            neighbors.add(row + "," + (col + 1));
          }
        }
      }
    }

    return count;
  }

  // dfs solution
  public int numIslands2(char[][] grid) {
    int count = 0;

    for (int i = 0; i < grid.length; i++) {
      for (int k = 0; k < grid[i].length; k++) {
        if (grid[i][k] == '1') {
          dfs(grid, i, k);
          count++;
        }
      }
    }

    return count;
  }

  void dfs(char[][] grid, int i, int k) {
    if (i < 0 || k < 0 || i >= grid.length || k >= grid[i].length || grid[i][k] == '0') {
      return;
    }

    grid[i][k] = '0';

    dfs(grid, i - 1, k);
    dfs(grid, i + 1, k);
    dfs(grid, i, k - 1);
    dfs(grid, i, k + 1);
  }
}