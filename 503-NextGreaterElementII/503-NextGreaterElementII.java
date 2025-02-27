// Algorithm:
// 1. Initialize a result array of size n and fill it with -1 since -1 is the default if no greater element is found.
// 2. Use a stack to store indices of elements that haven't yet found their next greater element.
// 3. Loop through the array twice (simulate circular behavior) using i from 0 to 2*n - 1:
//    a. Compute the current element using nums[i % n] to wrap around.
//    b. While the stack is not empty and the current element is greater than the element at index stack.peek(), pop the index and update result.
//    c. For the first pass (i < n), push the current index into the stack.
// 4. After the loop, indices still in the stack have no next greater element (remain -1).
// 5. Return the result array.
//
// Pseudo Code:
// -------------
// function nextGreaterElements(nums):
//     n = length(nums)
//     result = array of size n filled with -1
//     stack = empty stack
//     for i from 0 to 2*n - 1:
//         current = nums[i % n]
//         while stack is not empty and nums[stack.top()] < current:
//             idx = stack.pop()
//             result[idx] = current
//         if i < n:
//             stack.push(i)
//     return result
//
// Visualization Example for nums = [1,2,1]:
//    - Start with: result = [-1, -1, -1], stack = []
//    - i = 0, current = 1: stack is empty -> push index 0; stack = [0]
//    - i = 1, current = 2: nums[stack.peek()] = 1 < 2 -> pop index 0, set result[0] = 2; stack = []; push index 1; stack = [1]
//    - i = 2, current = 1: nums[stack.peek()] = 2 > 1 -> push index 2; stack = [1,2]
//    - i = 3, current = 1: (circular) -> no change; (don't push because i >= n)
//    - i = 4, current = 2: nums[stack.peek()] = 1 < 2 -> pop index 2, set result[2] = 2; stack = [1]
//    - i = 5, current = 1: (circular) -> no change; finish loop.
//    - Final result = [2, -1, 2]
//

class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length; // Total elements in the input array
        int[] result = new int[n];
        // Initialize result with -1 as default for each index
        Arrays.fill(result, -1);
        
        // Stack to hold indices of elements for which we need to find the next greater element
        Stack<Integer> stack = new Stack<>();
        
        // Loop through the array twice to simulate the circular behavior
        for (int i = 0; i < 2 * n; i++) {
            // current element using modulo operation to wrap around the array
            int current = nums[i % n];
            
            // While there are indices in the stack and the current element is greater than 
            // the element at the index on the top of the stack
            while (!stack.isEmpty() && nums[stack.peek()] < current) {
                int idx = stack.pop(); // Get the index whose next greater element is current
                result[idx] = current; // Update the result for that index
            }
            
            // Push the index only in the first pass to avoid duplicate indices
            if (i < n) {
                stack.push(i);
            }
        }
        
        // The indices remaining in the stack don't have any next greater element, so they remain -1
        return result;
    }
}
