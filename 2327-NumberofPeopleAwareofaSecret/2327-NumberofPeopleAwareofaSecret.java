// Last updated: 9/10/2025, 12:42:23 AM
class Solution {
    public int peopleAwareOfSecret(int n, int delay, int forget) {
        long mod = 1_000_000_007L;
        long[] dp = new long[n + 1];
        dp[1] = 1;
        long sharingCount = 0;

        for (int i = 2; i <= n; i++) {
            if (i - delay >= 1) {
                sharingCount = (sharingCount + dp[i - delay]) % mod;
            }
            if (i - forget >= 1) {
                sharingCount = (sharingCount - dp[i - forget] + mod) % mod;
            }
            dp[i] = sharingCount;
        }

        long totalAware = 0;
        for (int i = n - forget + 1; i <= n; i++) {
            totalAware = (totalAware + dp[i]) % mod;
        }
        return (int) totalAware;
    }
}