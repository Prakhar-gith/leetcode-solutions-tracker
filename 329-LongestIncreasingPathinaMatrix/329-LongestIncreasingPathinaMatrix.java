// Last updated: 12/24/2025, 7:12:25 PM
1class Solution {
2    int[][] g, dp;
3    int n, m;
4    int[] d= {0, 1, 0, -1, 0};
5    public int longestIncreasingPath(int[][] matrix) {
6        g= matrix; n= g.length; m= g[0].length;
7        dp= new int[n][m];
8        int ans= 0;
9        for(int i= 0; i< n; i++)
10            for(int j= 0; j< m; j++)
11                ans= Math.max(ans, s(i, j));
12        return ans;
13    }
14    int s(int r, int c){
15        if(dp[r][c]> 0) return dp[r][c];
16        int mx= 1;
17        for(int k= 0; k< 4; k++){
18            int nr= r+ d[k], nc= c+ d[k+ 1];
19            if(nr>= 0&& nr< n&& nc>= 0&& nc< m&& g[nr][nc]> g[r][c])
20                mx= Math.max(mx, 1+ s(nr, nc));
21        }
22        return dp[r][c]= mx;
23    }
24}