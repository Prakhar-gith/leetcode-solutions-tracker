// Last updated: 12/8/2025, 6:47:24 PM
1class Solution {
2    public int countTriples(int n) {
3        int ans= 0;
4        for(int i= 1; i<= n; i++) {
5            for(int j= 1; j<= n; j++) {
6                int sum= i* i+ j* j;
7                int c= (int)Math.sqrt(sum);
8                if(c<= n && c* c== sum) ans++;
9            }
10        }
11        return ans;
12    }
13}