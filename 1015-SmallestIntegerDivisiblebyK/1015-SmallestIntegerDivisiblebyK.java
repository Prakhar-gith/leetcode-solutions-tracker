// Last updated: 12/4/2025, 7:29:31 PM
1class Solution {
2    public int smallestRepunitDivByK(int k) {
3        if(k% 2== 0 || k% 5== 0) return -1;
4        int r= 0;
5        for(int i= 1; i<= k; i++) {
6            r= (r* 10+ 1)% k;
7            if(r== 0) return i;
8        }
9        return -1;
10    }
11}