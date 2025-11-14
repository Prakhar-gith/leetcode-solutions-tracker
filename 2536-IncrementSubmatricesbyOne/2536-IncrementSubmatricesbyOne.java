// Last updated: 11/15/2025, 2:01:38 AM
class Solution {
    public int[][] rangeAddQueries(int n, int[][] queries) {
        
        int[][] mat = new int[n][n];
        
        for (int[] q : queries) {
            int r1 = q[0];
            int c1 = q[1];
            int r2 = q[2];
            int c2 = q[3];
            
            mat[r1][c1]++;
            
            if (r2 + 1 < n) {
                mat[r2 + 1][c1]--;
            }
            if (c2 + 1 < n) {
                mat[r1][c2 + 1]--;
            }
            if (r2 + 1 < n && c2 + 1 < n) {
                mat[r2 + 1][c2 + 1]++;
            }
        }
        
        for (int r = 0; r < n; r++) {
            for (int c = 1; c < n; c++) {
                mat[r][c] += mat[r][c - 1];
            }
        }
        
        for (int c = 0; c < n; c++) {
            for (int r = 1; r < n; r++) {
                mat[r][c] += mat[r - 1][c];
            }
        }
        
        return mat;
    }
}