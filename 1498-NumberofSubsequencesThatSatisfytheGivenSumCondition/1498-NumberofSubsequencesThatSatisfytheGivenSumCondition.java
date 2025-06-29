// Last updated: 6/29/2025, 8:54:52 PM
class Solution {
    public int numSubseq(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int MOD = 1_000_000_007;

        // Precompute powers of 2 modulo MOD
        int[] pows = new int[n];
        pows[0] = 1;
        for (int i = 1; i < n; i++) {
            pows[i] = (pows[i - 1] * 2) % MOD;
        }

        int count = 0;
        int left = 0;
        int right = n - 1;

        while (left <= right) {
            if (nums[left] + nums[right] <= target) {
                // If nums[left] + nums[right] is within target,
                // then nums[left] can be the minimum.
                // Any element between nums[left] and nums[right] (inclusive)
                // can be part of the subsequence.
                // The number of elements from left to right (inclusive) is (right - left + 1).
                // If we fix nums[left] as min and nums[right] as max,
                // the elements nums[left+1]...nums[right-1] can be chosen or not chosen.
                // This gives 2^(right - left - 1) ways to choose elements between them.
                // Including nums[left] and nums[right] themselves, it's 2^(right - left) total subsequences
                // whose min is nums[left] and max is some element <= nums[right].
                // This logic is crucial: it effectively counts all subsequences where nums[left] is the minimum
                // and the maximum is any element from nums[left] up to nums[right]
                // (that also satisfies nums[left] + max_val <= target).
                // The number of choices for the elements between nums[left] and nums[right]
                // plus the choice of including nums[right] itself (as the upper bound)
                // results in 2^(right - left) subsequences.
                count = (count + pows[right - left]) % MOD;
                left++; // Try a larger minimum
            } else {
                // If nums[left] + nums[right] > target, then nums[right] is too large
                // to be the maximum for the current nums[left].
                // We need to try a smaller maximum.
                right--;
            }
        }

        return count;
    }
}