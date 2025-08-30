// Last updated: 8/30/2025, 6:55:59 PM
class Solution {
    public int minOperations(int[] nums, int k) {
        int sum = 0;
        for(int num : nums){
            sum += num;
        }
        int rem = sum % k;
        return rem == 0 ? 0 : rem;
    }
}