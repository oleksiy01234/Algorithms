public class RotateImage {
  public void rotate(int[][] m) {

    // will increase margin to 1, 2, etc until we rotated all circles
    // if len = 3, then we want margin 0
    // if len = 4, we want mar 0, mar 1
    // len = 5? we want mar 0, 1
    for (int margin = 0; margin < m.length / 2; margin++) {

      // 4 swaps per (len - 2 x margin) - 1
      // so for len 3, we will have
      // 4 swaps per (3 - 0 - 0) - 1 = per 2

      // for len 4, we will have
      // 4 swaps per (4 - 0 - 0) - 1 = per 3 elements
      // then, 4 swaps per (4 - 1 - 1) - 1 = per 1 element

      for (int offset = 0; offset < m.length - margin - margin - 1; offset++) {
        // 4 swaps, then inc col
        // swap 1: 0, 0 to 0, 3
        int topLeft = m[margin][margin + offset];
        int topRight = m[margin + offset][m.length - 1 - margin];
        int bottomRight = m[m.length - 1 - margin][m.length - 1 - margin - offset];
        int bottomLeft = m[m.length - 1 - margin - offset][margin];

        m[margin + offset][m.length - 1 - margin] = topLeft; // put top left into top right
        m[m.length - 1 - margin][m.length - 1 - margin - offset] = topRight; // put top right into bottom right
        m[m.length - 1 - margin - offset][margin] = bottomRight; // put bottom right into bottom left
        m[margin][margin + offset] = bottomLeft; // put bottom left into top left
      }

    }
  }
}
