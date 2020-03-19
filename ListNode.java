class ListNode {
  ListNode next;
  ListNode prev;
  int key;
  int val;

  ListNode(int key, int val) {
    this.key = key;
    this.val = val;
  }

  ListNode() {
    new ListNode(0, 0);
  }
}