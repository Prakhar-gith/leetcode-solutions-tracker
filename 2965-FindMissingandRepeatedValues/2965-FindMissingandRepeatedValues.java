// Algorithm:
// 1. Calculate n^2 which is the maximum number in the grid (grid has values in [1, n^2]).
// 2. Create a frequency array of size (n^2 + 1) to count occurrences of each number.
// 3. Traverse through each element in the grid and update the frequency array.
// 4. Iterate through numbers from 1 to n^2:
//    - If a number appears twice, that's our repeated number (a).
//    - If a number does not appear, that's our missing number (b).
// 5. Return an array where ans[0] = repeated number and ans[1] = missing number.

/*
   Pseudo Code:
   -------------
   function findErrorNums(grid):
       n = grid.length
       N = n * n
       freq = new int[N + 1] // index 1 to N
       for i from 0 to n-1:
           for j from 0 to n-1:
               freq[ grid[i][j] ]++
       
       repeated = -1, missing = -1
       for i from 1 to N:
           if freq[i] == 2:
               repeated = i
           if freq[i] == 0:
               missing = i
       return [repeated, missing]
       
   Visualization Example (grid = [[1,3],[2,2]]):
   -------------------------------------------------
   n = 2, so N = 4. Expected numbers are 1, 2, 3, 4.
   Frequency array after processing grid:
       freq[1] = 1, freq[2] = 2, freq[3] = 1, freq[4] = 0.
   Thus, repeated = 2 and missing = 4.
*/

class Solution {
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        int n = grid.length;              // Grid size n x n
        int totalNumbers = n * n;         // Maximum number in grid is n^2
        
        // Frequency array to count occurrence of numbers from 1 to n^2
        int[] freq = new int[totalNumbers + 1];  // Using 1-indexed array
        
        // Traverse the grid to update frequency for each number
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                freq[grid[i][j]]++; // Increment count for the encountered number
            }
        }
        
        int repeated = -1;  // Will store the number that appears twice (a)
        int missing = -1;   // Will store the number that is missing (b)
        
        // Check each number in the range [1, n^2]
        for (int num = 1; num <= totalNumbers; num++) {
            // Agar frequency 2 hai, then that number is repeated
            if (freq[num] == 2) {
                repeated = num;
            }
            // Agar frequency 0 hai, then that number is missing
            if (freq[num] == 0) {
                missing = num;
            }
        }
        
        // Return the result as an array: ans[0] = repeated, ans[1] = missing
        return new int[]{repeated, missing};
    }
}
