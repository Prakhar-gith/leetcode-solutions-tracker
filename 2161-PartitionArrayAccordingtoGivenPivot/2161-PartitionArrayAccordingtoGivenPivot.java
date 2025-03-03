// Algorithm:
// 1. Create three lists: 
//    - "less" to store elements smaller than pivot.
//    - "equal" to store elements equal to pivot.
//    - "greater" to store elements greater than pivot.
// 2. Traverse through the input array and add each element to its respective list based on its comparison with pivot.
// 3. Create a result array. First, add all elements from the "less" list (preserving their order).
// 4. Then, add all elements from the "equal" list.
// 5. Finally, add all elements from the "greater" list.
// 6. Return the result array, which now meets the required conditions.

// Pseudo Code:
// -------------
// function pivotArray(nums, pivot):
//     less = new empty list
//     equal = new empty list
//     greater = new empty list
//     
//     for each num in nums:
//         if num < pivot:
//             add num to less
//         else if num == pivot:
//             add num to equal
//         else:
//             add num to greater
//     
//     result = new array of length(nums)
//     index = 0
//     for each num in less:
//         result[index] = num; index++
//     for each num in equal:
//         result[index] = num; index++
//     for each num in greater:
//         result[index] = num; index++
//     
//     return result
//
// Visualization Example:
// ------------------------
// For nums = [9,12,5,10,14,3,10] and pivot = 10:
//   less   = [9, 5, 3]        (elements less than 10, order maintained)
//   equal  = [10, 10]         (elements equal to 10)
//   greater= [12, 14]         (elements greater than 10, order maintained)
// Final array: [9, 5, 3, 10, 10, 12, 14]

class Solution {
    public int[] pivotArray(int[] nums, int pivot) {
        // Lists to store numbers based on their comparison with pivot
        List<Integer> less = new ArrayList<>();    // Elements < pivot
        List<Integer> equal = new ArrayList<>();     // Elements == pivot
        List<Integer> greater = new ArrayList<>();   // Elements > pivot
        
        // Iterate over nums to distribute elements into less, equal, and greater lists
        for (int num : nums) {
            // Agar number pivot se chota hai, add it to less list
            if (num < pivot) {
                less.add(num);
            }
            // Agar number pivot ke barabar hai, add it to equal list
            else if (num == pivot) {
                equal.add(num);
            }
            // Otherwise, number is greater than pivot, add it to greater list
            else {
                greater.add(num);
            }
        }
        
        // Create the result array to store the rearranged elements
        int[] result = new int[nums.length];
        int index = 0; // Pointer to fill in result array
        
        // First, add all numbers from the less list preserving order
        for (int num : less) {
            result[index++] = num;
        }
        // Next, add all numbers equal to pivot
        for (int num : equal) {
            result[index++] = num;
        }
        // Finally, add all numbers greater than pivot preserving order
        for (int num : greater) {
            result[index++] = num;
        }
        
        // Return the final partitioned array that satisfies the conditions
        return result;
    }
}
