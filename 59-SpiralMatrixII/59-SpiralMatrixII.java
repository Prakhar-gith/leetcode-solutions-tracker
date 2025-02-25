// Algorithm:
// 1. Initialize an n x n matrix filled with 0's.
// 2. Define boundaries: top, bottom, left, and right which represent the current edges of the spiral.
// 3. Use a variable 'num' starting from 1 to fill numbers up to n*n.
// 4. Loop while top <= bottom and left <= right:
//    a. Traverse from left to right on the top row, filling values, then increment 'top'.
//    b. Traverse from top to bottom on the right column, filling values, then decrement 'right'.
//    c. If top <= bottom, traverse from right to left on the bottom row, filling values, then decrement 'bottom'.
//    d. If left <= right, traverse from bottom to top on the left column, filling values, then increment 'left'.
// 5. Return the filled matrix.

/*
   Pseudo Code:
   -------------
   function generateMatrix(n):
       matrix = new int[n][n]
       top = 0, bottom = n - 1, left = 0, right = n - 1
       num = 1
       while top <= bottom AND left <= right:
           // Traverse top row from left to right
           for i from left to right:
               matrix[top][i] = num
               num++
           top++

           // Traverse right column from top to bottom
           for i from top to bottom:
               matrix[i][right] = num
               num++
           right--

           // Traverse bottom row from right to left, if still valid row
           if top <= bottom:
               for i from right to left:
                   matrix[bottom][i] = num
                   num++
               bottom--

           // Traverse left column from bottom to top, if still valid column
           if left <= right:
               for i from bottom to top:
                   matrix[i][left] = num
                   num++
               left++
       return matrix
*/

/*
   Visualization Example for n = 3:

   Initial boundaries:
       top = 0, bottom = 2, left = 0, right = 2
   Matrix (indices):
       [ (0,0) (0,1) (0,2) ]
       [ (1,0) (1,1) (1,2) ]
       [ (2,0) (2,1) (2,2) ]
   
   Spiral filling steps:
   1. Fill top row: (0,0) -> (0,1) -> (0,2)
      Matrix becomes: [1, 2, 3]
   2. Fill right column: (1,2) -> (2,2)
      Matrix becomes: [1,2,3], [..., ...,4] -> [..., ...,5]
   3. Fill bottom row (reverse): (2,1) -> (2,0)
      Matrix becomes: [ ..., ..., ...], [..., ..., ...], [7,6,5]
   4. Fill left column (upwards): (1,0)
      Matrix becomes: [1,2,3], [8, ..., ...], [7,6,5]
   5. Only center left: (1,1)
      Matrix becomes: [1,2,3], [8,9,4], [7,6,5]
*/

class Solution {
    public int[][] generateMatrix(int n) {
        // Create a matrix of size n x n filled initially with zeros
        int[][] matrix = new int[n][n];
        
        // Define the boundaries for the spiral
        int top = 0, bottom = n - 1, left = 0, right = n - 1;
        // Start number from 1
        int num = 1;
        
        // Loop until the boundaries collapse
        while (top <= bottom && left <= right) {
            // Traverse the top row from left to right
            for (int i = left; i <= right; i++) {
                matrix[top][i] = num++; // Assign current number and increment it
            }
            top++; // Move the top boundary downwards
            
            // Traverse the right column from top to bottom
            for (int i = top; i <= bottom; i++) {
                matrix[i][right] = num++; // Fill rightmost column
            }
            right--; // Move the right boundary leftwards
            
            // Traverse the bottom row from right to left, if the current row is valid
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    matrix[bottom][i] = num++; // Fill bottom row in reverse order
                }
                bottom--; // Move the bottom boundary upwards
            }
            
            // Traverse the left column from bottom to top, if the current column is valid
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    matrix[i][left] = num++; // Fill leftmost column in upward direction
                }
                left++; // Move the left boundary rightwards
            }
        }
        
        // Return the filled spiral matrix
        return matrix;
    }
}
