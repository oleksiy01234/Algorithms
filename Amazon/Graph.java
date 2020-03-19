package Amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph<T> {
  List<Edge<T>> edges = new ArrayList<Edge<T>>();
  Map<Integer, Vertex<T>> vertices = new HashMap<>();
  boolean isDirected = false;

  Graph(boolean isDirected) {
    this.isDirected = isDirected;
  }

  void addEdge(int id1, int id2) {
    addEdge(id1, id2, 0);
  }

  // This works only for directed graph because for undirected graph we can end up
  // adding edges two times to allEdges
  void addVertex(Vertex<T> vertex) {
    if (vertices.containsKey(vertex.id)) {
      return;
    }
    vertices.put(vertex.id, vertex);
    for (Edge<T> edge : vertex.getEdges()) {
      edges.add(edge);
    }
  }

  public Vertex<T> addSingleVertex(int id) {
    if (vertices.containsKey(id)) {
      return vertices.get(id);
    }
    Vertex<T> v = new Vertex<T>(id);
    vertices.put(id, v);
    return v;
  }

  Vertex<T> getVertex(int id) {
    return vertices.get(id);
  }

  void addEdge(int id1, int id2, int weight) {
    Vertex<T> vertex1 = null;
    if (vertices.containsKey(id1)) {
      vertex1 = vertices.get(id1);
    } else {
      vertex1 = new Vertex<T>(id1);
      vertices.put(id1, vertex1);
    }
    Vertex<T> vertex2 = null;
    if (vertices.containsKey(id2)) {
      vertex2 = vertices.get(id2);
    } else {
      vertex2 = new Vertex<T>(id2);
      vertices.put(id2, vertex2);
    }

    Edge<T> edge = new Edge<T>(vertex1, vertex2, isDirected, weight);
    edges.add(edge);
    vertex1.addAdjacentVertex(edge, vertex2);
    if (!isDirected) {
      vertex2.addAdjacentVertex(edge, vertex1);
    }

  }

  public List<Edge<T>> getAllEdges() {
    return edges;
  }

  public Vertex<T> getStartVertex() {
    return vertices.values().iterator().next();
  }

  public void setDataForVertex(int id, T data) {
    if (vertices.containsKey(id)) {
      Vertex<T> vertex = vertices.get(id);
      vertex.data = data;
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Edge<T> edge : getAllEdges()) {
      sb.append(edge.vertex1 + " " + edge.vertex2 + " " + edge.weight + "\n");
    }
    return sb.toString();
  }
}

class Vertex<T> {
  int id;
  T data;
  List<Edge<T>> edges = new ArrayList<>();
  List<Vertex<T>> adjacentVertex = new ArrayList<>();

  Vertex(int id) {
    this.id = id;
  }

  public T getData() {
    return data;
  }

  public void addAdjacentVertex(Edge<T> e, Vertex<T> v) {
    edges.add(e);
    adjacentVertex.add(v);
  }

  public String toString() {
    return String.valueOf(id);
  }

  public List<Vertex<T>> getAdjacentVertices() {
    return adjacentVertex;
  }

  public List<Edge<T>> getEdges() {
    return edges;
  }

  public int getDegree() {
    return edges.size();
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof Vertex<?> && id == ((Vertex<?>) obj).id;
  }
}

class Edge<T> {
  boolean isDirected = false;
  Vertex<T> vertex1;
  Vertex<T> vertex2;
  int weight;

  Edge(Vertex<T> vertex1, Vertex<T> vertex2, boolean isDirected, int weight) {
    this.vertex1 = vertex1;
    this.vertex2 = vertex2;
    this.weight = weight;
    this.isDirected = isDirected;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Edge<?>) {
      Edge<?> other = (Edge<?>) obj;
      if (vertex1 == null) {
        if (other.vertex1 != null) {
          return false;
        }
      } else if (!vertex1.equals(other.vertex1)) {
        return false;
      }
      if (vertex2 == null) {
        if (other.vertex2 != null) {
          return false;
        }
      } else if (!vertex2.equals(other.vertex2)) {
        return false;
      }

    }
    return false;
  }

  @Override
  public String toString() {
    return "Edge [isDir=" + isDirected + ", v1=" + vertex1 + ", v2=" + vertex2 + ", w=" + weight + "]";
  }
}