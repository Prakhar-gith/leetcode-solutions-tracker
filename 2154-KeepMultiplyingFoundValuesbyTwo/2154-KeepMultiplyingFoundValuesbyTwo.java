// Last updated: 11/20/2025, 12:31:49 AM
class Solution {
    public int findFinalValue(int[] nums, int original) {
        java.util.Arrays.sort(nums);
        for(int n : nums){
            if(n == original) {
                original *= 2;
            }
        }
        return original;
    }
}