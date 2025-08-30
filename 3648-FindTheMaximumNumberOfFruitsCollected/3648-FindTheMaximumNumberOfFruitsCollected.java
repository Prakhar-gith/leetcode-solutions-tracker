// Last updated: 8/30/2025, 6:56:27 PM
class Solution {
    public int maxCollectedFruits(int[][] fruits) {
        int n = fruits.length;

        long diagonalSum = 0;
        for (int i = 0; i < n; i++) {
            diagonalSum += fruits[i][i];
        }

        long maxFruitsC2 = calculateMaxPath(fruits, n, true);
        long maxFruitsC3 = calculateMaxPath(fruits, n, false);

        return (int) (diagonalSum + maxFruitsC2 + maxFruitsC3);
    }

    private long calculateMaxPath(int[][] fruits, int n, boolean isChild2) {
        long[][] dp = new long[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }

        if (isChild2) {
            dp[0][n - 1] = fruits[0][n - 1];
        } else {
            dp[0][n - 1] = fruits[n - 1][0];
        }
        
        for (int k = 1; k < n; k++) {
            for (int pos = 0; pos < n; pos++) {
                long maxPrevDp = -1;
                
                if (pos > 0 && dp[k - 1][pos - 1] != -1) {
                    maxPrevDp = Math.max(maxPrevDp, dp[k - 1][pos - 1]);
                }
                if (dp[k - 1][pos] != -1) {
                    maxPrevDp = Math.max(maxPrevDp, dp[k - 1][pos]);
                }
                if (pos < n - 1 && dp[k - 1][pos + 1] != -1) {
                    maxPrevDp = Math.max(maxPrevDp, dp[k - 1][pos + 1]);
                }

                if (maxPrevDp != -1) {
                    long fruitsAdded = 0;
                    if (isChild2) {
                        if (k != pos) {
                            fruitsAdded = fruits[k][pos];
                        }
                    } else {
                        if (pos != k) {
                            fruitsAdded = fruits[pos][k];
                        }
                    }
                    dp[k][pos] = maxPrevDp + fruitsAdded;
                }
            }
        }
        
        return dp[n - 1][n - 1];
    }
}