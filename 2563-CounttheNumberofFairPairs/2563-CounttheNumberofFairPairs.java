// Last updated: 4/19/2025, 8:45:21 PM
class Solution {
    public long countFairPairs(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        long result = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int a = nums[i];
            int left = lower - a;
            int right = upper - a;
            
            // Find the first j >= i+1 where nums[j] >= left
            int lowerBound = findLowerBound(nums, i + 1, left);
            // Find the last j >= i+1 where nums[j] <= right
            int upperBound = findUpperBound(nums, i + 1, right);
            
            if (lowerBound <= upperBound) {
                result += (upperBound - lowerBound + 1);
            }
        }
        return result;
    }
    
    private int findLowerBound(int[] nums, int start, int target) {
        int left = start;
        int right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
    
    private int findUpperBound(int[] nums, int start, int target) {
        int left = start;
        int right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left - 1;
    }
}