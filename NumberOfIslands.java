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
  public static void main(String[] args) {
    System.out.println(new NumberOfIslands().numIslands(new char[][]{{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}}));
  }

  int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

  // bfs solution
  public int numIslands(char[][] grid) {
    int count = 0;

    for (int i = 0; i < grid.length; ++i) {
      for (int k = 0; k < grid[i].length; ++k) {
        if (grid[i][k] == '1') {
          count++;
          bfs(grid, i, k);
        }
      }
    }

    return count;
  }

  void bfs(char[][] grid, int i, int k) {
    Queue<String> q = new LinkedList<>();
    q.add(i + "," + k);

    while (!q.isEmpty()) {
      String[] id = q.poll().split(",");
      int row = Integer.parseInt(id[0]);
      int col = Integer.parseInt(id[1]);

      if (row < 0 || col < 0 || row >= grid.length || col >= grid[row].length || grid[row][col] != '1') {
        continue;
      }
      grid[row][col] = '2';

      for (int[] dir : dirs) {
        q.add((row + dir[0]) + "," + (col + dir[1]));
      }
    }
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

    grid[i][k] = '2';

    for (int[] dir : dirs) {
      dfs(grid, i + dir[0], k + dir[0]);
    }
  }
}