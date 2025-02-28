// Algorithm:
// 1. Compute the Longest Common Subsequence (LCS) between str1 and str2 using dynamic programming.
//    - Create a dp table of size (m+1)x(n+1) where m = str1.length and n = str2.length.
//    - dp[i+1][j+1] = dp[i][j] + 1 if str1.charAt(i) == str2.charAt(j), else it's the max of dp[i+1][j] and dp[i][j+1].
// 2. Reconstruct the LCS from the dp table by backtracking from dp[m][n].
// 3. Build the Shortest Common Supersequence (SCS) by merging str1 and str2 using the LCS:
//    - For each character in the LCS, add the non-LCS characters from str1 and str2 before the current LCS character.
//    - Append the common character, then move the pointers forward.
//    - Finally, append any remaining parts from str1 and str2.
// 4. Return the constructed SCS.
//
// Pseudo Code:
// -------------
// function shortestCommonSupersequence(str1, str2):
//     m = length(str1), n = length(str2)
//     dp = 2D array of size (m+1) x (n+1)
//     for i from 0 to m-1:
//         for j from 0 to n-1:
//             if str1[i] == str2[j]:
//                 dp[i+1][j+1] = dp[i][j] + 1
//             else:
//                 dp[i+1][j+1] = max(dp[i+1][j], dp[i][j+1])
//     
//     // Reconstruct LCS from dp table
//     lcs = empty string builder
//     i = m, j = n
//     while i > 0 and j > 0:
//         if str1[i-1] == str2[j-1]:
//             append str1[i-1] to lcs
//             i--, j--
//         else if dp[i-1][j] >= dp[i][j-1]:
//             i--
//         else:
//             j--
//     reverse lcs
//
//     // Build the SCS using the LCS
//     result = empty string builder
//     index1 = 0, index2 = 0
//     for each character c in lcs:
//         while index1 < m and str1[index1] != c:
//             append str1[index1] to result; index1++
//         while index2 < n and str2[index2] != c:
//             append str2[index2] to result; index2++
//         append c to result; index1++; index2++
//     append remaining str1 from index1 to result
//     append remaining str2 from index2 to result
//     return result as string
//
// Visualization Example:
// ----------------------
// Consider str1 = "abac", str2 = "cab"
// 1. Compute LCS:
//    LCS("abac", "cab") -> "ab" (One possible LCS)
// 2. Merge using LCS "ab":
//    - From str1: "a" (matches LCS), then "b", then remaining "ac"
//    - From str2: "c" before "a", then "ab" already in LCS
//    - Merging steps yield: "c" + "ab" + "ac" = "cabac"
//    (Note: The merging process ensures both strings are subsequences in the final SCS)
//

class Solution {
    public String shortestCommonSupersequence(String str1, String str2) {
        int m = str1.length(), n = str2.length();
        // dp table to store the lengths of LCS for subproblems
        int[][] dp = new int[m + 1][n + 1];
        
        // Filling dp table with LCS lengths
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Agar current characters same hain, then LCS length increases by 1 from previous state
                if (str1.charAt(i) == str2.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    // Otherwise, take maximum of excluding current character from either string
                    dp[i + 1][j + 1] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }
        
        // Reconstruct the LCS using the dp table
        StringBuilder lcs = new StringBuilder();
        int i = m, j = n;
        // Backtracking from dp[m][n] to build the LCS string
        while (i > 0 && j > 0) {
            // Agar characters match, then it's part of the LCS
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                lcs.append(str1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] >= dp[i][j - 1]) {
                // Move in the direction of the larger LCS value
                i--;
            } else {
                j--;
            }
        }
        // Reverse LCS since we built it backwards
        lcs.reverse();
        
        // Now, build the Shortest Common Supersequence (SCS) using the LCS as a guide
        StringBuilder res = new StringBuilder();
        int index1 = 0, index2 = 0;
        
        // Iterate through each character in the LCS to merge str1 and str2
        for (char c : lcs.toString().toCharArray()) {
            // Add all characters from str1 until we reach the current LCS character
            while (index1 < str1.length() && str1.charAt(index1) != c) {
                res.append(str1.charAt(index1));
                index1++;
            }
            // Similarly, add all characters from str2 until we reach the same LCS character
            while (index2 < str2.length() && str2.charAt(index2) != c) {
                res.append(str2.charAt(index2));
                index2++;
            }
            // Append the common character from LCS (yeh character dono strings mein present hai)
            res.append(c);
            index1++;
            index2++;
        }
        
        // Append any remaining characters from str1 and str2
        res.append(str1.substring(index1));
        res.append(str2.substring(index2));
        
        // Final SCS which contains both str1 and str2 as subsequences
        return res.toString();
    }
}
