import java.util.LinkedList;
import java.util.Queue;

/**
 * Minesweeper
 */
public class Minesweeper {
  public char[][] updateBoard(char[][] m, int[] click) {
    int row = click[0], col = click[1];

    if (m[row][col] == 'M') {
      m[row][col] = 'X';
      return m; // don't DFS on mines
    }

    dfs(m, row, col);
    return m;
  }

  private void dfs(char[][] m, int row, int col) {
    int count = getAdjacentMines(m, row, col);
    if (count > 0) {
      m[row][col] = Character.forDigit(count, 10);
      return; // don't DFS on cells with adj mines
    }

    m[row][col] = 'B';

    for (int r = Math.max(0, row - 1); r <= Math.min(m.length - 1, row + 1); r++) {
      for (int c = Math.max(0, col - 1); c <= Math.min(m[r].length - 1, col + 1); c++) {
        if (m[r][c] == 'E') {
          dfs(m, r, c); // only DFS on unexplored empty cells
        }
      }
    }
  }

  private int getAdjacentMines(char[][] board, int row, int col) {
    int count = 0;

    for (int i = Math.max(0, row - 1); i <= Math.min(row + 1, board.length - 1); i++) {
      for (int k = Math.max(0, col - 1); k <= Math.min(col + 1, board[row].length - 1); k++) {
        if (board[i][k] == 'M') {
          count++;
        }
      }
    }

    return count;
  }

  // BFS solution
  public char[][] updateBoardBFS(char[][] m, int[] click) {
    Queue<int[]> queue = new LinkedList<>();
    queue.add(click);

    while (!queue.isEmpty()) {
      int[] cell = queue.poll();
      int row = cell[0], col = cell[1];

      if (m[row][col] == 'M') {
        m[row][col] = 'X';
        break;
      }

      int count = getAdjacentMines(m, row, col);

      if (count > 0) { // If it is not a 'B', stop further BFS.
        m[row][col] = Character.forDigit(count, 10);
        continue;
      }

      m[row][col] = 'B';

      for (int r = Math.max(0, row - 1); r <= Math.min(m.length - 1, row + 1); r++) {
        for (int c = Math.max(0, col - 1); c <= Math.min(m[r].length - 1, col + 1); c++) {
          if (m[r][c] == 'E') {
            m[r][c] = 'B'; // Avoid to be added again.
            queue.add(new int[] { r, c });
          }
        }
      }
    }

    return m;
  }
}