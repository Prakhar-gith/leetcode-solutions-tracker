// Last updated: 12/2/2025, 4:38:05 PM
1class Solution {
2    public int countTrapezoids(int[][] points) {
3        java.util.Map<Integer, Integer> map = new java.util.HashMap<>();
4        for(int[] p : points) map.put(p[1], map.getOrDefault(p[1], 0) + 1);
5        
6        long mod = 1000000007;
7        long total = 0, sqSum = 0;
8        
9        for(int c : map.values()) {
10            if(c < 2) continue;
11            long w = (long)c * (c - 1) / 2 % mod;
12            total = (total + w) % mod;
13            sqSum = (sqSum + w * w) % mod;
14        }
15        
16        long ans = (total * total - sqSum + mod) % mod;
17        return (int)(ans * ((mod + 1) / 2) % mod);
18    }
19}