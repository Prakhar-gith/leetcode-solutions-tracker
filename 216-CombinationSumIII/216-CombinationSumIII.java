// Algorithm:
// 1. Use backtracking to generate combinations using numbers 1 to 9.
// 2. Start with an empty combination and iterate from a starting number to 9.
// 3. In each recursive call, if the current combination's size equals k:
//       - Check if the remaining target sum (n) is zero.
//       - If yes, we have found a valid combination; add it to the result.
// 4. If not, iterate through numbers from 'start' to 9 and include the number if it doesn't exceed the remaining sum.
// 5. After including a number, recursively call backtracking with updated parameters (next number and reduced sum).
// 6. Backtrack by removing the last added number to try other possibilities.

// Pseudo Code:
// -------------
// function combinationSum3(k, n):
//     result = new List of Lists
//     current = empty list
//     backtrack(start = 1, k, n, current, result)
//     return result
//
// function backtrack(start, k, n, current, result):
//     if current.size() == k:
//         if n == 0:
//             add a copy of current to result
//         return
//     for i from start to 9:
//         if i > n: break (since numbers are positive and sorted)
//         add i to current
//         backtrack(i + 1, k, n - i, current, result)
//         remove last element from current (backtracking)

// Visualization Example for k = 3, n = 7:
// ----------------------------------------
// Start: current = [], n = 7, start = 1
// 1st level: choose 1 -> current = [1], n becomes 6, start becomes 2
// 2nd level: choose 2 -> current = [1,2], n becomes 4, start becomes 3
// 3rd level: choose 4 -> current = [1,2,4], n becomes 0, valid combination found, add [1,2,4]
// Backtrack and try other possibilities (but none will sum to 7 with 3 numbers)

// Code:
class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        // List to store all valid combinations
        List<List<Integer>> result = new ArrayList<>();
        // Start backtracking with initial parameters
        backtrack(1, k, n, new ArrayList<>(), result);
        return result;
    }
    
    // Helper function for backtracking
    private void backtrack(int start, int k, int n, List<Integer> current, List<List<Integer>> result) {
        // Agar current combination ki size k ke barabar ho gayi hai
        if (current.size() == k) {
            // Check if the sum of current combination exactly equals n
            if (n == 0) {
                // Add a copy of current combination to result list
                result.add(new ArrayList<>(current));
            }
            // Return to backtrack further (either valid or not valid)
            return;
        }
        
        // Iterate through numbers from 'start' to 9
        for (int i = start; i <= 9; i++) {
            // Agar current number is greater than the remaining sum, break (optimization)
            if (i > n) break; // further numbers will only increase the sum
            
            // Add current number to the combination
            current.add(i);
            // Recursively call backtracking for the next number, with updated sum (n - i)
            backtrack(i + 1, k, n - i, current, result);
            // Backtracking step: remove the last element to try next candidate
            current.remove(current.size() - 1);
        }
    }
}
