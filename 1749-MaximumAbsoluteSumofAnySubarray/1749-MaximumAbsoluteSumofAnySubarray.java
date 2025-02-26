// Algorithm:
// 1. Hum maximum aur minimum subarray sum nikalenge using Kadane's algorithm.
// 2. Har element ke liye, hum current maximum sum (maxEndingHere) update karenge:
//    - maxEndingHere = max(current element, maxEndingHere + current element)
//    - Isse decide hota hai ki current element se new subarray shuru karein ya previous sum extend karein.
// 3. Isi tarah, current minimum sum (minEndingHere) update karenge:
//    - minEndingHere = min(current element, minEndingHere + current element)
// 4. Overall maximum (maxSoFar) aur minimum (minSoFar) subarray sum track karte rahenge.
// 5. Final answer hoga max(maxSoFar, |minSoFar|) yani maximum absolute subarray sum.
//
// Pseudo Code:
// -------------
// function maxAbsoluteSum(nums):
//     maxEnding = 0, minEnding = 0, maxOverall = 0, minOverall = 0
//     for each x in nums:
//         maxEnding = max(x, maxEnding + x)
//         maxOverall = max(maxOverall, maxEnding)
//         minEnding = min(x, minEnding + x)
//         minOverall = min(minOverall, minEnding)
//     return max(maxOverall, -minOverall)
//
// Visualization:
// --------------
// Consider nums = [2, -5, 1, -4, 3, -2]:
//   - Kadane's algorithm se maximum subarray sum (maxSoFar) aur minimum subarray sum (minSoFar) nikalte hain.
//   - For example, maxSoFar might be 3 and minSoFar might be -8 for some subarray.
//   - Maximum absolute sum is then max(3, |-8|) = 8.
// This gives a clear idea ki dono positive aur negative subarrays ko consider karke maximum absolute sum nikala jata hai.

class Solution {
    public int maxAbsoluteSum(int[] nums) {
        // Initialize variables to store current maximum and minimum subarray sums
        int maxEndingHere = 0; // current max sum ending at the current index
        int minEndingHere = 0; // current min sum ending at the current index
        int maxSoFar = 0;      // overall maximum subarray sum found so far
        int minSoFar = 0;      // overall minimum subarray sum found so far

        // Iterate over each element in the array
        for (int x : nums) {
            // Update current maximum sum: decide whether to start new subarray at x or extend previous sum
            maxEndingHere = Math.max(x, maxEndingHere + x);
            // Update overall maximum if current ending sum is greater
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
            
            // Update current minimum sum: decide whether to start new subarray at x or extend previous negative sum
            minEndingHere = Math.min(x, minEndingHere + x);
            // Update overall minimum if current ending sum is smaller
            minSoFar = Math.min(minSoFar, minEndingHere);
        }
        
        // The answer is the maximum of maxSoFar and absolute value of minSoFar (i.e., -minSoFar)
        // Yahan -minSoFar se hum absolute value le rahe hain kyunki minSoFar negative ho sakta hai
        return Math.max(maxSoFar, -minSoFar);
    }
}
