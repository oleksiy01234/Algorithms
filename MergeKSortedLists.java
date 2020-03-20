import java.util.PriorityQueue;

/**
 * 23. Merge k Sorted Lists
 * https://leetcode.com/problems/merge-k-sorted-lists/
 * 
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

 * Example:
 * Input:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * Output: 1->1->2->3->4->4->5->
 */
public class MergeKSortedLists {

  public Node mergeKLists(Node[] lists) {
    PriorityQueue<Node> pq = new PriorityQueue<>((l1, l2) -> l1.val - l2.val);

    for (Node l : lists) {
      pq.add(l);
    }

    Node preHead = new Node();
    Node n = preHead;

    while (!pq.isEmpty()) {
      n.next = pq.poll();
      if (n.next.next != null) {
        pq.add(n.next.next);
      }
      n = n.next;
    }

    return preHead.next;
  }
}