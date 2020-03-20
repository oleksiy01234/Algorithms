import java.util.HashMap;
import java.util.Map;

/**
 * 146. LRU Cache
 * https://leetcode.com/problems/lru-cache/
 * 
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put in O(1) time complexity.
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 * LRUCache cache = new LRUCache(capacity );

 * cache.put(1,1);cache.put(2,2);cache.get(1); // returns 1
 * cache.put(3,3); // evicts key 2
 * cache.get(2); // returns -1 (not found)
 * cache.put(4,4); // evicts key 1
 * cache.get(1); // returns -1 (not found)
 * cache.get(3); // returns 3
 * cache.get(4); // returns 4
 */

public class LRUCache {
  Map<Integer, Node> map = new HashMap<>();
  Node head = null;
  Node tail = null;
  int max;

  public LRUCache(int capacity) {
    max = capacity;
  }

  public int get(int key) {
    if (!map.containsKey(key)) {
      return -1;
    }

    Node n = map.get(key);
    remove(n);
    prepend(n);
    return n.val;
  }

  public void put(int key, int val) {
    if (map.containsKey(key)) {
      Node node = map.get(key);
      node.val = val;
      remove(node);
      prepend(node);
      return;
    }

    if (map.size() == max) {
      map.remove(tail.key);
      remove(tail);
    }

    Node n = new Node(key, val);
    map.put(key, n);
    prepend(n);
  }

  public void prepend(Node n) {
    if (head == null) {
      head = n;
      tail = n;
      return;
    }

    n.next = head;
    n.prev = null;
    head.prev = n;
    head = n;
  }

  public void remove(Node n) {
    if (n == tail) {
      tail = n.prev;
    }
    if (n == head) {
      head = n.next;
    }

    if (n.prev != null) {
      n.prev.next = n.next;
    }

    if (n.next != null) {
      n.next.prev = n.prev;
    }

    n.next = null;
    n.prev = null;
  }

  public static void main(String[] args) {
    LRUCache c = new LRUCache(2);
    c.put(1, 1);
    c.print("after put(1, 1): ");
    c.put(2, 2);
    c.print("after put(2, 2): ");
    System.out.println(c.get(1));
    c.print("after get(1): ");
    System.out.println(c.get(3));
    c.print("after get(3): ");
    c.put(3, 3);
    c.print("after put(3, 3): ");
    c.put(4, 4);
    c.print("after put(4, 4): ");
  }

  // debug methods
  public void print(String message) {
    System.out.print(message);
    printHeadTail();
    printFromHead();
    printFromTail();
    System.out.println();
  }

  public void printFromHead() {
    Node n = head;
    System.out.print(". Forward: ");
    StringBuilder sb = new StringBuilder();

    while (n != null) {
      sb.append(n.key + "->");
      n = n.next;
    }

    System.out.print(sb.toString());
  }

  public void printFromTail() {
    Node n = tail;
    System.out.print(". Backward: ");
    StringBuilder sb = new StringBuilder();

    while (n != null) {
      sb.insert(0, "<-" + n.key);
      n = n.prev;
    }

    System.out.print(sb.toString());
  }

  public void printHeadTail() {
    System.out.print("Head: " + (head == null ? "null" : head.val));
    System.out.print("; Tail: " + (tail == null ? "null" : tail.val));
  }
}