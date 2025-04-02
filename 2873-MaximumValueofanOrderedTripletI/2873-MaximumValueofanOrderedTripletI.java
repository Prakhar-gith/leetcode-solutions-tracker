// Last updated: 4/2/2025, 3:02:43 PM
class Solution {
    public long maximumTripletValue(int[] nums) {
        int n = nums.length;
        long[] pref = new long[n];
        pref[1] = (long)(nums[0] - nums[1]);
        long d = Math.max(nums[1], nums[0]);
        long g = 0;
        
        for (int i = 2; i <= n - 1; i++) {
            pref[i] = Math.max(pref[i - 1], d - nums[i]);
            
            long possible = (long)(nums[i]) * (pref[i - 1]);
            g = Math.max(g, possible);
            
            d = Math.max(d, nums[i]);
        }
        
        return g;
    }
}