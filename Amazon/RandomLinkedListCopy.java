package Amazon;

import java.util.HashMap;
import java.util.Map;

class RandomLinkedListCopy {
  static class Node {
    int val;
    Node next, random;

    Node(int x) {
      this.val = x;
    }
  };

  // constant space
  static Node copyRandomList(Node head) {
    if (head == null) {
      return null;
    }

    Node n = head;
    while (n != null) {
      Node newNode = new Node(n.val);
      newNode.next = n.next;
      n.next = newNode;
      n = newNode.next;
    }

    n = head;
    while (n != null) {
      if (n.random != null) {
        n.next.random = n.random.next;
      }
      n = n.next.next;
    }

    Node oldN = head;
    Node newN = head.next;
    Node newHead = head.next;

    while (oldN != null) {
      oldN.next = oldN.next.next;
      newN.next = (newN.next != null) ? newN.next.next : null;
      oldN = oldN.next;
      newN = newN.next;
    }

    return newHead;
  }

  // linear space
  public Node copyRandomList2(Node head) {
    if (head == null) {
      return null;
    }

    Map<Node, Node> cloneMap = new HashMap<>();
    Node curr = head;
    while (curr != null) {
      cloneMap.put(curr, new Node(curr.val));
      curr = curr.next;
    }

    curr = head;
    while (curr != null) {
      cloneMap.get(curr).next = cloneMap.get(curr.next);
      cloneMap.get(curr).random = cloneMap.get(curr.random);
      curr = curr.next;
    }

    return cloneMap.get(head);
  }
}