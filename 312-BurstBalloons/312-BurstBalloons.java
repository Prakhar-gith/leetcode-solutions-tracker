// Last updated: 4/1/2025, 12:22:38 PM
class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] balloons = new int[n + 2];
        balloons[0] = 1;
        balloons[n + 1] = 1;
        for (int i = 0; i < n; i++) {
            balloons[i + 1] = nums[i];
        }
        int[][] dp = new int[n + 2][n + 2];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }
        return burstBalloonsRecursively(balloons, 0, n + 1, dp);
    }

    public int burstBalloonsRecursively(int[] balloons, int start, int end, int[][] dp) {
        if (start + 1 >= end) return 0;
        if (dp[start][end] != -1) return dp[start][end];
        int maxCoins = Integer.MIN_VALUE;
        for (int i = start + 1; i < end; i++) {
            int leftCoins = burstBalloonsRecursively(balloons, start, i, dp);
            int rightCoins = burstBalloonsRecursively(balloons, i, end, dp);
            int currentCoins = leftCoins + rightCoins + balloons[start] * balloons[i] * balloons[end];
            maxCoins = Math.max(maxCoins, currentCoins);
        }
        dp[start][end] = maxCoins;
        return maxCoins;
    }
}