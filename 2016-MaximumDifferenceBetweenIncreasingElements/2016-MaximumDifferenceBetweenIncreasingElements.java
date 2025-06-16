// Last updated: 6/17/2025, 1:56:35 AM
class Solution {
    public int maximumDifference(int[] nums) {
        if (nums == null || nums.length < 2) {
            return -1; // Constraint: n >= 2, but good for robustness
        }

        int maxDiff = -1;
        int minElement = nums[0];

        for (int j = 1; j < nums.length; j++) {
            if (nums[j] > minElement) {
                maxDiff = Math.max(maxDiff, nums[j] - minElement);
            }
            // Always update minElement to the smallest value encountered so far
            // because a smaller minElement can lead to a larger future difference.
            minElement = Math.min(minElement, nums[j]);
        }

        return maxDiff;
    }
}