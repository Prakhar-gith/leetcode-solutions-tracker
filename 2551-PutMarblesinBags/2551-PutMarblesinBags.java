// Last updated: 3/31/2025, 12:21:30 PM
// Algorithm:
// 1. Understand that any valid distribution's total cost can be expressed as:
//       base = weights[0] + weights[n-1]  (cost contributed by the first and last marble)
//       plus the sum of (k-1) additional costs obtained from making cuts between adjacent marbles.
// 2. For each possible cut between marble i-1 and i (for i=1 to n-1), 
//    the additional cost contributed if we make a cut there is: weights[i-1] + weights[i].
// 3. To obtain the minimum overall score, we want to choose the (k-1) cuts with the smallest additional costs.
//    Similarly, for the maximum overall score, choose the (k-1) cuts with the largest additional costs.
// 4. Thus, compute an array "diffs" of size n-1 where diffs[i] = weights[i-1] + weights[i] for i = 1..n-1.
// 5. Sort this diffs array.
// 6. The minimum extra cost is the sum of the smallest (k-1) values and the maximum extra cost is the sum of the largest (k-1) values.
// 7. Since the base cost is common for any distribution, the difference between the maximum and minimum scores
//    is simply: (maxExtra - minExtra).
// 8. Edge-case: If k == 1, no cut is made so answer is 0.
//
// Pseudo Code:
// -------------
// function putMarbles(weights, k):
//     n = length(weights)
//     if k == 1:
//         return 0
//     base = weights[0] + weights[n-1]
//     create empty list diffs
//     for i from 1 to n-1:
//         diffs.add(weights[i-1] + weights[i])
//     sort diffs
//     minExtra = sum of first (k-1) elements in diffs
//     maxExtra = sum of last (k-1) elements in diffs
//     return maxExtra - minExtra
//
// Visualization Example:
// ----------------------
// Example 1: weights = [1, 3, 5, 1], k = 2
//   - Base = 1 + 1 = 2
//   - diffs = [ (1+3)=4, (3+5)=8, (5+1)=6 ]
//   - Sorted diffs = [4, 6, 8]
//   - For k-1 = 1 cut:
//         minExtra = 4, maxExtra = 8
//   - Difference = 8 - 4 = 4
//
// Example 2: weights = [1, 3], k = 2
//   - Base = 1 + 3 = 4
//   - diffs = [ (1+3)=4 ]
//   - minExtra = maxExtra = 4
//   - Difference = 4 - 4 = 0

class Solution {
    public long putMarbles(int[] weights, int k) {
        int n = weights.length;
        // If only one bag is required, there is no cut, so the difference is 0.
        if (k == 1) {
            return 0;
        }
        
        // Create an array to store the cost of each potential cut between adjacent marbles.
        long[] diffs = new long[n - 1];
        // Fill the diffs array where each element represents:
        // cost = weights[i-1] + weights[i]
        for (int i = 1; i < n; i++) {
            diffs[i - 1] = (long)weights[i - 1] + weights[i];
        }
        
        // Sort the diffs array so that we can easily pick smallest and largest (k-1) cuts.
        Arrays.sort(diffs);
        
        long minExtra = 0, maxExtra = 0;
        // For minimal score, pick the smallest (k-1) cuts
        for (int i = 0; i < k - 1; i++) {
            minExtra += diffs[i];
        }
        // For maximum score, pick the largest (k-1) cuts
        for (int i = diffs.length - (k - 1); i < diffs.length; i++) {
            maxExtra += diffs[i];
        }
        
        // The base cost is the same in both cases, so the difference is just the difference in extra costs.
        return maxExtra - minExtra;
    }
}
