// Last updated: 4/5/2025, 8:36:02 PM
// Algorithm:
// 1. The key observation is that the XOR total of every subset can be computed using the bitwise OR of all numbers.
// 2. For each bit position, if the bit is set in at least one number, then it will contribute to the XOR total
//    in exactly half of all subsets.
// 3. Since there are 2^(n) subsets in total and each bit appears in 2^(n-1) subset XOR totals,
//    the sum of all subset XOR totals is: (OR of all numbers) * 2^(n-1).
//
// Pseudo Code:
// -------------
// function subsetXORSum(nums):
//     or = 0
//     for num in nums:
//         or = or OR num
//     return or * (2^(length(nums)-1))
//
// Visualization Example:
// ----------------------
// Example: nums = [1,3]
// - Compute OR: 1 OR 3 = 1 (001) OR 3 (011) = 3 (011)
// - There are 2 elements, so total subsets count = 2^2 = 4. Each bit contributes in 2^(2-1)=2 subsets.
// - Final answer = 3 * 2 = 6, which matches the example.
//
// Code:
class Solution {
    public int subsetXORSum(int[] nums) {
        int or = 0; // This will hold the bitwise OR of all numbers in the array.
        for (int num : nums) {
            or |= num; // Update the OR with the current number.
        }
        // (1 << (nums.length - 1)) computes 2^(n-1) where n is the number of elements in nums.
        // Multiply the OR with this value to get the final answer.
        return or * (1 << (nums.length - 1));
    }
}
