import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * 207. Course Schedule
 * https://leetcode.com/problems/course-schedule/
 * 
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses-1.
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 * 
 * Example 1:
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take. 
 *              To take course 1 you should have finished course 0. So it is possible.
 * 
 * Example 2:
 * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take. 
 *              To take course 1 you should have finished course 0, and to take course 0 you should
 *              also have finished course 1. So it is impossible.
 * 
 * Constraints:
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 * You may assume that there are no duplicate edges in the input prerequisites.
 * 1 <= numCourses <= 10^5
 */
public class CourseSchedule1and2 {

  // Course Schedule: can finish = no cycles in directed graph
  public boolean canFinish(int numCourses, int[][] prerequisites) {
    List<List<Integer>> g = edgesToAdj(numCourses, prerequisites);
    Set<Integer> seeing = new HashSet<>();
    Set<Integer> seen = new HashSet<>();

    for (int i = 0; i < numCourses; i++) {
      if (dfsHasCycles(i, g, seeing, seen, new Stack<>())) { //  last param is dummy for Course Schedule 2
        return false;
      }
    }

    return true;
  }

  // Course Schedule 2: return a topological ordering or an empty array if impossible
  public int[] findOrder(int numCourses, int[][] prerequisites) {
    List<List<Integer>> g = edgesToAdj(numCourses, prerequisites);
    Set<Integer> seeing = new HashSet<>();
    Set<Integer> seen = new HashSet<>();
    Stack<Integer> sorted = new Stack<>();

    for (int i = 0; i < numCourses; i++) {
      if (dfsHasCycles(i, g, seeing, seen, sorted)) {
        return new int[] {};
      }
    }

    int[] res = new int[sorted.size()];
    while (!sorted.isEmpty()) {
      res[--numCourses] = sorted.pop();
    }
    return res;
  }

  List<List<Integer>> edgesToAdj(int n, int[][] edges) {
    List<List<Integer>> g = new ArrayList<>();
    while (n > 0) {
      g.add(new ArrayList<>());
      n--;
    }

    for (int[] edge : edges) {
      g.get(edge[0]).add(edge[1]);
    }

    return g;
  }

  // find actual cycle = use child->parent map
  boolean dfsHasCycles(int n, List<List<Integer>> g, Set<Integer> seeing, Set<Integer> seen, Stack<Integer> sorted) {
    if (seen.contains(n)) {
      return false;
    }

    seeing.add(n);

    for (int adj : g.get(n)) {
      if (seeing.contains(adj) || dfsHasCycles(adj, g, seeing, seen, sorted)) {
        return true;
      }
    }

    seeing.remove(n);
    seen.add(n);
    sorted.push(n);
    return false;
  }
}