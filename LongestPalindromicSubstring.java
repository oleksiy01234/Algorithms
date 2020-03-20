/**
 * LongestPalindromicSubstring
 * https://leetcode.com/problems/longest-palindromic-substring/
 */
public class LongestPalindromicSubstring {

  // obvious brute forces is O(n^3): for each substring in O(n^2) check isPal in O(n)
  // expand around center is O(n^2)
  // another O(n^2) is DP
  public String longestPalindrome(String s) {
    String max = "";

    for (int i = 0; i < s.length(); i++) {
      max = findMax(max, maxPal(s, i, i), maxPal(s, i, i + 1));
    }

    return max;
  }

  String findMax(String... strings) {
    String max = "";

    for (String s : strings) {
      max = s.length() > max.length() ? s : max;
    }

    return max;
  }

  String maxPal(String s, int start, int end) {
    if (end == s.length() || s.charAt(start) != s.charAt(end)) {
      return "";
    }

    for (int i = start - 1, k = end + 1; i >= 0 && k < s.length(); i--, k++) {
      if (s.charAt(i) != s.charAt(k)) {
        break;
      }
      start = i;
      end = k;
    }

    return s.substring(start, end + 1);
  }
}