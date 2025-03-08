// Algorithm:
// 1. Use a sliding window approach of size k over the string 'blocks'.
// 2. Count the number of white blocks ('W') in the first window of size k.
// 3. Slide the window from left to right:
//    - For each new window, subtract the effect of the block that goes out (if it's 'W', reduce count)
//      and add the effect of the new block coming in (if it's 'W', increase count).
// 4. Track the minimum number of white blocks encountered in any window,
//    because that represents the minimum operations needed (each 'W' must be repainted to 'B').
// 5. Return the minimum count as the answer.
//
// Pseudo Code:
// -------------
// function minimumRecolors(blocks, k):
//     n = length(blocks)
//     whiteCount = count of 'W' in blocks[0...k-1]
//     minOperations = whiteCount
//     for i from k to n-1:
//         if blocks[i-k] is 'W':
//             whiteCount--
//         if blocks[i] is 'W':
//             whiteCount++
//         minOperations = min(minOperations, whiteCount)
//     return minOperations
//
// Visualization Example:
// ------------------------
// Let blocks = "WBBWWBBWBW", k = 7
// Initial window (indices 0 to 6): "WBBWWBB"
//   Count of 'W': index 0,3,4 -> 3 white blocks => cost = 3
// Slide window to indices 1 to 7: "BBWWBBW"
//   Update: remove 'W' at index 0, add block at index 7 ('W') -> cost remains 3
// Slide window to indices 2 to 8: "BWWBBWB"
//   Similarly, count remains 3
// Slide window to indices 3 to 9: "WWBBWBW"
//   Now count becomes 4 white blocks => cost = 4
// Minimum cost over all windows = 3
// Hence, the answer is 3.

class Solution {
    public int minimumRecolors(String blocks, int k) {
        int n = blocks.length(); // Length of the input string
        
        // Count the number of 'W' (white blocks) in the first window of size k
        int whiteCount = 0;
        for (int i = 0; i < k; i++) {
            if (blocks.charAt(i) == 'W') {
                whiteCount++;
            }
        }
        // Set the initial minimum operations required as the count from the first window
        int minOperations = whiteCount;
        
        // Sliding window: from index k to the end of the string
        // Har window ke liye, update whiteCount based on the block that goes out and the new block that comes in
        for (int i = k; i < n; i++) {
            // Agar window se bahar ho raha block white hai, toh whiteCount ghatado
            if (blocks.charAt(i - k) == 'W') {
                whiteCount--;
            }
            // Agar naya block jo window mein aa raha hai white hai, toh whiteCount badhao
            if (blocks.charAt(i) == 'W') {
                whiteCount++;
            }
            // Update the minimum operations required if current window has fewer white blocks
            minOperations = Math.min(minOperations, whiteCount);
        }
        
        // Return the minimum number of recolors needed to get k consecutive black blocks
        return minOperations;
    }
}
