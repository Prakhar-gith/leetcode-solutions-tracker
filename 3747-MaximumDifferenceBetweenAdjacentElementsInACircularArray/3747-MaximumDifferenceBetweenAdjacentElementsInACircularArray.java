// Last updated: 8/30/2025, 6:56:13 PM
class Solution {
    // Algorithm:
    // 1. Traverse the array once, compute abs(nums[i] - nums[i+1]) for i=0..n-2.
    // 2. Also compute the circular pair abs(nums[n-1] - nums[0]).
    // 3. Track the maximum of these absolute differences.
    //
    // Pseudo Code:
    // function maxAdjacentDistance(nums):
    //     n = length of nums
    //     maxDiff = 0
    //     for i from 0 to n-2:
    //         diff = abs(nums[i] - nums[i+1])
    //         maxDiff = max(maxDiff, diff)
    //     // circular pair:
    //     diff = abs(nums[n-1] - nums[0])
    //     maxDiff = max(maxDiff, diff)
    //     return maxDiff
    //
    /* Visualization:
       nums = [a, b, c, d]
       Pairs checked:
         |a-b|, |b-c|, |c-d|, and circular |d-a|
       Max of these is the answer.
    */

    public int maxAdjacentDistance(int[] nums) {
        int n = nums.length;
        // Initially assume maxDiff 0, since abs diff >= 0
        int maxDiff = 0;
        // Linear adjacent pairs
        for (int i = 0; i < n - 1; i++) {
            // currDiff is |nums[i] - nums[i+1]|
            int currDiff = Math.abs(nums[i] - nums[i + 1]);
            // Update maxDiff if this is larger
            if (currDiff > maxDiff) {
                maxDiff = currDiff; // kyunki yeh ab tak ka sabse bada difference hai
            }
        }
        // Circular pair: last and first element
        int circularDiff = Math.abs(nums[n - 1] - nums[0]);
        // Final comparison for circular case
        if (circularDiff > maxDiff) {
            maxDiff = circularDiff; // agar circular wala bada hai toh update karo
        }
        return maxDiff; // return the maximum absolute adjacent difference
    }
}
