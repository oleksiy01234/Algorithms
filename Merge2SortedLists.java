/**
 * 21. Merge Two Sorted Lists (Easy)
 * https://leetcode.com/problems/merge-two-sorted-lists/
 * 
 * Merge two sorted linked lists and return it as a new list. The new list
 * should be made by splicing together the nodes of the first two lists.
 * 
 * Example: Input: 1->2->4, 1->3->4 Output: 1->1->2->3->4->4
 */
public class Merge2SortedLists {

  // recursive: O(min(n,m)) time and space
  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    if (l1 == null) {
      return l2;
    } else if (l2 == null) {
      return l1;
    } else if (l1.val < l2.val) {
      l1.next = mergeTwoLists(l1.next, l2);
      return l1;
    } else {
      l2.next = mergeTwoLists(l1, l2.next);
      return l2;
    }
  }

  // iterative O(min(n,m)) time, O(1) space
  public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
    ListNode preHead = new ListNode();
    ListNode n = preHead;

    while (l1 != null && l2 != null) {
      if (l1.val < l2.val) {
        n.next = l1;
        l1 = l1.next;
      } else {
        n.next = l2;
        l2 = l2.next;
      }

      n = n.next;
    }

    if (l1 != null) {
      n.next = l1;
    } else {
      n.next = l2;
    }

    return preHead.next;
  }
}