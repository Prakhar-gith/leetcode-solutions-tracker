// Last updated: 12/4/2025, 7:28:25 PM
1class Solution {
2    public int numberOfPaths(int[][] grid, int k) {
3        int m= grid.length;
4        int n= grid[0].length;
5        int mod= 1000000007;
6        int[][][] dp= new int[m][n][k];
7        
8        dp[0][0][grid[0][0]% k]= 1;
9        
10        for(int i=0; i< m; i++) {
11            for(int j= 0; j< n; j++){
12                for(int r=0; r< k; r++){
13                    if(i> 0) {
14                        int rem = (r + grid[i][j])% k;
15                        dp[i][j][rem]= (dp[i][j][rem] + dp[i-1][j][r])% mod;
16                    }
17                    if(j > 0){
18                        int rem = (r+ grid[i][j]) % k;
19                        dp[i][j][rem]= (dp[i][j][rem] + dp[i][j-1][r])% mod;
20                    }
21                }
22            }
23        }
24        return dp[m- 1][n- 1][0];
25    }
26}