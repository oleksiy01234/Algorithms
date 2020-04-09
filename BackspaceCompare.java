/**
 * 844. Backspace String Compare
 * https://leetcode.com/problems/backspace-string-compare/submissions/
 * 
 * Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.

 * Example 1:
 * Input: S = "ab#c", T = "ad#c"
 * Output: true
 * Explanation: Both S and T become "ac".
 * 
 * Can you solve it in O(N) time and O(1) space?
 */
public class BackspaceCompare {

  // O(1) space solution. O(n) space is simple: build StringBuilders, then compare
  public boolean backspaceCompare(String s1, String s2) {
    for (int i1 = s1.length() - 1, i2 = s2.length() - 1; i1 >= 0 || i2 >= 0; i1--, i2--) {
      // find next non-skipped characters in s1 and s2
      i1 = getLastNonSkippedCharIndex(s1, i1);
      i2 = getLastNonSkippedCharIndex(s2, i2);

      // if neither exists, return true
      if (i1 < 0 && i2 < 0) {
        return true;
      }

      // if only one exists, or if both exist and are not the same, return false
      if (i1 < 0 || i2 < 0 || s1.charAt(i1) != s2.charAt(i2)) {
        return false;
      }
    }

    return true;
  }

  int getLastNonSkippedCharIndex(String s, int i) {
    int skip = 0;

    while (i >= 0) {
      if (s.charAt(i) == '#') {
        skip++;
      } else if (skip == 0) {
        break; // found the first non-skipped character
      } else {
        skip--;
      }
      i--;
    }

    return i;
  }
}