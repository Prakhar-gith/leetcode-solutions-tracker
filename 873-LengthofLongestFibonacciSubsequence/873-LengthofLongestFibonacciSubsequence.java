// Algorithm:
// 1. Create a HashMap 'index' to map each value in arr to its index for fast look-up.
// 2. Initialize a 2D DP array 'dp' where dp[i][j] represents the length of the Fibonacci-like subsequence 
//    ending with arr[i] and arr[j]. Start each pair with a minimum length of 2.
// 3. For every pair (i, j) with i < j, calculate the potential previous number 'prev' = arr[j] - arr[i].
// 4. If 'prev' exists in the map and its index k is less than i, update dp[i][j] = dp[k][i] + 1.
// 5. Maintain a variable 'ans' to record the maximum length of any Fibonacci-like subsequence found.
// 6. Return 'ans' if it is at least 3, otherwise return 0 (since a valid sequence must have length >= 3).

/*
   Pseudo Code:
   -------------
   function lenLongestFibSubseq(arr):
       n = length(arr)
       index = map each arr[i] -> i
       dp = 2D array of size n x n, initialize all to 2
       ans = 0
       for j from 0 to n-1:
           for i from 0 to j-1:
               prev = arr[j] - arr[i]
               if index contains prev and index[prev] < i:
                   k = index[prev]
                   dp[i][j] = dp[k][i] + 1
                   ans = max(ans, dp[i][j])
       if ans >= 3:
           return ans
       else:
           return 0
*/

/*
   Visualization:
   --------------
   Consider arr = [1, 3, 4, 7, 11, 12, 14, 18]:
   - Mapping: {1:0, 3:1, 4:2, 7:3, 11:4, 12:5, 14:6, 18:7}
   - For pair (3,4): prev = 4 - 3 = 1, index[1] = 0, so a sequence [1, 3, 4] of length 3 is formed.
   - The process continues for each valid pair to extend Fibonacci-like sequences.
*/

class Solution {
    public int lenLongestFibSubseq(int[] arr) {
        int n = arr.length; // Total number of elements in the array
        
        // Create a map to store each element and its index for O(1) look-up
        Map<Integer, Integer> index = new HashMap<>();
        for (int i = 0; i < n; i++) {
            index.put(arr[i], i);
        }
        
        // DP table where dp[i][j] represents the length of the Fibonacci-like sequence ending with arr[i] and arr[j]
        int[][] dp = new int[n][n];
        int ans = 0; // Variable to keep track of the maximum sequence length found
        
        // Iterate over all pairs (i, j) with i < j
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < j; i++) {
                // Minimum sequence length is 2 for any pair (arr[i], arr[j])
                dp[i][j] = 2;
                
                // Calculate the potential previous element in the Fibonacci-like sequence
                int prev = arr[j] - arr[i];
                
                // Check if 'prev' exists in the array and its index is less than i
                if (index.containsKey(prev) && index.get(prev) < i) {
                    int k = index.get(prev); // Retrieve index k of the previous element
                    // Update dp[i][j] by extending the sequence ending at (k, i)
                    dp[i][j] = dp[k][i] + 1;
                    // Update the answer with the maximum sequence length encountered
                    ans = Math.max(ans, dp[i][j]);
                }
            }
        }
        
        // If a valid Fibonacci-like subsequence of length >= 3 is found, return its length; otherwise, return 0
        return ans >= 3 ? ans : 0;
    }
}
