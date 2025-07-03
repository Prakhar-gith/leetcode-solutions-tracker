// Last updated: 7/3/2025, 9:00:40 PM
class Solution {
    public int minimizeMax(int[] nums, int p) {
        // Step 1: Sort the array
        Arrays.sort(nums);

        int n = nums.length;
        if (p == 0) {
            return 0; // If p is 0, no pairs needed, max difference is 0.
        }

        // Step 2: Define the search space for the maximum difference
        // The minimum possible difference is 0.
        // The maximum possible difference is the difference between the largest and smallest elements.
        int left = 0;
        int right = nums[n - 1] - nums[0];

        int minMaxDiff = right; // Initialize with a large possible answer

        // Step 3: Binary search on the possible maximum difference
        while (left <= right) {
            int mid = left + (right - left) / 2; // Current guess for the maximum difference

            // Check if we can form 'p' pairs with 'mid' as the maximum allowed difference
            if (canFormPairs(nums, p, mid)) {
                // If we can form 'p' pairs with 'mid', it means 'mid' is a possible answer.
                // We try to find a smaller maximum difference by searching in the left half.
                minMaxDiff = mid;
                right = mid - 1;
            } else {
                // If we cannot form 'p' pairs with 'mid', it means 'mid' is too small.
                // We need a larger maximum difference, so search in the right half.
                left = mid + 1;
            }
        }

        return minMaxDiff;
    }

    // Helper function to check if 'p' pairs can be formed with a given 'maxDiff'
    private boolean canFormPairs(int[] nums, int p, int maxDiff) {
        int pairsCount = 0;
        int i = 0;
        while (i < nums.length - 1) {
            // Greedily try to form a pair with the current and next element
            if (nums[i + 1] - nums[i] <= maxDiff) {
                pairsCount++;
                i += 2; // Both nums[i] and nums[i+1] are used, skip the next element
            } else {
                i++; // Only nums[i] is considered, move to the next element
            }
            if (pairsCount >= p) {
                return true; // We've found enough pairs
            }
        }
        return false;
    }
}