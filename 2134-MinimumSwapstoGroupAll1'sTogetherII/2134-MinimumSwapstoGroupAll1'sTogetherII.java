// Algorithm:
// 1. Count total number of 1's in the array (totalOnes). This represents the group size we need.
// 2. Use a sliding window of size equal to totalOnes to traverse the circular array.
//    - We simulate circularity by iterating from 0 to 2*n - 1 and using modulo arithmetic.
// 3. In each window, count the number of 1's (curOnes).
//    - When the window exceeds the required size, subtract the element going out of the window.
// 4. Keep track of the maximum number of 1's (maxOnesInWindow) found in any window.
// 5. The minimum number of swaps required to group all 1's together is totalOnes - maxOnesInWindow.
//    - This works because in the optimal window, the number of 0's equals the swaps needed.

// Pseudo Code:
// -------------
// function minSwaps(nums):
//     n = length(nums)
//     totalOnes = count of 1's in nums
//     if totalOnes == 0: return 0
//     maxOnesInWindow = 0
//     curOnes = 0
//     for i from 0 to 2*n - 1:
//         if nums[i % n] == 1:
//             curOnes++
//         if i >= totalOnes and nums[(i - totalOnes) % n] == 1:
//             curOnes--
//         maxOnesInWindow = max(maxOnesInWindow, curOnes)
//     return totalOnes - maxOnesInWindow
//
// Visualization Example for nums = [0,1,0,1,1,0,0]:
//  - totalOnes = 3 (1's count)
//  - We slide a window of size 3 over the circular array:
//      Window positions and ones count:
//         [0,1,0] -> 1 one (swaps = 3-1 = 2)
//         [1,0,1] -> 2 ones (swaps = 3-2 = 1)
//         [0,1,1] -> 2 ones (swaps = 1)
//         [1,1,0] -> 2 ones (swaps = 1)
//         [1,0,0] -> 1 one (swaps = 2)
//         [0,0,0] -> 0 ones (swaps = 3)
//         [0,0,1] -> 1 one (swaps = 2)
//  - The optimal window has 2 ones, so minimum swaps = 3 - 2 = 1.

class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        int totalOnes = 0;
        
        // Count total number of 1's in the array
        for (int num : nums) {
            if (num == 1) {
                totalOnes++;
            }
        }
        
        // Agar array mein koi 1 nahi hai, toh grouping is already satisfied (or not applicable)
        if (totalOnes == 0) return 0;
        
        int maxOnesInWindow = 0; // maximum number of 1's found in any window of size 'totalOnes'
        int curOnes = 0; // current count of 1's in the sliding window
        
        // Loop through the array twice to simulate circular behavior
        // i goes from 0 to 2*n - 1
        for (int i = 0; i < 2 * n; i++) {
            // current element from circular array using modulo
            if (nums[i % n] == 1) {
                curOnes++; // agar current element 1 hai, increment count
            }
            
            // Once window size exceeds totalOnes, remove the element that is no longer in the window
            if (i >= totalOnes && nums[(i - totalOnes) % n] == 1) {
                curOnes--; // subtract the 1 going out of the window
            }
            
            // Update maximum count of 1's seen in any window of size totalOnes
            maxOnesInWindow = Math.max(maxOnesInWindow, curOnes);
        }
        
        // The minimum swaps required equals the number of zeros in the best window
        // That is, totalOnes - maxOnesInWindow
        return totalOnes - maxOnesInWindow;
    }
}
