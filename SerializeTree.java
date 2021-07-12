import DataStructures.ListNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 297. Serialize and Deserialize Binary Tree
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/submissions/
 * 
 * Serialization is the process of converting a data structure or object into a sequence of bits 
 * so that it can be stored in a file or memory buffer, or transmitted across a network connection 
 * link to be reconstructed later in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your  
 * serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be
 * serialized to a string and this string can be deserialized to the original tree structure.
 *
 * Example:
 * You may serialize the following tree:
 *
 *    1
 *   / \
 *  2   3
 *     / \
 *    4   5
 *
 * as "[1,2,3,null,null,4,5]"
 *
 * Clarification: The above format is the same as how LeetCode serializes a binary tree. You do not necessarily
 * need to follow this format, so please be creative and come up with different approaches yourself.
 *
 * Note: Do not use class member/global/static variables to store states. 
 * Your serialize and deserialize algorithms should be stateless.
 */

public class SerializeTree {
  // dfs preorder
  public String serialize(ListNode n) {
    if (n == null) {
      return "null,";
    }

    return n.val + "," + serialize(n.left) + serialize(n.right);
  }

  public ListNode deserialize(String s) {
    return deserialize(new StringBuilder(s));
  }

  ListNode deserialize(StringBuilder sb) {
    if (sb.length() == 0) {
      return null;
    }

    String val = sb.substring(0, sb.indexOf(","));
    sb.delete(0, val.length() + 1);
    if (val.equals("null")) {
      return null;
    }

    ListNode n = new ListNode(Integer.parseInt(val));
    n.left = deserialize(sb);
    n.right = deserialize(sb);
    return n;
  }

  // bfs inorder
  public String serialize2(ListNode root) {
    if (root == null) {
      return "";
    }

    StringBuilder sb = new StringBuilder();
    Queue<ListNode> q = new LinkedList<>();
    q.add(root);

    while (!q.isEmpty()) {
      ListNode n = q.poll();
      if (n == null) {
        sb.append("null,");
      } else {
        sb.append(n.val + ",");
        q.add(n.left);
        q.add(n.right);
      }
    }

    return sb.toString();
  }

  public ListNode deserialize2(String s) {
    if (s.isEmpty()) {
      return null;
    }

    String[] vals = s.split(",");
    Queue<ListNode> q = new LinkedList<>();
    int index = 0;

    ListNode root = makeNode(vals[index]);
    q.add(root);

    while (!q.isEmpty()) {
      ListNode n = q.poll();
      if (n == null) {
        continue;
      }

      n.left = makeNode(vals[++index]);
      n.right = makeNode(vals[++index]);
      q.add(n.left);
      q.add(n.right);
    }

    return root;
  }

  ListNode makeNode(String s) {
    return s.equals("null") ? null : new ListNode(Integer.parseInt(s));
  }
}