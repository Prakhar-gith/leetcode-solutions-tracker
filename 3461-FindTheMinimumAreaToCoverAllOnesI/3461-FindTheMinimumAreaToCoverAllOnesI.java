// Last updated: 8/30/2025, 6:56:48 PM
class Solution {

    public int minimumArea(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;


        int minRow = Integer.MAX_VALUE;
        int maxRow = -1;
        int minCol = Integer.MAX_VALUE;
        int maxCol = -1;


        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1) {
                    minRow = Math.min(minRow, r);
                    maxRow = Math.max(maxRow, r);
                    minCol = Math.min(minCol, c);
                    maxCol = Math.max(maxCol, c);
                }
            }
        }
        
       
        int height = maxRow - minRow + 1;
        int width = maxCol - minCol + 1;


        return height * width;
    }
}