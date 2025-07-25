// Last updated: 7/25/2025, 1:43:45 PM
class Solution {
    public int maxSum(int[] nums) {
        
        // Algorithm:
        // The problem statement, with its rules about deletions and subarrays, can be simplified.
        // The ability to delete any element means we can pick any subsequence from the original
        // array and form a new contiguous array with it. The core task is to find a
        // subsequence of `nums` that contains unique elements and has the maximum possible sum.
        //
        // To maximize the sum under the "unique elements" constraint, the greedy strategy is:
        // 1. Identify all unique numbers in the input array. A HashSet is perfect for this.
        // 2. To get the maximum sum, we should add up all the unique numbers that are positive.
        //    Including any negative number would only decrease our sum.
        // 3. Edge Case: If there are no positive unique numbers (i.e., all unique numbers are
        //    zero or negative), the sum of positives would be 0. However, the problem states
        //    we cannot make the array empty, so we must select at least one number. In this
        //    scenario, the best we can do is to select a subsequence with a single element: the
        //    largest unique number available (which would be 0 or the least negative number).

        // Pseudo Code:
        // 1. unique_numbers = new HashSet()
        // 2. for num in nums:
        // 3.   add num to unique_numbers
        //
        // 4. max_sum = 0
        // 5. has_positive = false
        // 6. for unique_num in unique_numbers:
        // 7.   if unique_num > 0:
        // 8.     max_sum += unique_num
        // 9.     has_positive = true
        //
        // 10. if has_positive is true:
        // 11.   return max_sum
        // 12. else: // No positive numbers, find the largest available number.
        // 13.   return Collections.max(unique_numbers)

        // Step 1: Use a HashSet to get all unique numbers from the input array.
        // Duplicate numbers automatically handle ho jaate hain.
        Set<Integer> uniqueNums = new HashSet<>();
        for (int num : nums) {
            uniqueNums.add(num);
        }

        long maxSum = 0;
        boolean foundPositive = false;

        // Step 2: Iterate through the unique numbers and sum up all the positive ones.
        // Saare unique positive numbers ko add karke maximum sum nikalenge.
        for (int num : uniqueNums) {
            if (num > 0) {
                maxSum += num;
                foundPositive = true;
            }
        }

        // Step 3: Determine the final result based on whether we found any positive numbers.
        if (foundPositive) {
            // If we found at least one positive number, their sum is the answer.
            return (int) maxSum;
        } else {
            // If there were no positive unique numbers, the array must not be empty.
            // So, we must choose at least one element. To maximize the sum, we pick the
            // largest single element from the unique set (this will be 0 or the least negative number).
            // Agar koi positive number nahi mila, toh jo sabse bada number (0 ya negative) hai, wahi hamara answer hoga.
            return Collections.max(uniqueNums);
        }
        
        /*
         * Visualization of the logic:
         *
         * Example 1: nums = [1, 2, -1, -2, 1, 0, -1]
         * 1. Find unique numbers: `uniqueNums` becomes {1, 2, -1, -2, 0}.
         * 2. Sum positive unique numbers:
         * - num = 1 -> maxSum = 1, foundPositive = true
         * - num = 2 -> maxSum = 1 + 2 = 3
         * - Others are <= 0, so they are skipped.
         * 3. `foundPositive` is true, so we return `maxSum` which is 3.
         *
         * Example 2: nums = [-5, -1, -10, 0]
         * 1. Find unique numbers: `uniqueNums` becomes {-5, -1, -10, 0}.
         * 2. Sum positive unique numbers: No numbers are > 0. `maxSum` remains 0, `foundPositive` remains false.
         * 3. `foundPositive` is false. We enter the `else` block.
         * 4. Find the maximum element in the set {-5, -1, -10, 0}.
         * 5. `Collections.max()` returns 0. The answer is 0.
        */
    }
}