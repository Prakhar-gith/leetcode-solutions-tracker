// Last updated: 4/6/2025, 4:26:53 PM
// Algorithm:
// 1. Sort the input array. Sorting ensures that if nums[i] is divisible by nums[j] for j < i, then 
//    the divisibility property can extend to a larger subset.
// 2. Use dynamic programming to compute the largest divisible subset ending at each element.
//    - Let dp[i] be the size of the largest divisible subset that ends with nums[i].
//    - Let prev[i] store the index of the previous element in the subset ending at nums[i].
// 3. For each element nums[i], check all previous elements nums[j] (with j < i):
//    - If nums[i] is divisible by nums[j] and dp[j] + 1 > dp[i], update dp[i] and prev[i].
// 4. Identify the index with the maximum dp value. This index is the end of the largest subset.
// 5. Reconstruct the subset using the prev array, and reverse it to get the correct order.
// 6. Return the resulting subset.
//
// Pseudo Code:
// -------------
// function largestDivisibleSubset(nums):
//     sort(nums)
//     n = length(nums)
//     dp = array of size n initialized to 1
//     prev = array of size n initialized to -1
//     maxIndex = 0
//     for i from 0 to n-1:
//         for j from 0 to i-1:
//             if nums[i] % nums[j] == 0 and dp[j] + 1 > dp[i]:
//                 dp[i] = dp[j] + 1
//                 prev[i] = j
//         if dp[i] > dp[maxIndex]:
//             maxIndex = i
//     result = empty list
//     while maxIndex != -1:
//         add nums[maxIndex] to result
//         maxIndex = prev[maxIndex]
//     reverse result
//     return result
//
// Visualization Example:
// ----------------------
// Given nums = [1, 2, 4, 8]:
//   - Sorted: [1, 2, 4, 8]
//   - dp array will be computed as follows:
//         dp[0] = 1, (subset: [1])
//         dp[1] = dp[0] + 1 = 2, (subset: [1,2])
//         dp[2] = dp[1] + 1 = 3, (subset: [1,2,4])
//         dp[3] = dp[2] + 1 = 4, (subset: [1,2,4,8])
//   - Final result: [1,2,4,8]
//
// Code with detailed comments (hinglish):
class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        // Agar array empty hai, return empty list
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        
        // Step 1: Sort the input array so that divisibility checks are easier
        Arrays.sort(nums);
        int n = nums.length;
        
        // dp[i] will store the size of the largest divisible subset ending at nums[i]
        int[] dp = new int[n];
        // prev[i] will store the previous index in the subset chain for nums[i]
        int[] prev = new int[n];
        // Initially, har element ka subset size 1 hai and koi previous index nahi hai, so initialize with -1.
        Arrays.fill(dp, 1);
        Arrays.fill(prev, -1);
        
        int maxIndex = 0; // Index of the last element in the largest subset
        
        // Step 2: Build dp and prev arrays using nested loops
        for (int i = 0; i < n; i++) {
            // Check for every previous element j (0 to i-1)
            for (int j = 0; j < i; j++) {
                // Agar nums[i] is divisible by nums[j] and a better chain can be formed
                if (nums[i] % nums[j] == 0 && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1; // Update the size of the subset ending at i
                    prev[i] = j;       // Set j as the previous index for i
                }
            }
            // Update maxIndex if we found a larger subset ending at i
            if (dp[i] > dp[maxIndex]) {
                maxIndex = i;
            }
        }
        
        // Step 3: Reconstruct the largest subset using the prev array
        List<Integer> result = new ArrayList<>();
        // Start from maxIndex and go backwards using prev array
        while (maxIndex != -1) {
            result.add(nums[maxIndex]);
            maxIndex = prev[maxIndex];
        }
        
        // Reverse the result list to get the correct order (smallest to largest)
        Collections.reverse(result);
        return result;
    }
}
