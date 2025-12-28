// Last updated: 12/29/2025, 12:02:56 AM
1class Solution {
2    public int countNegatives(int[][] grid) {
3        int m=grid.length, n=grid[0].length;
4        int r=m-1, c=0, ans=0;
5        while(r>=0&&c<n){
6            if(grid[r][c]<0){
7                ans+=n-c;
8                r--;
9            }else c++;
10        }
11        return ans;
12    }
13}