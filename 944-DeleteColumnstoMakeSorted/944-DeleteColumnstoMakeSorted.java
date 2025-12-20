// Last updated: 12/21/2025, 1:03:24 AM
1class Solution {
2    public int minDeletionSize(String[] strs) {
3        int c= 0;
4        int n= strs.length;
5        int m= strs[0].length();
6        for(int j=0; j< m; j++){
7            for(int i=0; i< n- 1; i++){
8                if(strs[i].charAt(j)> strs[i+1].charAt(j)){
9                    c++;
10                    break;
11                }
12            }
13        }
14        return c;
15    }
16}