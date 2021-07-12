import java.util.Arrays;

public class SetZeroMatrix {
  public void setZeroes(int[][] m) {
    boolean isCol = false;

    for (int i = 0; i < m.length; i++) {
      if (m[i][0] == 0) {
        isCol = true;
      }

      for (int j = 1; j < m[i].length; j++) {
        if (m[i][j] == 0) {
          m[0][j] = 0;
          m[i][0] = 0;
        }
      }
    }

    for (int i = 1; i < m.length; i++) {
      for (int j = 1; j < m[i].length; j++) {
        if (m[i][0] == 0 || m[0][j] == 0) {
          m[i][j] = 0;
        }
      }
    }

    if (m[0][0] == 0) {
      Arrays.fill(m[0], 0);
    }

    if (isCol) {
      for (int i = 0; i < m.length; i++) {
        m[i][0] = 0;
      }
    }
  }
}
