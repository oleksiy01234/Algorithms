import DataStructures.ListNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 547. Friend Circles
 * https://leetcode.com/problems/friend-circles/
 * 
 * Number of connected components
 * 
 * There are N students in a class. Some of them are friends, while some are not. Their friendship is transitive in nature. For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of C. And we defined a friend circle is a group of students who are direct or indirect friends.
 * Given a N*N matrix M representing the friend relationship between students in the class. If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not. And you have to output the total number of friend circles among all the students.

 * Example 1:
 * Input: 
 * [[1,1,0],
 *  [1,1,0],
 *  [0,0,1]]
 * Output: 2
 * Explanation:The 0th and 1st students are direct friends, so they are in a friend circle. 
 * The 2nd student himself is in a friend circle. So return 2.
 * Example 2:
 * Input: 
 * [[1,1,0],
 *  [1,1,1],
 *  [0,1,1]]
 * Output: 1
 * Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends, 
 * so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.
 * Note:
 * N is in range [1,200].
 * M[i][i] = 1 for all students.
 * If M[i][j] = 1, then M[j][i] = 1.
 */
public class FriendCircles {
  public static void main(String[] args) {
    System.out.println(new FriendCircles().findCircleNum2(new int[][] { { 1, 1, 0 }, { 1, 1, 0 }, { 0, 0, 1 } }));
    System.out.println(new FriendCircles().findCircleNum2(new int[][] { { 1, 1, 0 }, { 1, 1, 1 }, { 0, 1, 1 } }));
  }

  // approach 1: DFS
  public int findCircleNum1(int[][] m) {
    boolean[] visited = new boolean[m.length];
    int count = 0;
    for (int i = 0; i < m.length; i++) {
      if (!visited[i]) {
        dfs(m, visited, i);
        count++;
      }
    }
    return count;
  }

  public void dfs(int[][] m, boolean[] visited, int i) {
    for (int k = 0; k < m.length; k++) {
      if (m[i][k] == 1 && !visited[k]) {
        visited[k] = true; // unseen friend? mark as seen
        dfs(m, visited, k); // depth search his unseen friends
      }
    }
  }

  // approach 2: BFS
  public int findCircleNum2(int[][] m) {
    boolean[] visited = new boolean[m.length];
    int count = 0;

    Queue<Integer> q = new LinkedList<>();
    for (int i = 0; i < m.length; i++) {
      if (visited[i]) {
        continue;
      }

      q.add(i); // treat i as its own friend for the first time
      while (!q.isEmpty()) {
        int friend = q.remove(); // remove friend and process
        visited[friend] = true;
        for (int j = 0; j < m.length; j++) { // add all unseen friends of friend to q
          if (m[friend][j] == 1 && !visited[j]) {
            q.add(j);
          }
        }
      }
      count++; // can't get to any more people. if there are any more components, iterate more.
    }

    return count;
  }

  // approach 3: Union Set
  public int findCircleNum3(int[][] m) {
    Map<Integer, ListNode> map = new HashMap<>();

    for (int i = 0; i < m.length; i++) {
      makeSet(map, i);
    }

    for (int i = 0; i < m.length; i++) {
      for (int k = 0; k < m[i].length; k++) {
        if (m[i][k] == 1) {
          union(map, i, k);
        }
      }
    }

    Set<ListNode> set = new HashSet<>();
    for (ListNode n : map.values()) {
      set.add(findSet(n));
    }
    return set.size();
  }

  private void makeSet(Map<Integer, ListNode> map, int val) {
    ListNode n = new ListNode(val);
    n.rank = 0;
    n.parent = n;
    map.put(val, n);
  }

  private void union(Map<Integer, ListNode> map, int n1, int n2) {
    ListNode root1 = findSet(map.get(n1));
    ListNode root2 = findSet(map.get(n2));

    if (root1.val == root2.val) {
      return;
    }

    if (root1.rank > root2.rank) {
      root2.parent = root1;
    } else if (root1.rank < root2.rank) {
      root1.parent = root2;
    } else {
      root2.parent = root1;
      root1.rank++;
    }
  }

  private ListNode findSet(ListNode n) {
    if (n.parent == n) {
      return n;
    }

    n.parent = findSet(n.parent);
    return n.parent;
  }
}