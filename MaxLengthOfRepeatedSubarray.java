public class MaxLengthOfRepeatedSubarray {
  public int findLength(int[] a1, int[] a2) {
    int[][] dp = new int[a1.length + 1][a2.length + 1];
    int max = 0;

    for (int i = 1; i < dp.length; i++) {
      for (int k = 1; k < dp[i].length; k++) {
        if (a1[i - 1] == a2[k - 1]) {
          dp[i][k] = dp[i - 1][k - 1] + 1;
          max = Math.max(max, dp[i][k]);
        }
      }
    }

    return max;
  }

}
