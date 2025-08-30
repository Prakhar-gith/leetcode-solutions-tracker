// Last updated: 8/30/2025, 6:56:44 PM
class Solution {
    public int maximumLength(int[] nums, int k) {
        /*
         * ============================================================================================
         * |                        (Comments)                                       |
         * ============================================================================================
         *
         * A valid subsequence is one where the sum of every two consecutive elements modulo k is constant.
         * (sub[0] + sub[1]) % k == (sub[1] + sub[2]) % k == ...
         *
         * We need to find the length of the longest such subsequence.
         *
         * Approach: Dynamic Programming
         * We can solve this problem using Dynamic Programming.
         *
         * Key Idea:
         * Every valid subsequence follows a particular 'target_sum' (between 0 and k-1).
         * We will find the longest subsequence for each possible `target_sum` and then take the maximum of all these lengths.
         *
         * DP State:
         * We will create a 2D DP table: `dp[target_sum][last_rem]`
         * `dp[ts][rem]` means: "The length of a valid subsequence whose constant pair-sum modulo k is 'ts',
         * and which ends with a number whose remainder is 'rem'."
         *
         * DP Transition (Logic):
         * We will loop through each element `num` of the `nums` array.
         * Let `rem = num % k`.
         * This `num` can extend a previous subsequence.
         * If we add `num` to a subsequence that has a `target_sum` of 'ts', then the last element of that subsequence
         * must have a remainder `prev_rem` such that `(prev_rem + rem) % k == ts`.
         * From this, we get `prev_rem`: `prev_rem = (ts - rem + k) % k`.
         *
         * The length of the old subsequence (ending at `prev_rem`) was `dp[ts][prev_rem]`.
         * After adding `num`, the new length becomes `1 + dp[ts][prev_rem]`.
         * This new subsequence now ends at `rem`, so we update `dp[ts][rem]`.
         *
         * `dp[ts][rem] = 1 + dp[ts][prev_rem]`
         *
         * After every update, we will also update the `maxLength`.
         *
         * ============================================================================================
         * |                                  Algorithm                                               |
         * ============================================================================================
         *
         * 1. Initialize a 2D DP array `dp[k][k]` with all values as 0.
         * `dp[ts][rem]` will store the length of the longest valid subsequence with a constant
         * pair-sum `ts` and ending with an element having remainder `rem`.
         *
         * 2. Initialize a variable `maxLength` to 0 to keep track of the maximum length found so far.
         *
         * 3. Iterate through each number `num` in the input array `nums`.
         * a. Calculate its remainder: `rem = num % k`.
         * b. Iterate through all possible target sums `ts` from 0 to `k-1`.
         * i.  Determine the required remainder of the previous element in the subsequence:
         * `prev_rem = (ts - rem + k) % k`.
         * ii. The length of the new subsequence is `1 +` the length of the subsequence we are extending.
         * The length of the old subsequence (ending in `prev_rem` with `target_sum` as `ts`) is `dp[ts][prev_rem]`.
         * iii.Update the DP table for the current state: `dp[ts][rem] = 1 + dp[ts][prev_rem]`.
         * iv. Update the overall `maxLength`: `maxLength = max(maxLength, dp[ts][rem])`.
         *
         * 4. After iterating through all numbers, `maxLength` will hold the length of the longest valid subsequence. Return `maxLength`.
         *
         * ============================================================================================
         * |                                 Pseudocode                                               |
         * ============================================================================================
         *
         * function maximumLength(nums, k):
         * // dp[ts][rem] = length of subsequence with target_sum 'ts' ending in 'rem'
         * // dp table is of size k x k.
         * dp = new int[k][k] initialized to 0
         *
         * maxLength = 0
         *
         * // Iterate through each number in nums
         * for each num in nums:
         * rem = num % k
         *
         * // Check for every possible target_sum
         * for ts from 0 to k-1:
         * // What should the previous remainder be?
         * prev_rem = (ts - rem + k) % k
         *
         * // Update the DP state. Increase length by adding the current number.
         * // dp[ts][prev_rem] gives the old length.
         * dp[ts][rem] = 1 + dp[ts][prev_rem]
         *
         * // Keep updating the overall maximum length
         * maxLength = max(maxLength, dp[ts][rem])
         *
         * return maxLength
         *
         * ============================================================================================
         * |                             Text Visualization                                           |
         * ============================================================================================
         *
         * Let's visualize for nums = [1, 2, 3, 4, 5], k = 2.
         * Remainders mod 2 are [1, 0, 1, 0, 1].
         * DP table `dp[target_sum][rem]` is `dp[2][2]`.
         *
         * Initially: `dp = [[0, 0], [0, 0]]`, `maxLength = 0`.
         *
         * 1. num = 1 (rem = 1):
         * - ts = 0, prev_rem = 1 -> dp[0][1] = 1 + dp[0][1] = 1.
         * - ts = 1, prev_rem = 0 -> dp[1][1] = 1 + dp[1][0] = 1.
         * - dp = [[0, 1], [0, 1]], maxLength = 1.
         *
         * 2. num = 2 (rem = 0):
         * - ts = 0, prev_rem = 0 -> dp[0][0] = 1 + dp[0][0] = 1.
         * - ts = 1, prev_rem = 1 -> dp[1][0] = 1 + dp[1][1] = 2. (Subseq: [1, 2])
         * - dp = [[1, 1], [2, 1]], maxLength = 2.
         *
         * 3. num = 3 (rem = 1):
         * - ts = 0, prev_rem = 1 -> dp[0][1] = 1 + dp[0][1] = 2. (Subseq: [1, 3])
         * - ts = 1, prev_rem = 0 -> dp[1][1] = 1 + dp[1][0] = 3. (Subseq: [1, 2, 3])
         * - dp = [[1, 2], [2, 3]], maxLength = 3.
         *
         * 4. num = 4 (rem = 0):
         * - ts = 0, prev_rem = 0 -> dp[0][0] = 1 + dp[0][0] = 2. (Subseq: [2, 4])
         * - ts = 1, prev_rem = 1 -> dp[1][0] = 1 + dp[1][1] = 4. (Subseq: [1, 2, 3, 4])
         * - dp = [[2, 2], [4, 3]], maxLength = 4.
         *
         * 5. num = 5 (rem = 1):
         * - ts = 0, prev_rem = 1 -> dp[0][1] = 1 + dp[0][1] = 3. (Subseq: [1, 3, 5])
         * - ts = 1, prev_rem = 0 -> dp[1][1] = 1 + dp[1][0] = 5. (Subseq: [1, 2, 3, 4, 5])
         * - dp = [[2, 3], [4, 5]], maxLength = 5.
         *
         *  5.
         */

        // Create the DP table. dp[target_sum][last_remainder]
        // This will store the length of the longest valid subsequence.
        int[][] dp = new int[k][k];

        // This variable will track the length of the longest subsequence found.
        int maxLength = 0;

        // Iterate over each element of the nums array.
        for (int num : nums) {
            // Calculate the remainder of the current number.
            int rem = num % k;

            // Check for every possible target sum (from 0 to k-1).
            for (int ts = 0; ts < k; ts++) {
                // If the target_sum is 'ts', and the current remainder is 'rem',
                // what should the remainder of the previous element have been?
                // (prev_rem + rem) % k = ts  =>  prev_rem = (ts - rem + k) % k
                int prev_rem = (ts - rem + k) % k;

                // The new length will be 1 + the length of the old subsequence.
                // The old subsequence was one with target_sum 'ts' that ended at 'prev_rem'.
                // Its length is stored in dp[ts][prev_rem].
                dp[ts][rem] = 1 + dp[ts][prev_rem];

                // Keep updating the overall maximum length.
                maxLength = Math.max(maxLength, dp[ts][rem]);
            }
        }

        // Return the final result.
        return maxLength;
    }
}