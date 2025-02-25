// Algorithm:
// 1. Traverse the array and try to place each number in its "correct" position.
//    - For an element nums[i] that lies in the range [1, n], its correct position is at index nums[i] - 1.
//    - If nums[i] is not at its correct position, swap it with the element at index nums[i] - 1.
//    - Continue swapping until the current element is either out of range, a duplicate, or already in the correct position.
// 2. After rearranging the array, traverse it again.
//    - The first index j where nums[j] != j + 1 indicates that j + 1 is missing.
// 3. If all elements are in their correct positions, return n + 1.

// Pseudo Code:
// -------------
// function firstMissingPositive(nums):
//     n = length(nums)
//     i = 0
//     while i < n:
//         if nums[i] == i+1:
//             i++
//             continue
//         if nums[i] <= 0 or nums[i] > n:
//             i++
//             continue
//         idx1 = i
//         idx2 = nums[i] - 1
//         if nums[idx1] == nums[idx2]:
//             i++
//             continue
//         swap(nums[idx1], nums[idx2])
//     for j from 0 to n-1:
//         if nums[j] != j+1:
//             return j+1
//     return n+1
//
// Visualization:
// --------------
// Consider nums = [3, 4, -1, 1]
// After rearrangement using swaps:
//   Step 1: Swap 3 into its correct position -> [ -1, 4, 3, 1 ]
//   Step 2: For index 0, -1 is invalid (not in [1, n]), so move on.
//   Step 3: For index 1, 4 is in range. Its correct index is 3. Swap -> [ -1, 1, 3, 4 ]
//   Step 4: For index 1 now, 1 should be at index 0. Swap -> [ 1, -1, 3, 4 ]
//   Step 5: Final pass: Index 0 has 1, index 1 has -1 (should be 2), so answer is 2.
//

class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length; // Array length, important for range [1, n]
        int i = 0;
        
        // Loop through the array to place each number in its correct position
        while (i < n) {
            // Check if current element is at its correct position (i.e., nums[i] == i+1)
            if (nums[i] == i + 1) {
                i++; // Agar sahi jagah par hai, next index par chale jao
                continue;
            }
            // Agar number range mein nahi hai (<= 0 ya > n), toh usse ignore karo
            if (nums[i] <= 0 || nums[i] > n) {
                i++; // Yeh element valid range mein nahi hai, so skip
                continue;
            }
            // Calculate the correct index for the current element
            int idx1 = i;
            int idx2 = nums[i] - 1;
            
            // Agar duplicate ya already in place, avoid infinite loop by skipping
            if (nums[idx1] == nums[idx2]) {
                i++; // Duplicate detected, move on
                continue;
            }
            
            // Swap the current element with the element at its correct position
            int temp = nums[idx1];
            nums[idx1] = nums[idx2];
            nums[idx2] = temp;
            // Note: Do not increment i here, because the new number at index i may still be misplaced
        }
        
        // After rearrangement, find the first index where the number is not (index + 1)
        for (int j = 0; j < n; j++) {
            if (nums[j] != j + 1) {
                return j + 1; // This is the smallest missing positive number
            }
        }
        
        // If every number from 1 to n is in place, the missing number is n+1
        return n + 1;
    }
}
