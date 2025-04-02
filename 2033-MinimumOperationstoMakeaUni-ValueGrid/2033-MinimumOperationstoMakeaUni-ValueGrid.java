// Last updated: 4/2/2025, 3:05:28 PM
// Algorithm:
// 1. Check if it is possible to make the grid uni-value: 
//    All grid elements must have the same remainder when divided by x. 
//    Agar kisi element ka remainder different hai, then return -1.
// 2. Flatten the grid into a one-dimensional list to work easily with all values.
// 3. Sort the flattened list.
// 4. Choose the median of the sorted list as the target value to minimize the total operations.
//    (Median minimizes the sum of absolute deviations)
// 5. For each element in the list, add up the operations required, which is:
//       abs(element - median) / x 
// 6. Return the total number of operations.
//
// Pseudo Code:
// -------------
// function minOperations(grid, x):
//     flatList = []
//     for each row in grid:
//         for each element in row:
//             if element mod x != firstElement mod x:
//                 return -1
//             add element to flatList
//     sort flatList
//     median = flatList[length(flatList) / 2]
//     ops = 0
//     for each element in flatList:
//         ops += abs(element - median) / x
//     return ops
//
// Visualization Example:
// ----------------------
// Let grid = [[2,4],[6,8]] and x = 2.
// Flattened list = [2, 4, 6, 8]. 
// Check: All numbers mod 2 = 0, so possible.
// Sorted list = [2,4,6,8]. Median can be 4 or 6 (choosing 4 here).
// Operations: (|2-4| + |4-4| + |6-4| + |8-4|) / 2 = (2+0+2+4)/2 = 8/2 = 4.
// Thus, answer is 4.

class Solution {
    public int minOperations(int[][] grid, int x) {
        int m = grid.length, n = grid[0].length;
        int total = m * n;
        int mod = grid[0][0] % x; // Remainder of the first element
        
        // Create a list to store all elements of grid
        int[] arr = new int[total];
        int index = 0;
        
        // Flatten grid and check possibility condition (all elements should have same mod x)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Agar remainder different hai, then it's impossible
                if (grid[i][j] % x != mod) {
                    return -1;
                }
                arr[index++] = grid[i][j];
            }
        }
        
        // Sort the flattened array
        Arrays.sort(arr);
        
        // Choose the median as target value. For even count, one of the middle values minimizes cost.
        int median = arr[total / 2];
        long operations = 0;
        
        // Calculate total operations required by summing the difference divided by x for each element.
        for (int num : arr) {
            operations += Math.abs(num - median) / x;
        }
        
        return (int) operations;
    }
}
