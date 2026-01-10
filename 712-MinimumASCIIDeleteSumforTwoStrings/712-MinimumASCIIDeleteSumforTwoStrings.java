// Last updated: 1/10/2026, 12:07:54 PM
1class Solution {
2    public int minimumDeleteSum(String s1, String s2) {
3        int m= s1.length(); int n= s2.length();
4        int[][] d= new int[m +1][n +1];
5        for(int i=1; i<=m; i++) d[i][0]= d[i-1][0]+ s1.charAt(i-1);
6        for(int j=1; j<=n; j++) d[0][j]= d[0][j-1]+ s2.charAt(j-1);
7        for(int i=1; i<=m; i++){
8            for(int j=1; j<=n; j++){
9                if(s1.charAt(i-1)== s2.charAt(j-1)) d[i][j]= d[i-1][j-1];
10                else d[i][j]= Math.min(d[i-1][j]+ s1.charAt(i-1), d[i][j-1]+ s2.charAt(j-1));
11            }
12        }
13        return d[m][n];
14    }
15}