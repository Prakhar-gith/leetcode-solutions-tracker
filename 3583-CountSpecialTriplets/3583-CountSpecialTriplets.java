// Last updated: 12/10/2025, 3:29:07 AM
1class Solution {
2    public int specialTriplets(int[] nums) {
3        int[] l= new int[200001];
4        int[] r= new int[200001];
5        for(int x : nums) r[x]++;
6        
7        long ans= 0;
8        long mod= 1000000007;
9        
10        for(int x : nums) {
11            r[x]--;
12            int target= x* 2;
13            if(target <= 200000) {
14                ans= (ans + (long)l[target] * r[target]) % mod;
15            }
16            l[x]++;
17        }
18        return (int)ans;
19    }
20}