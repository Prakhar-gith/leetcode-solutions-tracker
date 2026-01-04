// Last updated: 1/4/2026, 6:09:24 PM
1class Solution {
2    public int numOfWays(int n) {
3        long typeABA = 6; // Patterns using 2 colors (e.g., 121)
4        long typeABC = 6; // Patterns using 3 colors (e.g., 123)
5        long mod = 1_000_000_007;
6
7        for (int i = 1; i < n; i++) {
8            long nextABA = (3 * typeABA + 2 * typeABC) % mod;
9            long nextABC = (2 * typeABA + 2 * typeABC) % mod;
10            
11            typeABA = nextABA;
12            typeABC = nextABC;
13        }
14
15        return (int)((typeABA + typeABC) % mod);
16    }
17}