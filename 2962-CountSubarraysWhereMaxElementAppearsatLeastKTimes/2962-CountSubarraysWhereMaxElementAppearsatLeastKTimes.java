// Last updated: 4/30/2025, 4:09:37 AM
class Solution {
    public long countSubarrays(int[] nums, int k) {
        int maxElement = 0;
        for (int num : nums) {
            maxElement = Math.max(maxElement, num);
        }

        long count = 0;
        int left = 0;
        int maxCount = 0;

        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == maxElement) {
                maxCount++;
            }

            while (maxCount >= k) {
                count += nums.length - right;
                if (nums[left] == maxElement) {
                    maxCount--;
                }
                left++;
            }
        }

        return count;
    }
}