// Last updated: 8/30/2025, 6:56:49 PM
class Solution {

    public int minimumSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        long minArea = Long.MAX_VALUE;


        java.util.function.Function<int[][], Long> solve = (g) -> getArea(g, 0, 0, g.length - 1, g[0].length - 1);

        

        for (int i = 1; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                long area1 = getArea(grid, 0, 0, i - 1, n - 1);
                long area2 = getArea(grid, i, 0, j - 1, n - 1);
                long area3 = getArea(grid, j, 0, m - 1, n - 1);
                if (area1 != Long.MAX_VALUE && area2 != Long.MAX_VALUE && area3 != Long.MAX_VALUE) {
                    minArea = Math.min(minArea, area1 + area2 + area3);
                }
            }
        }


        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                long area1 = getArea(grid, 0, 0, m - 1, i - 1);
                long area2 = getArea(grid, 0, i, m - 1, j - 1);
                long area3 = getArea(grid, 0, j, m - 1, n - 1);
                if (area1 != Long.MAX_VALUE && area2 != Long.MAX_VALUE && area3 != Long.MAX_VALUE) {
                    minArea = Math.min(minArea, area1 + area2 + area3);
                }
            }
        }
        
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                long a1 = getArea(grid, 0, 0, i - 1, j - 1);
                long a2 = getArea(grid, 0, j, i - 1, n - 1);
                long a3 = getArea(grid, i, 0, m - 1, n - 1);
                 if (a1 != Long.MAX_VALUE && a2 != Long.MAX_VALUE && a3 != Long.MAX_VALUE) {
                    minArea = Math.min(minArea, a1 + a2 + a3);
                }
                
                a1 = getArea(grid, 0, 0, i - 1, n - 1);
                a2 = getArea(grid, i, 0, m - 1, j - 1);
                a3 = getArea(grid, i, j, m - 1, n - 1);
                 if (a1 != Long.MAX_VALUE && a2 != Long.MAX_VALUE && a3 != Long.MAX_VALUE) {
                    minArea = Math.min(minArea, a1 + a2 + a3);
                }

                a1 = getArea(grid, 0, 0, i - 1, j - 1);
                a2 = getArea(grid, i, 0, m - 1, j - 1);
                a3 = getArea(grid, 0, j, m - 1, n - 1);
                 if (a1 != Long.MAX_VALUE && a2 != Long.MAX_VALUE && a3 != Long.MAX_VALUE) {
                    minArea = Math.min(minArea, a1 + a2 + a3);
                }

                
                a1 = getArea(grid, 0, 0, m - 1, j - 1);
                a2 = getArea(grid, 0, j, i - 1, n - 1);
                a3 = getArea(grid, i, j, m - 1, n - 1);
                 if (a1 != Long.MAX_VALUE && a2 != Long.MAX_VALUE && a3 != Long.MAX_VALUE) {
                    minArea = Math.min(minArea, a1 + a2 + a3);
                }
            }
        }


        return (int) minArea;
    }

    private long getArea(int[][] grid, int r1, int c1, int r2, int c2) {
        int minR = Integer.MAX_VALUE, maxR = Integer.MIN_VALUE;
        int minC = Integer.MAX_VALUE, maxC = Integer.MIN_VALUE;
        boolean foundOne = false;

        for (int i = r1; i <= r2; i++) {
            for (int j = c1; j <= c2; j++) {
                if (grid[i][j] == 1) {
                    foundOne = true;
                    minR = Math.min(minR, i);
                    maxR = Math.max(maxR, i);
                    minC = Math.min(minC, j);
                    maxC = Math.max(maxC, j);
                }
            }
        }

        if (!foundOne) {
            return Long.MAX_VALUE;
        }

        return (long) (maxR - minR + 1) * (maxC - minC + 1);
    }
}