// Algorithm:
// 1. Traverse the array 'nums' once.
// 2. For each element, calculate its corresponding index as abs(nums[i]) - 1.
// 3. Check the value at nums[index]:
//    - If it's negative, it means the number (index+1) has been seen before, so add it to the result list.
//    - If it's positive, mark it as negative to indicate that the number (index+1) is now encountered.
// 4. Finally, return the list containing all numbers that appear twice.

// Pseudo Code:
// -------------
// function findDuplicates(nums):
//     result = empty list
//     for i from 0 to length(nums)-1:
//         index = abs(nums[i]) - 1
//         if nums[index] < 0 then
//             add (index + 1) to result
//         else
//             nums[index] = -nums[index]
//     return result

// Visualization Example:
// ------------------------
// Let nums = [4,3,2,7,8,2,3,1]
// Step by step modification:
// i = 0: value = 4, index = 3 => mark nums[3] negative => nums becomes [4,3,2,-7,8,2,3,1]
// i = 1: value = 3, index = 2 => mark nums[2] negative => nums becomes [4,3,-2,-7,8,2,3,1]
// i = 2: value = -2 (abs = 2), index = 1 => mark nums[1] negative => nums becomes [4,-3,-2,-7,8,2,3,1]
// i = 3: value = -7 (abs = 7), index = 6 => mark nums[6] negative => nums becomes [4,-3,-2,-7,8,2,-3,1]
// i = 4: value = 8, index = 7 => mark nums[7] negative => nums becomes [4,-3,-2,-7,8,2,-3,-1]
// i = 5: value = 2, index = 1 => nums[1] is already negative, so duplicate found -> add 2 to result
// i = 6: value = -3 (abs = 3), index = 2 => nums[2] is already negative, so duplicate found -> add 3 to result
// i = 7: value = -1 (abs = 1), index = 0 => mark nums[0] negative => nums becomes [-4,-3,-2,-7,8,2,-3,-1]
// Final duplicates: [2, 3]

class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        // 'result' will store the numbers that appear twice in the array
        List<Integer> result = new ArrayList<>();
        
        // Iterate through the array to mark visited numbers
        for (int i = 0; i < nums.length; i++) {
            // Calculate the index corresponding to the absolute value of the current element
            int index = Math.abs(nums[i]) - 1;
            
            // If the element at 'index' is already negative, it means we've encountered this number before
            if (nums[index] < 0) {
                // Add the duplicate number (index + 1) to the result list
                result.add(index + 1);
            } else {
                // Mark the number as visited by making it negative
                nums[index] = -nums[index];
            }
        }
        
        // Return the list of duplicates found
        return result;
    }
}
