// Algorithm:
// 1. Recognize that after n minutes, the set of colored cells forms a diamond shape centered at the initial cell.
// 2. The cells that are colored are exactly those with Manhattan distance <= (n - 1) from the center.
// 3. The number of cells with Manhattan distance d (for d >= 1) is 4*d. For d = 0, there is 1 cell.
// 4. Thus, the total number of colored cells = 1 + 4 * (1 + 2 + ... + (n - 1)).
// 5. The sum (1 + 2 + ... + (n - 1)) can be computed as (n - 1)*n/2.
// 6. Therefore, total colored cells = 1 + 4 * ((n - 1)*n/2) = 1 + 2*n*(n - 1).
//
// Pseudo Code:
// -------------
// function coloredCells(n):
//     // Calculate diamond area where radius = n - 1
//     total = 1 + 2 * n * (n - 1)
//     return total
//
// Visualization Example:
// ------------------------
// For n = 3 minutes:
//   - Manhattan distance 0: cell (0,0) --> count = 1
//   - Manhattan distance 1: cells (0,1), (1,0), (0,-1), (-1,0) --> count = 4
//   - Manhattan distance 2: cells (0,2), (1,1), (2,0), (1,-1), (0,-2), (-1,-1), (-2,0), (-1,1) --> count = 8
// Total = 1 + 4 + 8 = 13
//
// Code Explanation:
// -----------------
// - The formula used is: result = 1 + 2 * n * (n - 1)
// - We use 'long' as the result type because n can be as large as 10^5,
//   which makes the result too large for an int.
// - We also store the input array (or in this case, the input 'n') in a variable if needed,
//   but here the main requirement is to compute and return the formula result.
// - "xantreloqu" variable is not required here as we have a single integer input.
 
class Solution {
    public long coloredCells(int n) {
        // n is the number of minutes
        // The total number of colored cells is given by: 1 + 2 * n * (n - 1)
        // Explanation: 1 (center cell) + 2*n*(n-1) covers the diamond area expansion.
        return 1L + 2L * n * (n - 1);
    }
}
