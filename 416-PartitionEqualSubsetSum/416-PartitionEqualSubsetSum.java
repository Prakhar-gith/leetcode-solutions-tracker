// Last updated: 4/7/2025, 9:25:44 PM
// Algorithm:
// 1. Sabse pehle, calculate total sum of the array. Agar sum odd hai, partition possible nahi, so return false.
// 2. Otherwise, target = total sum / 2. Ab hume ye check karna hai ke koi subset exists jiski sum exactly target ho.
// 3. Use dynamic programming (DP) with a boolean array "dp" of size (target+1):
//    - dp[s] is true if there exists a subset with sum s.
// 4. Initialize dp[0] = true (zero sum is always possible).
// 5. For each number in the array, iterate backwards from target down to the number and update:
//       if (dp[s - num] is true) then dp[s] becomes true.
//    Backward iteration ensure karta hai ki har number ko sirf ek baar use kiya jaye.
// 6. Finally, return dp[target] which tells if subset with sum target exists.
//
// Pseudo Code:
// -------------
// function canPartition(nums):
//     total = sum(nums)
//     if total is odd:
//         return false
//     target = total / 2
//     dp = boolean array of size target+1, initialize dp[0] = true
//     for each num in nums:
//         for s from target downto num:
//             dp[s] = dp[s] OR dp[s - num]
//     return dp[target]
//
// Visualization Example:
// ----------------------
// Example: nums = [1,5,11,5]
// total sum = 22, target = 11
// Initial dp: [true, false, false, ..., false] (length 12)
// Process 1: update dp[1] becomes true (since dp[0] was true)
// Process 5: update dp[5] becomes true (dp[0] true), dp[6] becomes true (dp[1] true)
// Process 11: update dp[11] becomes true (dp[0] true), dp[12] (not needed)...
// Process 5 (second occurrence): update dp[10] becomes true (dp[5] true), dp[11] remains true (dp[6] true)
// Finally, dp[11] is true, so answer is true.

class Solution {
    public boolean canPartition(int[] nums) {
        int total = 0;
        // Calculate total sum of array
        for (int num : nums) {
            total += num;
        }
        // Agar total sum odd hai, partition equal possible nahi
        if (total % 2 != 0) return false;
        
        int target = total / 2;
        // dp[s] means: can we get sum s using some subset of nums?
        boolean[] dp = new boolean[target + 1];
        dp[0] = true; // sum 0 is always possible (no elements)
        
        // For each number, update dp array backwards
        for (int num : nums) {
            // Backward iteration to avoid reusing the same number
            for (int s = target; s >= num; s--) {
                dp[s] = dp[s] || dp[s - num]; // Agar s-num possible hai, then s is possible with current num
            }
        }
        
        // Agar dp[target] true hai, then partition exists
        return dp[target];
    }
}
