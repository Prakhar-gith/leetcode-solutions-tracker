// Algorithm:
// 1. Iterate over the array from index 0 to n-2 (since we compare nums[i] with nums[i+1]).
// 2. For each index i:
//    - If nums[i] is equal to nums[i+1], then update nums[i] = 2 * nums[i] and set nums[i+1] = 0.
//      (This operation is applied sequentially as given in the problem.)
// 3. After applying all operations, shift all non-zero elements to the beginning of the array while preserving their order.
// 4. Fill the remaining positions in the array with 0's.
// 5. Return the resulting array.
//
// Pseudo Code:
// -------------
// function applyOperations(nums):
//     n = length(nums)
//     for i from 0 to n-2:
//         if nums[i] == nums[i+1]:
//             nums[i] = nums[i] * 2
//             nums[i+1] = 0
//     newIndex = 0
//     result = new array of length n
//     for each num in nums:
//         if num != 0:
//             result[newIndex] = num
//             newIndex++
//     return result
//
// Visualization Example:
// ------------------------
// Input: nums = [1,2,2,1,1,0]
//
// Step 1: Apply Operations Sequentially:
//   - i = 0: Compare 1 and 2 -> not equal, array remains [1,2,2,1,1,0].
//   - i = 1: Compare 2 and 2 -> equal, update: nums[1]=4, nums[2]=0, array becomes [1,4,0,1,1,0].
//   - i = 2: Compare 0 and 1 -> not equal, array remains [1,4,0,1,1,0].
//   - i = 3: Compare 1 and 1 -> equal, update: nums[3]=2, nums[4]=0, array becomes [1,4,0,2,0,0].
//   - i = 4: Compare 0 and 0 -> equal, update: nums[4]=0, nums[5]=0, array remains [1,4,0,2,0,0].
//
// Step 2: Shift All Zeros to the End:
//   - Collect non-zero values: [1,4,2]
//   - Append zeros to maintain the array size: [1,4,2,0,0,0]
//
// Final Output: [1,4,2,0,0,0]

class Solution {
    public int[] applyOperations(int[] nums) {
        int n = nums.length; // Array length
        
        // Step 1: Apply operations on adjacent elements
        // Loop through indices from 0 to n - 2, because we compare with next element
        for (int i = 0; i < n - 1; i++) {
            // Agar current element aur next element same hain, toh operation perform karo
            if (nums[i] == nums[i + 1]) {
                nums[i] = nums[i] * 2; // Double the current element (nums[i] = 2 * nums[i])
                nums[i + 1] = 0;       // Set the next element to 0
            }
        }
        
        // Step 2: Shift all non-zero values to the front and fill remaining indices with 0
        // Create a new array for the result with the same length as input
        int[] result = new int[n];
        int newIndex = 0; // Index pointer for placing non-zero elements
        
        // Traverse through the modified nums array
        for (int num : nums) {
            // Agar number 0 nahi hai, then place it in the result array
            if (num != 0) {
                result[newIndex] = num;
                newIndex++; // Increment the position for next non-zero number
            }
        }
        
        // Note: Remaining positions in result array are already 0 by default
        
        // Return the resulting array after all operations and shifting zeros
        return result;
    }
}
