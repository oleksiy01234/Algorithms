

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 139. Word Break
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 *
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 *
 * Example 1:
 * Input: s = "leetcode", wordDict = ["leet", "code"]
 * Output: true. "leetcode" can be segmented as "leet code".
 *
 * Example 2:
 * Input: s = "applepenapple", wordDict = ["apple", "pen"]
 * Output: true "applepenapple" can be segmented as "apple pen apple". You can reuse dict words.
 *
 * Example 3:
 * Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output: false
 */
public class WordBreak {

  public boolean wordBreak(String s, List<String> wordDict) {
    Set<String> set = new HashSet<>(wordDict);
    boolean[] dp = new boolean[s.length() + 1];
    dp[0] = true;
    for (int i = 1; i <= s.length(); i++) {
      for (int k = 0; k < i; k++) {
        if (dp[k] && set.contains(s.substring(k, i))) {
          dp[i] = true;
          break;
        }
      }
    }
    return dp[s.length()];
  }

  public boolean wordBreak2(String s, List<String> wordDict) {
    return dfs(s, new HashSet<>(wordDict), 0, new HashMap<>());
  }

  public boolean dfs(String s, Set<String> set, int start, Map<Integer, Boolean> memo) {
    if (start == s.length()) {
      return true;
    }

    if (memo.containsKey(start)) {
      return memo.get(start);
    }

    for (int end = start + 1; end <= s.length(); end++) {
      if (set.contains(s.substring(start, end)) && dfs(s, set, end, memo)) {
        memo.put(start, true);
        return true;
      }
    }

    memo.put(start, false);
    return false;
  }
}