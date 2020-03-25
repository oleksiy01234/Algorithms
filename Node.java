class Node {
  Node next;
  Node prev;
  Node random;
  Node left;
  Node right;
  Node parent; // for disjoint set, union find, friend circle
  int key;
  int val;
  int rank;
  int min;

  // for min stack
  Node(int val, int min, Node next) {
    this.val = val;
    this.min = min;
    this.next = next;
  }
  
  Node(int key, int val) {
    this.key = key;
    this.val = val;
  }

  Node(int val) {
    this(0, val);
  }

  Node() {
    this(0, 0);
  }
}