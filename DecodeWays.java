import java.util.HashMap;
import java.util.Map;

public class DecodeWays {
  public int numDecodings(String s) {
    if (s.charAt(0) == '0') {
      return 0;
    }

    int twoBack = 1;
    int oneBack = 1;

    for (int i = 1; i < s.length(); i++) {
      int current = 0;
      if (s.charAt(i) != '0') {
        current = oneBack;
      }

      int twoDigit = Integer.parseInt(s.substring(i - 1, i + 1));
      if (twoDigit >= 10 && twoDigit <= 26) {
        current += twoBack;
      }

      twoBack = oneBack;
      oneBack = current;
    }
    return oneBack;
  }

  private Map<String, Integer> memo = new HashMap<>();

  // rec with memo. O(n) due to memo -- each index only once. O(n) space
  public int numDecodings2(String s) {
    if (s.isEmpty()) {
      return 1;
    }

    if (s.charAt(0) == '0') {
      return 0;
    }

    if (memo.containsKey(s)) {
      return memo.get(s);
    }

    int numWays = numDecodings(s.substring(1));

    if (s.length() > 1 && Integer.parseInt(s.substring(0, 2)) <= 26) {
      numWays += numDecodings(s.substring(2));
    }

    memo.put(s, numWays);
    return numWays;
  }

}
