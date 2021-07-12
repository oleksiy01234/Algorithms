import DataStructures.ListNode;

import java.util.HashMap;
import java.util.Map;

class RandomLinkedListCopy {
  // constant space
  public ListNode copyRandomList(ListNode oldHead) {
    if (oldHead == null) {
      return null;
    }

    // interweave new nodes
    ListNode n = oldHead;
    while (n != null) {
      ListNode nNext = n.next;
      n.next = new ListNode(n.val);
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
    ListNode newHead = oldHead.next;
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
  public ListNode copyRandomList2(ListNode head) {
    if (head == null) {
      return null;
    }

    Map<ListNode, ListNode> cloneMap = new HashMap<>();
    ListNode curr = head;
    while (curr != null) {
      cloneMap.put(curr, new ListNode(curr.val));
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