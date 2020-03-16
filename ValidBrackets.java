import java.util.Stack;

/**
 * 20. Valid Parentheses https://leetcode.com/problems/valid-parentheses/
 * 
 * Given a string containing just the characters '(', ')', '{', '}', '[' and
 * ']', determine if the input string is valid.
 * 
 * An input string is valid if: Open brackets must be closed by the same type of
 * brackets. Open brackets must be closed in the correct order. Valid: "()",
 * "()[]{}", "{[]}", "" Invalid: "(]", "([)]"
 */
public class ValidBrackets {

  public boolean isValid(String s) {
    Stack<Character> stack = new Stack<>();

    for (char c : s.toCharArray()) {
      switch (c) {
        case '(':
        case '[':
        case '{':
          stack.push(c);
          break;

        case ')':
          if (stack.isEmpty() || stack.pop() != '(') {
            return false;
          }
          break;
        case ']':
          if (stack.isEmpty() || stack.pop() != '[') {
            return false;
          }
          break;
        case '}':
          if (stack.isEmpty() || stack.pop() != '{') {
            return false;
          }
          break;
      }
    }

    return stack.isEmpty();
  }
}