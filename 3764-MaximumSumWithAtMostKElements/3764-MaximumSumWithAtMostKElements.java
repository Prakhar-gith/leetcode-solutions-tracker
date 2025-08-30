// Last updated: 8/30/2025, 6:56:10 PM
class Solution {
    public long maxSum(int[][] grid, int[] limits, int k) {
        List<Long> picks = new ArrayList<>();
        int n = grid.length, m = grid[0].length;
        
        for (int i = 0; i < n; i++) {
            Arrays.sort(grid[i]);
            int count = limits[i];
            int j = m - 1;  
            while (count > 0 && j >= 0) {
                picks.add((long) grid[i][j]);
                count--;
                j--;
            }
        }
        
        picks.sort(Collections.reverseOrder());
        
        long ans = 0;
        int pickCount = Math.min(k, picks.size());
        for (int i = 0; i < pickCount; i++) {
            ans += picks.get(i);
        }
        
        return ans;
    }
}



// // For each row in grid:
// // a. Sort the row in descending order.
// // b. Take the first 'min(limits[i], row.length)' elements. But since limits[i] is given as per the problem, which is <= m (since m is the row length), so we take the first 'limits[i]' elements. Because the row has m elements, and limits[i] is <= m.
//     public long maxSum(int[][] grid, int[] limits, int k) {
//         int n = grid.length;
//         int m = grid[0].length;
//         List<Integer> candidates = new ArrayList<>();
//         for (int i = 0; i < n; i++) {
//             int limit = limits[i];
//             if (limit > 0) {
//                 Integer[] rowCopy = new Integer[m];
//                 for (int j = 0; j < m; j++) {
//                     rowCopy[j] = grid[i][j];
//                 }
//                 Arrays.sort(rowCopy, Collections.reverseOrder());
//                 for (int j = 0; j < limit && j < m; j++) {
//                     candidates.add(rowCopy[j]);
//                 }
//             }
//         }
//         long sum = 0;
//         for (int i = 0; i < k && i < candidates.size(); i++) {
//             sum += candidates.get(i);
//         }
//         return sum;
//     }