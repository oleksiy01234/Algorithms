package DataStructures;

public class ListNode {
  public ListNode next;
  public ListNode prev;
  public ListNode random;
  public ListNode left;
  public ListNode right;
  public ListNode parent; // for disjoint set, union find, friend circle
  public int key;
  public int val;
  public int rank;
  public int min;

  // for min stack
  public ListNode(int val, int min, ListNode next) {
    this.val = val;
    this.min = min;
    this.next = next;
  }

  public ListNode(int key, int val) {
    this.key = key;
    this.val = val;
  }

  public ListNode(int val) {
    this(0, val);
  }

  public ListNode() {
    this(0, 0);
  }
}