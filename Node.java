class Node {
  Node next;
  Node prev;
  Node random;
  Node left;
  Node right;
  int key;
  int val;

  Node(int key, int val) {
    this.key = key;
    this.val = val;
  }

  Node(int key) {
    new Node(key, 0);
  }

  Node() {
    new Node(0, 0);
  }
}