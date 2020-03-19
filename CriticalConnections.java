import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/critical-connections-in-a-network/
/**
 * CriticalConnections
 * 
 * Find articulation points in connected undirected graph. Articulation points
 * are vertices such that removing any one of them disconnects the graph.
 *
 * We need to do DFS of this graph and keep visitedTime and lowTime for each
 * vertex. lowTime keeps track of back edges.
 *
 * If any one of following condition meets then vertex is AP
 *
 * 1) If vertex is root of DFS and has at least 2 independent children.(By
 * independent it means they are not connected to each other except via this
 * vertex). This condition is needed because if we started from corner vertex it
 * will meet condition 2 but still is not an articulation point. To filter out
 * those vertices we need this condition.
 *
 * 2) It is not root of DFS and if visitedTime of vertex <= lowTime of any
 * adjacent vertex then its articulation point.
 *
 * Time complexity is O(E + V) Space complexity is O(V)
 *
 * References: https://en.wikipedia.org/wiki/Biconnected_component
 * http://www.geeksforgeeks.org/articulation-points-or-cut-vertices-in-a-graph/
 */
public class CriticalConnections {
  public static void main(String[] args) {
    List<List<Integer>> graph = new ArrayList<>();
    graph.add(Arrays.asList(0, 1));
    graph.add(Arrays.asList(0, 2));
    graph.add(Arrays.asList(2, 1));
    graph.add(Arrays.asList(2, 3));
    graph.add(Arrays.asList(2, 4));
    graph.add(Arrays.asList(3, 4));
    graph.add(Arrays.asList(5, 4));

    System.out.println(new CriticalConnections().criticalConnections(6, graph).toString() + "\n");

    graph.clear();
    graph.add(Arrays.asList(1, 2));
    graph.add(Arrays.asList(2, 3));
    graph.add(Arrays.asList(1, 3));
    graph.add(Arrays.asList(1, 4));
    graph.add(Arrays.asList(4, 5));
    graph.add(Arrays.asList(5, 6));
    graph.add(Arrays.asList(6, 7));
    graph.add(Arrays.asList(7, 5));
    graph.add(Arrays.asList(6, 0));

    System.out.println(new CriticalConnections().criticalConnections(8, graph).toString() + "\n");

    graph.clear();
    graph.add(Arrays.asList(0, 1));
    graph.add(Arrays.asList(0, 2));
    graph.add(Arrays.asList(0, 3));
    graph.add(Arrays.asList(0, 4));
    graph.add(Arrays.asList(4, 2));
    graph.add(Arrays.asList(3, 5));
    graph.add(Arrays.asList(4, 6));
    graph.add(Arrays.asList(6, 3));
    graph.add(Arrays.asList(6, 7));
    graph.add(Arrays.asList(6, 8));
    graph.add(Arrays.asList(7, 9));
    graph.add(Arrays.asList(9, 10));
    graph.add(Arrays.asList(8, 10));
    System.out.println(new CriticalConnections().criticalConnections(11, graph).toString() + "\n");

  }

  public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
    List<List<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      graph.add(new ArrayList<>());
    }

    for (List<Integer> edge : connections) {
      int v1 = edge.get(0);
      int v2 = edge.get(1);
      graph.get(v1).add(v2);
      graph.get(v2).add(v1);
    }

    int[] discTimes = new int[n]; // discovery time of each node
    int[] lowTimes = new int[n]; // earliest discovered node reachable from this node in DFS
    boolean[] visited = new boolean[n]; // whether this node has been visited in DFS
    List<List<Integer>> critEdges = new ArrayList<>();
    List<Integer> artPoints = new ArrayList<>();

    dfs(0, -1, 0, discTimes, lowTimes, visited, graph, critEdges, artPoints); // arbitrarily pick a node to start DFS
    artPoints.forEach(artPoint -> System.out.println("AP: " + artPoint));
    return critEdges;
  }

  // n = current node under consideration
  // parent = parent of current node
  private void dfs(int v, int parent, int discTime, int[] discTimes, int[] lowTimes, boolean[] seen,
      List<List<Integer>> graph, List<List<Integer>> critEdges, List<Integer> artPoints) {
    seen[v] = true;
    discTimes[v] = discTime;
    lowTimes[v] = discTimes[v]; // lowTime can't be higher than discTime

    // art points variables
    int childCount = 0;
    boolean isArtPoint = false;

    for (int neighbor : graph.get(v)) {
      if (neighbor == parent) {
        continue;
      }

      if (!seen[neighbor]) {
        childCount++;
        dfs(neighbor, v, discTime + 1, discTimes, lowTimes, seen, graph, critEdges, artPoints);

        if (discTimes[v] <= lowTimes[neighbor]) {
          isArtPoint = true;
          if (discTimes[v] < lowTimes[neighbor]) {
            critEdges.add(Arrays.asList(v, neighbor));
          }
        } else {
          lowTimes[v] = Math.min(lowTimes[v], lowTimes[neighbor]);
        }
      } else {
        lowTimes[v] = Math.min(lowTimes[v], discTimes[neighbor]);
      }
    }

    if ((parent == -1 && childCount >= 2) || (parent != -1 && isArtPoint)) {
      artPoints.add(v);
    }
  }
}