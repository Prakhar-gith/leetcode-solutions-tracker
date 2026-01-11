// Last updated: 1/12/2026, 12:44:32 AM
1class Solution {
2    public int maximalRectangle(char[][] matrix) {
3        if(matrix.length==0) return 0;
4        int m= matrix.length; int n= matrix[0].length;
5        int[] l= new int[n]; int[] r= new int[n]; int[] h= new int[n];
6        Arrays.fill(r, n); int mx=0;
7        for(int i= 0; i< m; i++){
8            int cl=0; int cr= n;
9            for(int j=0; j<n; j++){
10                if(matrix[i][j]=='1') h[j]++;
11                else h[j]=0;
12            }
13            for(int j=0; j<n; j++){
14                if(matrix[i][j]=='1') l[j]= Math.max(l[j], cl);
15                else{ l[j]=0; cl= j+1; }
16            }
17            for(int j=n-1; j >=0; j--){
18                if(matrix[i][j]=='1') r[j]= Math.min(r[j], cr);
19                else{ r[j]= n; cr= j; }
20            }
21            for(int j=0; j<n; j++){
22                int a= (r[j]- l[j])* h[j];
23                if(a> mx) mx= a;
24            }
25        }
26        return mx;
27    }
28}