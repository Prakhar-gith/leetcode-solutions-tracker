// Last updated: 1/8/2026, 9:35:33 PM
1class Solution {
2    public int maxDotProduct(int[] nums1, int[] nums2) {
3        int n= nums1.length; int m= nums2.length;
4        int[][] dp= new int[n +1][ m+1];
5        for(int i=0;i <=n;i++) for(int j=0;j <=m;j++) dp[i][j]= -1000000000;
6        for(int i=1; i<=n; i++){
7            for(int j=1; j<=m; j++){
8                int p= nums1[i-1]* nums2[j-1];
9                dp[i][j]= Math.max(p, Math.max(dp[i-1][j-1]+ p, Math.max(dp[i-1][j], dp[i][j-1])));
10            }
11        }
12        return dp[n][m];
13    }
14}