package Amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CriticalConnections {
  class Edge {
    Edge(int a, int b) {
      this.a = a;
      this.b = b;
    }

    int a;
    int b;
  }

  List<Edge> criticalConnections(int serverCount, int edgeCount, List<Edge> edges) {
    Map<Integer, Set<Integer>> adj = new HashMap<>();
    for (Edge edge : edges) {
      int a = edge.a;
      int b = edge.b;

      if (adj.get(a) == null) {
        adj.put(a, new HashSet<>());
      }
      adj.get(a).add(b);

      if (adj.get(b) == null) {
        adj.put(b, new HashSet<>());
      }
      adj.get(b).add(a);
    }

    List<Edge> list = new ArrayList<>();
    Set<Integer> visited = new HashSet<>();

    for (Edge p : edges) {
      int x = p.a;
      int y = p.b;
      adj.get(x).remove(y);
      adj.get(y).remove(x);
      dfs(adj, 1, visited);
      if (visited.size() != serverCount) {
        if (p.a > p.b) {
          list.add(new Edge(p.b, p.a));
        } else {
          list.add(p);
        }
      }
      adj.get(x).add(y);
      adj.get(y).add(x);
    }
    return list;
  }

  public void dfs(Map<Integer, Set<Integer>> adj, int b, Set<Integer> visited) {
    visited.add(b);
    for (int v : adj.get(b)) {
      if (!visited.contains(v)) {
        dfs(adj, v, visited);
      }
    }
  }
}