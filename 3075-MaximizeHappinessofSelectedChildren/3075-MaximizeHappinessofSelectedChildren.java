// Last updated: 12/25/2025, 11:23:08 PM
1class Solution {
2    public long maximumHappinessSum(int[] happiness, int k) {
3        Arrays.sort(happiness);
4        long ans= 0;
5        int n= happiness.length;
6        for(int i= 0; i< k; i++){
7            ans+= Math.max(0, happiness[n- 1- i]- i);
8        }
9        return ans;
10    }
11}