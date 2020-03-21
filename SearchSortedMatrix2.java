import java.util.Arrays;

/**
 * 240. Search a 2D Matrix II
 * https://leetcode.com/problems/search-a-2d-matrix-ii/
 * 
 * Write an efficient algorithm that searches for a value in an m x n matrix:
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 * 
 * Example:
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 * Given target = 5, return true.
 * Given target = 20, return false.
 */
public class SearchSortedMatrix2 {

  // solution 2: O(r + c)
  public boolean searchMatrix(int[][] m, int t) {
    if (m.length == 0 || m[0].length == 0) {
      return false;
    }

    int r = 0, c = m[r].length - 1;
    while (r < m.length && c >= 0) {
      if (m[r][c] > t) {
        c--;
      } else if (m[r][c] < t) {
        r++;
      } else {
        return true;
      }
    }

    return false;
  }

  // attempt 1: O(r) * O(log c)
  public boolean searchMatrix2(int[][] m, int t) {
    for (int[] row : m) {
      if (Arrays.binarySearch(row, t) >= 0) {
        return true;
      }
    }

    return false;
  }
}