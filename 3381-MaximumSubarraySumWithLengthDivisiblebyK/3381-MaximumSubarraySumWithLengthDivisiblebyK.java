// Last updated: 11/28/2025, 2:59:07 AM
1class Solution {
2    public long maxSubarraySum(int[] nums, int k) {
3        long[] minP= new long[k];
4        java.util.Arrays.fill(minP, Long.MAX_VALUE);
5        minP[0] = 0;
6        long cur= 0;
7        long ans = Long.MIN_VALUE;
8        
9        for(int i= 0; i< nums.length; i++) {
10            cur+= nums[i];
11            int rem = (i+ 1) % k;
12            if (minP[rem] != Long.MAX_VALUE) {
13                ans= Math.max(ans, cur- minP[rem]);
14            }
15            minP[rem] = Math.min(minP[rem], cur);
16        }
17        return ans;
18    }
19}