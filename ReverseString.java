/**
 * 344. Reverse A String (Easy): https://leetcode.com/problems/reverse-string/
 * 
 * Write a function that reverses a string in place
 */
public class ReverseString {
  public static void test() {
    ReverseString t = new ReverseString();
    System.out.println(t.reverseString("abcd"));
    System.out.println(t.reverseString("ab"));
    System.out.println(t.reverseString("a"));
    System.out.println(t.reverseString(""));
  }

  // iterative, single pointer. O(n) time, O(1) space
  public void reverseString(char[] s) {
    for (int i = 0; i < s.length / 2; i++) {
      Util.swap(s, i, s.length - i - 1);
    }
  }

  // recursive, String, O(n) time and space
  public String reverseString(String s) {
    if (s.length() < 2) {
      return s;
    }

    return reverseString(s.substring(1)) + s.charAt(0);
  }

  // recursive, char[], O(n) time and space
  public void reverseString2(char[] s) {
    reverseString(s, 0, s.length - 1);
  }

  void reverseString(char[] s, int lo, int hi) {
    if (lo >= hi) {
      return;
    }

    Util.swap(s, lo, hi);
    reverseString(s, ++lo, --hi);
  }
}