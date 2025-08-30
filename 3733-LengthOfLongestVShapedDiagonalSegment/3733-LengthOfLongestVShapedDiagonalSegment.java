// Last updated: 8/30/2025, 6:56:19 PM
class Solution {
    private int getExpectedValue(int k) {
        if (k == 0) return 1;
        return (k % 2 == 1) ? 2 : 0;
    }

    public int lenOfVDiagonal(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int[][][] dp_s = new int[n][m][4];
        int[][][] dp_v = new int[n][m][4];
        int maxLen = 0;

        int[][] prev_deltas = {{-1, -1}, {-1, 1}, {1, 1}, {1, -1}};

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                processStraight(grid, dp_s, r, c, 0, prev_deltas);
                maxLen = Math.max(maxLen, dp_s[r][c][0]);
            }
        }
        // Dir 1: TR -> BL
        for (int r = 0; r < n; r++) {
            for (int c = m - 1; c >= 0; c--) {
                processStraight(grid, dp_s, r, c, 1, prev_deltas);
                maxLen = Math.max(maxLen, dp_s[r][c][1]);
            }
        }
        // Dir 2: BR -> TL
        for (int r = n - 1; r >= 0; r--) {
            for (int c = m - 1; c >= 0; c--) {
                processStraight(grid, dp_s, r, c, 2, prev_deltas);
                maxLen = Math.max(maxLen, dp_s[r][c][2]);
            }
        }
        // Dir 3: BL -> TR
        for (int r = n - 1; r >= 0; r--) {
            for (int c = 0; c < m; c++) {
                processStraight(grid, dp_s, r, c, 3, prev_deltas);
                maxLen = Math.max(maxLen, dp_s[r][c][3]);
            }
        }
        
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                processVee(grid, dp_s, dp_v, r, c, 0, prev_deltas);
                maxLen = Math.max(maxLen, dp_v[r][c][0]);
            }
        }
        for (int r = 0; r < n; r++) {
            for (int c = m - 1; c >= 0; c--) {
                processVee(grid, dp_s, dp_v, r, c, 1, prev_deltas);
                maxLen = Math.max(maxLen, dp_v[r][c][1]);
            }
        }
        for (int r = n - 1; r >= 0; r--) {
            for (int c = m - 1; c >= 0; c--) {
                processVee(grid, dp_s, dp_v, r, c, 2, prev_deltas);
                maxLen = Math.max(maxLen, dp_v[r][c][2]);
            }
        }
        for (int r = n - 1; r >= 0; r--) {
            for (int c = 0; c < m; c++) {
                processVee(grid, dp_s, dp_v, r, c, 3, prev_deltas);
                maxLen = Math.max(maxLen, dp_v[r][c][3]);
            }
        }
        
        return maxLen;
    }

    private void processStraight(int[][] grid, int[][][] dp_s, int r, int c, int dir, int[][] prev_deltas) {
        int n = grid.length;
        int m = grid[0].length;
        
        if (grid[r][c] == 1) {
            dp_s[r][c][dir] = 1;
        } else {
            int pr = r + prev_deltas[dir][0];
            int pc = c + prev_deltas[dir][1];
            if (pr >= 0 && pr < n && pc >= 0 && pc < m) {
                int prevLenS = dp_s[pr][pc][dir];
                if (prevLenS > 0 && grid[r][c] == getExpectedValue(prevLenS)) {
                    dp_s[r][c][dir] = prevLenS + 1;
                }
            }
        }
    }

    private void processVee(int[][] grid, int[][][] dp_s, int[][][] dp_v, int r, int c, int dir, int[][] prev_deltas) {
        int n = grid.length;
        int m = grid[0].length;

        int pr = r + prev_deltas[dir][0];
        int pc = c + prev_deltas[dir][1];
        
        if (!(pr >= 0 && pr < n && pc >= 0 && pc < m)) {
            return;
        }

        int prevLenV = dp_v[pr][pc][dir];
        if (prevLenV > 0 && grid[r][c] == getExpectedValue(prevLenV)) {
            dp_v[r][c][dir] = prevLenV + 1;
        }

        int dirIn = (dir - 1 + 4) % 4;
        int lenArm1 = dp_s[pr][pc][dirIn];
        if (lenArm1 > 0 && grid[r][c] == getExpectedValue(lenArm1)) {
            int newVLen = lenArm1 + 1;
            dp_v[r][c][dir] = Math.max(dp_v[r][c][dir], newVLen);
        }
    }
}