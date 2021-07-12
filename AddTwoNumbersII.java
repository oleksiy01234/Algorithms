import DataStructures.ListNode;

/**
 * 2. Add Two Numbers
 * https://leetcode.com/problems/add-two-numbers/
 *
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are storedin reverse order and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Example:
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 */
public class AddTwoNumbersII {

  /*
    Numbers are stored in normal order, ie most significant digit is first: 7 -> 2 -> 4 -> 3 = 7243
    Input: l1 = [7,2,4,3], l2 = [5,6,4]
    Output: [7,8,0,7]
   */

  // reverse the list, add them normally
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    l1 = reverse(l1);
    l2 = reverse(l2);

    ListNode n = null;
    boolean carry = false;

    while (l1 != null || l2 != null || carry) {
      int num = 0;

      if(l1 != null) {
        num += l1.val;
        l1 = l1.next;
      }

      if (l2 != null) {
        num += l2.val;
        l2 = l2.next;
      }

      if (carry) {
        num++;
      }

      ListNode someNode = new ListNode(num % 10);
      someNode.next = n;
      n = someNode;

      carry = num > 9;
    }
    return n;
  }

  private void print(ListNode n) {
    while (n != null) {
      System.out.print(n.val + "->");
      n = n.next;
    }

    System.out.println();
  }

  private ListNode reverse(ListNode n) {
    ListNode prev = null;

    while (n != null) {
      ListNode newNode = n.next;
      n.next = prev;
      prev = n;
      n = newNode;
    }

    return prev;
  }
}