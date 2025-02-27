// Algorithm:
// 1. Traverse through nums2 to determine the next greater element for every number.
//    - Use a stack to track numbers for which the next greater element hasn't been identified.
//    - For each number in nums2, while the stack is non-empty and the current number is greater than the element at the stack's top,
//      pop from the stack and record in a map that this popped element's next greater element is the current number.
//    - Push the current number onto the stack for future comparisons.
// 2. For each number in nums1 (which is a subset of nums2), look up its next greater element in the map. If it doesn't exist, assign -1.
// 3. Return the resulting array.
//
// Pseudo Code:
// -------------
// function nextGreaterElement(nums1, nums2):
//     nextGreaterMap = new empty map
//     stack = new empty stack
//     for each num in nums2:
//         while stack is not empty AND stack.top < num:
//             element = stack.pop()
//             nextGreaterMap[element] = num
//         stack.push(num)
//     result = new array of size length(nums1)
//     for i from 0 to length(nums1)-1:
//         if nums1[i] exists in nextGreaterMap:
//             result[i] = nextGreaterMap[nums1[i]]
//         else:
//             result[i] = -1
//     return result
//
// Visualization Example:
// ----------------------
// Consider nums2 = [1, 3, 4, 2]:
// - Start: stack = []
// - Process 1: stack empty, push 1 -> stack = [1]
// - Process 3: 3 > 1, so pop 1 and map 1 -> 3; then push 3 -> stack = [3]
// - Process 4: 4 > 3, so pop 3 and map 3 -> 4; then push 4 -> stack = [4]
// - Process 2: 2 is not > 4, so push 2 -> stack = [4, 2]
// Mapping now: {1:3, 3:4} (4 and 2 have no next greater element)
// For nums1, lookup each element in this mapping to determine the answer.
//

class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // Map to store the next greater element for each number in nums2
        Map<Integer, Integer> nextGreaterMap = new HashMap<>();
        // Stack to keep track of numbers for which next greater element hasn't been found yet
        Stack<Integer> stack = new Stack<>();
        
        // Traverse through each element in nums2
        for (int num : nums2) {
            // Check if current number is greater than the top of the stack
            // If yes, then for all such elements, the current number is their next greater element
            while (!stack.isEmpty() && stack.peek() < num) {
                // Pop the element and record its next greater element in the map
                nextGreaterMap.put(stack.pop(), num);
            }
            // Push current number onto the stack for future comparison
            stack.push(num);
        }
        
        // Prepare the result array for nums1
        int[] result = new int[nums1.length];
        // For each number in nums1, fetch its next greater element from the map
        for (int i = 0; i < nums1.length; i++) {
            // If no next greater element is found, default to -1
            result[i] = nextGreaterMap.getOrDefault(nums1[i], -1);
        }
        
        // Return the final array of next greater elements for nums1
        return result;
    }
}
