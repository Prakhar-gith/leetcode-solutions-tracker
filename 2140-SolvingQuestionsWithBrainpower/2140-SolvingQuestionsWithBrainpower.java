// Last updated: 4/2/2025, 3:19:33 AM
// Algorithm:
// 1. We use Dynamic Programming (DP) to decide whether to solve or skip each question.
// 2. Let dp[i] represent the maximum points that can be earned starting from question i.
// 3. For each question at index i, there are two choices:
//    a. Solve question i: Earn questions[i][0] points, then skip the next questions[i][1] questions.
//       So, next available question is i + questions[i][1] + 1. The total becomes questions[i][0] + dp[i + questions[i][1] + 1].
//    b. Skip question i: In that case, the total points is dp[i+1].
// 4. The recurrence is: dp[i] = max(questions[i][0] + dp[i + questions[i][1] + 1], dp[i+1]).
// 5. We fill dp array backwards from the last question to the first.
// 6. The answer is dp[0] which is the maximum points starting from question 0.

// Pseudo Code:
// -------------
// function maxPoints(questions):
//     n = length(questions)
//     dp = array of size n+1, initialized with 0
//     for i from n-1 downto 0:
//         solve = questions[i][0] + (if i + questions[i][1] + 1 <= n then dp[i + questions[i][1] + 1] else 0)
//         skip = dp[i+1]
//         dp[i] = max(solve, skip)
//     return dp[0]
//
// Visualization Example:
// ----------------------
// For questions = [[3,2],[4,3],[4,4],[2,5]]
//   - Start from last question (index 3):
//         dp[3] = max( 2 + dp[3+5+1], dp[4] )
//               = max( 2 + 0, 0 ) = 2
//   - For index 2:
//         dp[2] = max( 4 + dp[2+4+1], dp[3] )
//               = max( 4 + 0, 2 ) = 4
//   - For index 1:
//         dp[1] = max( 4 + dp[1+3+1], dp[2] )
//               = max( 4 + dp[5], 4 ) = max( 4 + 0, 4 ) = 4
//   - For index 0:
//         dp[0] = max( 3 + dp[0+2+1], dp[1] )
//               = max( 3 + dp[3], 4 ) = max( 3 + 2, 4 ) = 5
//   - Answer is dp[0] = 5
//

class Solution {
    public long mostPoints(int[][] questions) {
        int n = questions.length;
        // Create dp array of size n+1. Extra element for base case dp[n]=0.
        long[] dp = new long[n + 1];
        
        // Process questions in reverse order (from last question to first)
        for (int i = n - 1; i >= 0; i--) {
            // Option 1: Solve the current question.
            // Calculate the index of the next available question after skipping.
            int next = i + questions[i][1] + 1;
            // If next index is within bounds, add its dp value; else, it's 0.
            long solve = questions[i][0] + (next < dp.length ? dp[next] : 0);
            
            // Option 2: Skip the current question.
            long skip = dp[i + 1];
            
            // Choose the maximum points possible.
            dp[i] = Math.max(solve, skip);
        }
        
        // dp[0] stores the maximum points starting from the first question.
        return dp[0];
    }
}
