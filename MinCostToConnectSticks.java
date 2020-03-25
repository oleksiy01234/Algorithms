import java.util.PriorityQueue;

/**
 * 1167. Minimum Cost to Connect Sticks [Medium]
 * https://leetcode.com/problems/minimum-cost-to-connect-sticks/
 * 
 * You have some sticks with positive integer lengths.
 * You can connect any two sticks of lengths X and Y into one stick by paying a cost of X + Y.  
 * You perform this action until there is one stick remaining.
 * 
 * Return the minimum cost of connecting all the given sticks into one stick in this way.
 * 
 * Example 1:
 * Input: sticks = [2,4,3]
 * Output: 14
 * 
 * Example 2:
 * Input: sticks = [1,8,3,5]
 * Output: 30
 */
public class MinCostToConnectSticks {

  public int connectSticks(int[] sticks) {
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    for (int stick : sticks) {
      pq.add(stick);
    }

    int cost = 0;

    while (pq.size() > 1) {
      int res = pq.poll() + pq.poll();
      cost += res;
      pq.add(res);
    }

    return cost;
  }

}