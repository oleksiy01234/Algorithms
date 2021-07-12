public class CountBattleships {
  public int countBattleships(char[][] m) {
    int count = 0;

    for (int i = 0; i < m.length; i++) {
      for (int k = 0; k < m[i].length; k++) {
        if (m[i][k] == 'X') {
          count++;
          dfs(m, i, k);
        }
      }
    }

    for (int i = 0; i < m.length; i++) {
      for (int k = 0; k < m[i].length; k++) {
        if (m[i][k] == 'O') {
          m[i][k] = 'X';
        }
      }
    }

    return count;
  }

  private void dfs(char[][] m, int row, int col) {
    if (row < 0 || row >= m.length || col < 0 || col >= m[row].length || m[row][col] != 'X') {
      return;
    }

    m[row][col] = 'O';
    dfs(m, row - 1, col);
    dfs(m, row + 1, col);
    dfs(m, row, col - 1);
    dfs(m, row, col + 1);
  }
}
