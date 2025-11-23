// Last updated: 11/23/2025, 10:36:16 AM
class Solution {
    public int maxSumDivThree(int[] nums) {
        int[] dp= new int[]{0, Integer.MIN_VALUE, Integer.MIN_VALUE};
        for(int n: nums) {
            int[] next= dp.clone();
            for(int i : dp) {
                if(i != Integer.MIN_VALUE) {
                    int sum = i+ n;
                    next[sum% 3]= Math.max(next[sum%3], sum);
                }
            }
            dp = next;
        }
        return dp[0];
    }
}