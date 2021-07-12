import DataStructures.ListNode;

class MinStack {
  // DataStructures.ListNode in Util.Util.java
  ListNode head = null;
  
  public void push(int x) {
    ListNode n = new ListNode(x, head == null ? x : Math.min(head.min, x), head);
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