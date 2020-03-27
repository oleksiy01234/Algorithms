class TicTacToe {
  int[] rows;
  int[] cols;
  int diag1 = 0;
  int diag2 = 0;
  int n;

  public TicTacToe(int n) {
    rows = new int[n];
    cols = new int[n];
    this.n = n;
  }

  /** Player {player} makes a move at ({row}, {col}).
      @param row The row of the board.
      @param col The column of the board.
      @param player The player, can be either 1 or 2.
      @return The current winning condition, can be either:
              0: No one wins.
              1: Player 1 wins.
              2: Player 2 wins. */
  public int move(int row, int col, int player) {
    int mult = player == 1 ? 1 : -1;

    rows[row] += mult;
    cols[col] += mult;
    if (row == col) { // diag1 = 0,0 ---> n,n.
      diag1 += mult;
    }
    if (row == n - col - 1 || col == n - row - 1) {
      diag2 += mult;
    }

    int vic = mult * n;
    return (rows[row] == vic || cols[col] == vic || diag1 == vic || diag2 == vic) ? player : 0;
  }
}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */