class MinStack {
  // Node in Util.java
  Node head = null;
  
  public void push(int x) {
    Node n = new Node(x, head == null ? x : Math.min(head.min, x), head);
    head = n;
  }

  public void pop() {
    head = head.next;
  }

  public int top() {
    return head.val;
  }

  public int getMin() {
    return head.min;
  }
}