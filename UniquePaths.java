public class UniquePaths {
    public int uniquePaths(int m, int n) {
        int[][] memo = new int[m][n];
        memo[0][0] = 1;
        return uniquePaths(memo, m - 1, n - 1);
    }

    private int uniquePaths(int[][] memo, int m, int n) {
        if (memo[m][n] != 0) {
            return memo[m][n];
        }

        if (m == 0) {
            memo[m][n] = uniquePaths(memo, m, n - 1);
        } else if (n == 0) {
            memo[m][n] = uniquePaths(memo, m - 1, n);
        } else {
            memo[m][n] = uniquePaths(memo, m - 1, n) + uniquePaths(memo, m, n - 1);
        }

        return memo[m][n];
    }

}
