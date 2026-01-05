// Last updated: 1/5/2026, 7:42:51 PM
1class Solution {
2    public long maxMatrixSum(int[][] matrix) {
3        long s =0; int mn= Integer.MAX_VALUE; int c =0;
4        for(int i=0; i <matrix.length; i++){
5            for(int j= 0;j< matrix[0].length;j ++){
6                int v= matrix[i][j];
7                if(v <0)c ++;
8                int a= Math.abs(v);
9                s+= a;
10                if(a < mn) mn= a;
11            }
12        }
13        if(c %2 !=0) s-= 2L* mn;
14        return s;
15    }
16}