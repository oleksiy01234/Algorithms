package Amazon;

/**
 * ArticulationPoint
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Date 08/22/2015
 * 
 * @author Tushar Roy
 *
 *         Find articulation points in connected undirected graph. Articulation
 *         points are vertices such that removing any one of them disconnects
 *         the graph.
 *
 *         We need to do DFS of this graph and keep visitedTime and lowTime for
 *         each vertex. lowTime keeps track of back edges.
 *
 *         If any one of following condition meets then vertex is AP
 *
 *         1) If vertex is root of DFS and has at least 2 independent
 *         children.(By independent it means they are not connected to each
 *         other except via this vertex). This condition is needed because if we
 *         started from corner vertex it will meet condition 2 but still is not
 *         an articulation point. To filter out those vertices we need this
 *         condition.
 *
 *         2) It is not root of DFS and if visitedTime of vertex <= lowTime of
 *         any adjacent vertex then its articulation point.
 *
 *         Time complexity is O(E + V) Space complexity is O(V)
 *
 *         References: https://en.wikipedia.org/wiki/Biconnected_component
 *         http://www.geeksforgeeks.org/articulation-points-or-cut-vertices-in-a-graph/
 */
class ArticulationPoint<T> {
  int time;

  Set<Vertex<T>> findarticulationPoints(Graph<T> g) {
    Set<Vertex<T>> artPoints = new HashSet<>();

    dfs(new HashSet<>(), artPoints, g.getStartVertex(), new HashMap<>(), new HashMap<>(), null, 0);
    return artPoints;
  }

  void dfs(Set<Vertex<T>> visited, Set<Vertex<T>> artPoints, Vertex<T> v, Map<Vertex<T>, Integer> visitTime,
      Map<Vertex<T>, Integer> lowTime, Vertex<T> parent, int time) {

    visited.add(v);
    visitTime.put(v, time);
    lowTime.put(v, time);
    int childCount = 0;
    boolean isArticulationPoint = false;

    for (Vertex<T> adj : v.getAdjacentVertices()) {
      if (adj.equals(parent)) {
        continue;
      }

      if (!visited.contains(adj)) {
        childCount++;
        dfs(visited, artPoints, adj, visitTime, lowTime, v, time + 1);

        if (visitTime.get(v) <= lowTime.get(adj)) {
          isArticulationPoint = true;
        } else {
          // below operation basically does lowTime[vertex] = min(lowTime[vertex],
          // lowTime[adj]);
          lowTime.compute(v, (currentVertex, t) -> Math.min(t, lowTime.get(adj)));
        }

      } else { // if adj is already visited see if you can get better low time.
               // below operation basically does lowTime[vertex] = min(lowTime[vertex],
               // visitedTime[adj]);
        lowTime.compute(v, (currentVertex, t) -> Math.min(t, visitTime.get(adj)));
      }
    }

    // if either condition 1 or 2 meets). it is an articulation point.
    if ((parent == null && childCount >= 2) || parent != null && isArticulationPoint) {
      artPoints.add(v);
    }

  }

  public static void main(String args[]) {
    Graph<Integer> g1 = new Graph<>(false);
    g1.addEdge(1, 2);
    g1.addEdge(2, 3);
    g1.addEdge(1, 3);
    g1.addEdge(1, 4);
    g1.addEdge(4, 5);
    g1.addEdge(5, 6);
    g1.addEdge(6, 7);
    g1.addEdge(7, 5);
    g1.addEdge(6, 8);
    testAp(g1);

    Graph<Integer> g2 = new Graph<>(false);
    g2.addEdge(0, 1);
    g2.addEdge(0, 2);
    g2.addEdge(0, 3);
    g2.addEdge(0, 4);
    g2.addEdge(4, 2);
    g2.addEdge(3, 5);
    g2.addEdge(4, 6);
    g2.addEdge(6, 3);
    g2.addEdge(6, 7);
    g2.addEdge(6, 8);
    g2.addEdge(7, 9);
    g2.addEdge(9, 10);
    g2.addEdge(8, 10);

    testAp(g2);
  }

  static void testAp(Graph<Integer> graph) {
    ArticulationPoint<Integer> ap = new ArticulationPoint<>();
    Set<Vertex<Integer>> aPoints = ap.findarticulationPoints(graph);
    aPoints.forEach(point -> System.out.print(point + ", "));
    System.out.println();
  }
}