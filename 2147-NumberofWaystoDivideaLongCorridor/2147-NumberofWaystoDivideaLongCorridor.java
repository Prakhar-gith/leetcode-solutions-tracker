// Last updated: 12/14/2025, 11:07:52 PM
1class Solution {
2    public int numberOfWays(String corridor) {
3        long ans = 1;
4        int mod = 1000000007;
5        int seats = 0;
6        int prevSeatIdx = -1;
7        
8        for (int i = 0; i < corridor.length(); i++) {
9            if (corridor.charAt(i) == 'S') {
10                seats++;
11                
12                if (seats > 2 && seats % 2 == 1) {
13                    long ways = i - prevSeatIdx;
14                    ans = (ans * ways) % mod;
15                }
16                prevSeatIdx = i;
17            }
18        }
19        
20        if (seats == 0 || seats % 2 != 0) {
21            return 0;
22        }
23        
24        return (int) ans;
25    }
26}