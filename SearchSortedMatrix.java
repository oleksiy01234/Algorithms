import java.util.Arrays;

/**
 * 74. Search a 2D Matrix
 * https://leetcode.com/problems/search-a-2d-matrix/
 * 
 * Write an efficient algorithm that searches for a value in an m x n matrix. 
 * This matrix has the following properties:
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 * 
 * Example 1:
 * Input:
 * matrix = [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * target = 3
 * Output: true
 * 
 * Example 2:
 * Input:
 * matrix = [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * target = 13
 * Output: false
 */

class SearchSortedMatrix {
  // 1. find the biggest row whose [0] <= t
  //    t can't be anywhere but that row
  //    if all rows > t, return false
  //
  // 2. binary search that row
  public boolean searchMatrix(int[][] m, int t) {
    if (m.length == 0 || m[0].length == 0) {
      return false;
    }

    int lo = 0, hi = m.length - 1;
    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      int rowVal = m[mid][0];

      if (rowVal > t) {
        hi = mid - 1;
      } else {
        if (mid == m.length - 1 || m[mid + 1][0] > t) {
          return Arrays.binarySearch(m[mid], t) >= 0;
        }

        lo = mid + 1;
      }
    }

    return lo == -1;
  }
}