// Last updated: 12/11/2025, 2:33:01 AM
1class Solution {
2    public int countPermutations(int[] complexity) {
3        int Mod = 1_000_000_007;
4        long result = 1;
5        int first = complexity[0];
6
7        for(int i = 1; i < complexity.length; i++){
8            if(complexity[i] <= first) return 0;         
9            result = (result * i) % Mod;
10        }
11
12        return (int) result;
13    }
14}