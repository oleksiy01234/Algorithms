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
public class AddTwoNumbers {

  public Node addTwoNumbers(Node l1, Node l2) {
    Node preHead = new Node(), n = preHead;
    boolean carry = false;

    while (carry || l1 != null || l2 != null) {
      int num = carry ? 1 : 0;

      if (l1 != null) {
        num += l1.val;
        l1 = l1.next;
      }

      if (l2 != null) {
        num += l2.val;
        l2 = l2.next;
      }

      n.next = new Node(num % 10);
      n = n.next;
      carry = num >= 10;
    }

    return preHead.next;
  }
}