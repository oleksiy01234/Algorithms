import java.util.PriorityQueue;

/**
 * 973. K Closest Points to Origin
 * https://leetcode.com/problems/k-closest-points-to-origin/
 * 
 * We have a list of points on the plane.  Find the K closest points to the origin (0, 0).
 * 
 * Example 1:
 * Input: points = [[1,3],[-2,2]], K = 1
 * Output: [[-2,2]]
 * 
 * Explanation: 
 * The distance between (1, 3) and the origin is sqrt(10).
 * The distance between (-2, 2) and the origin is sqrt(8).
 * Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
 * We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
 * 
 * Example 2:
 * Input: points = [[3,3],[5,-1],[-2,4]], K = 2
 * Output: [[3,3],[-2,4]]
 * (The answer [[-2,4],[3,3]] would also be accepted.)
 */
public class KClosestPoints {

  public int[][] kClosest(int[][] points, int k) {
    PriorityQueue<int[]> pq = new PriorityQueue<>((p1, p2) -> {
      return findDist(p2) - findDist(p1);
    });

    for (int[] point : points) {
      pq.add(point);
      if (pq.size() > k) {
        pq.poll();
      }
    }

    int[][] res = new int[pq.size()][];
    for (int i = 0; i < res.length; i++) {
      res[i] = pq.poll();
    }
    return res;
  }

  int findDist(int[] p) {
    return p[0] * p[0] + p[1] * p[1];
  }
}