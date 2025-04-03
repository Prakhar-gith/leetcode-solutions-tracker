// Last updated: 4/3/2025, 9:55:34 PM
// Algorithm:
// 1. We observe that any valid triplet (i, j, k) with i < j < k yields a value:
//       (nums[i] - nums[j]) * nums[k]
//    To maximize this, we want:
//      - The difference (nums[i] - nums[j]) to be as large as possible. 
//        For a fixed j, the best nums[i] is the maximum element among indices [0, j-1].
//      - The multiplier nums[k] (with k > j) to be as large as possible.
// 2. For each possible middle index j (from 1 to n-2), we compute:
//       leftMax = max(nums[0 ... j-1])
//       diff = leftMax - nums[j]
//       rightMax = max(nums[j+1 ... n-1])
//       candidate = diff * rightMax
// 3. The answer is the maximum candidate over all valid j. If all candidates are negative (or zero), return 0.
//
// Pseudo Code:
// -------------
// function maximumTripletValue(nums):
//     n = length(nums)
//     best = -infinity
//     leftMax = nums[0]
//     Precompute rightMax for every j (or compute on the fly using reverse scan)
//     for j = 1 to n - 2:
//         leftMax = max(leftMax, nums[j-1])
//         candidate = (leftMax - nums[j]) * rightMax[j+1...n-1]
//         best = max(best, candidate)
//     return max(0, best)
//
// Visualization Example:
// ----------------------
// Example: nums = [12, 6, 1, 2, 7]
// j = 1:
//   leftMax = 12, diff = 12 - 6 = 6, rightMax = max(1,2,7)=7, candidate = 6*7 = 42
// j = 2:
//   leftMax = max(12,6)=12, diff = 12 - 1 = 11, rightMax = max(2,7)=7, candidate = 11*7 = 77
// j = 3:
//   leftMax = max(12,6,1)=12, diff = 12 - 2 = 10, rightMax = 7, candidate = 10*7 = 70
// Maximum candidate = 77, so answer = 77.

class Solution {
    public long maximumTripletValue(int[] nums) {
        int n = nums.length;
        // There must be at least one valid j (since n >= 3)
        long best = Long.MIN_VALUE;
        
        // Precompute an array that holds the maximum element to the right of every index
        // rightMax[i] = max(nums[i], nums[i+1], ..., nums[n-1])
        int[] rightMax = new int[n];
        rightMax[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(nums[i], rightMax[i + 1]);
        }
        
        // Maintain the maximum element seen so far to the left of index j
        int leftMax = nums[0];
        // Iterate j from 1 to n-2, because j must have both left and right elements.
        for (int j = 1; j <= n - 2; j++) {
            // Update leftMax from the left side (for j, consider max among [0, j-1])
            leftMax = Math.max(leftMax, nums[j - 1]);
            // For the current j, compute the difference
            int diff = leftMax - nums[j];
            // The best candidate for k is the maximum element in [j+1, n-1]
            int rightCandidate = rightMax[j + 1];
            long candidate = (long) diff * rightCandidate;
            best = Math.max(best, candidate);
        }
        
        // If the best candidate is negative, we return 0 as per problem statement.
        return best < 0 ? 0 : best;
    }
}
