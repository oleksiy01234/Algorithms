import java.util.HashMap;
import java.util.Map;

class RandomLinkedListCopy {
  // constant space
  public Node copyRandomList(Node oldHead) {
    if (oldHead == null) {
      return null;
    }


    // interweave new nodes
    Node n = oldHead;
    while (n != null) {
      Node nNext = n.next;
      n.next = new Node(n.val);
      n.next.next = nNext;
      n = nNext;
    }

    // fix new random pointers
    n = oldHead;
    while (n != null) {
      if (n.random != null) {
        n.next.random = n.random.next;
      }
      n = n.next.next;
    }

    // un-weave old and new nodes
    Node newHead = oldHead.next;
    n = oldHead.next;
    while (n.next != null) {
      oldHead.next = n.next;
      n.next = n.next.next;
      n = n.next;
      oldHead = oldHead.next;
    }

    oldHead.next = null;
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