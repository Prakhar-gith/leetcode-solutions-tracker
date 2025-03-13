// Algorithm:
// 1. Since the array is sorted in non-decreasing order, all negative numbers come first,
//    then zeros (if any), and finally the positive numbers.
// 2. To count negatives, we can use binary search (lowerBound) to find the first index where the number is >= 0.
//    - All elements before this index are negative.
// 3. To count positives, we use binary search to find the first index where the number is >= 1.
//    - All elements from this index to the end are positive.
// 4. Return the maximum between the count of negatives and positives.
//
// Pseudo Code:
// -------------
// function lowerBound(nums, target):
//     lo = 0, hi = nums.length - 1
//     while lo <= hi:
//         mid = (lo + hi) / 2
//         if nums[mid] < target:
//             lo = mid + 1
//         else:
//             hi = mid - 1
//     return lo
//
// posIndex = lowerBound(nums, 1)      // first index with a positive number
// negCount = lowerBound(nums, 0)        // first index with a non-negative number, so negatives = negCount
// countPos = nums.length - posIndex
// answer = max(countPos, negCount)
//
// Visualization Example:
// ------------------------
// For nums = [-2, -1, -1, 1, 2, 3]:
//   lowerBound(nums, 1) returns index 3 (value 1), so count of positives = 6 - 3 = 3.
//   lowerBound(nums, 0) returns index 3 (first number >= 0), so count of negatives = 3.
//   Maximum = max(3, 3) = 3.
//

class Solution {
    public int maximumCount(int[] nums) {
        int n = nums.length;
        
        // Find the first index where the number is >= 1 (i.e., first positive number)
        int posIndex = lowerBound(nums, 1);
        int countPos = n - posIndex; // All elements from posIndex to n-1 are positive
        
        // Find the first index where the number is >= 0
        // This index gives the count of negative numbers (since all before it are negative)
        int negCount = lowerBound(nums, 0);
        
        // Return the maximum of countPos and negCount
        return Math.max(countPos, negCount);
    }
    
    // Helper function: Binary search to find the first index in nums where value >= target.
    // This is the lower bound.
    private int lowerBound(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            // Agar mid index ka element target se chhota hai, to search right half
            if (nums[mid] < target) {
                lo = mid + 1;
            } else {
                // Otherwise, move hi to mid-1 to search for an earlier occurrence
                hi = mid - 1;
            }
        }
        return lo; // lo is the first index where nums[lo] >= target
    }
}
